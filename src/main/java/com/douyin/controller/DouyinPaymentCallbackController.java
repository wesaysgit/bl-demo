package com.douyin.controller;

import com.douyin.config.DouyinConfig;
import com.douyin.dto.PaymentCallbackDTO;
import com.douyin.dto.PaymentCallbackResponseDTO;
import com.douyin.enums.CallbackTypeEnum;
import com.douyin.service.DouyinCallbackService;
import com.douyin.util.DouyinSignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 抖音通用回调控制器
 * 处理抖音开放平台的各种回调事件
 */
@Slf4j
@RestController
@RequestMapping("/douyin")
public class DouyinPaymentCallbackController {
    
    @Autowired
    private DouyinCallbackService callbackService;
    
    @Autowired
    private DouyinConfig douyinConfig;
    
    /**
     * 抖音通用回调接口
     * 处理抖音开放平台的各种回调事件（支付、退款、履约等）
     * 
     * @param request HTTP请求
     * @param callbackDTO 回调参数
     * @return 回调响应
     */
    @PostMapping("/notify")
    public ResponseEntity<PaymentCallbackResponseDTO> notify(
            HttpServletRequest request,
            @RequestBody PaymentCallbackDTO callbackDTO) {
        
        try {
            log.info("收到抖音回调请求 - 类型: {}, 版本: {}", callbackDTO.getType(), callbackDTO.getVersion());
            
            // 1. 解析请求信息
            DouyinSignUtil.ReqInfo reqInfo = DouyinSignUtil.resolveRequest(request);
            
            // 2. 验证签名（如果启用）
            if (douyinConfig.getEnableSignatureVerification() && douyinConfig.getPlatformPublicKey() != null) {
                if (!DouyinSignUtil.verifyCallbackSignature(reqInfo, douyinConfig.getPlatformPublicKey())) {
                    log.error("签名验证失败");
                    return ResponseEntity.ok(PaymentCallbackResponseDTO.error("签名验证失败"));
                }
                log.info("签名验证成功");
            }
            
            // 3. 验证回调版本
            if (!"3.0".equals(callbackDTO.getVersion())) {
                log.warn("不支持的回调版本: {}", callbackDTO.getVersion());
                return ResponseEntity.ok(PaymentCallbackResponseDTO.error("不支持的回调版本"));
            }
            
            // 4. 根据回调类型分发处理
            PaymentCallbackResponseDTO response = handleCallbackByType(callbackDTO);
            
            // 5. 返回响应
            if (response.getErrNo() == 0) {
                log.info("回调处理成功 - 类型: {}", callbackDTO.getType());
                return ResponseEntity.ok(response);
            } else {
                log.error("回调处理失败 - 类型: {}, 错误: {}", callbackDTO.getType(), response.getErrTips());
                return ResponseEntity.ok(response);
            }
            
        } catch (Exception e) {
            log.error("处理回调异常 - 类型: {}", callbackDTO.getType(), e);
            return ResponseEntity.ok(PaymentCallbackResponseDTO.error("系统异常: " + e.getMessage()));
        }
    }
    
    /**
     * 根据回调类型分发处理
     * 
     * @param callbackDTO 回调参数
     * @return 处理结果
     */
    private PaymentCallbackResponseDTO handleCallbackByType(PaymentCallbackDTO callbackDTO) {
        String callbackType = callbackDTO.getType();
        
        // 检查是否为支持的回调类型
        if (!CallbackTypeEnum.isSupported(callbackType)) {
            log.warn("不支持的回调类型: {}", callbackType);
            return PaymentCallbackResponseDTO.error("不支持的回调类型: " + callbackType);
        }
        
        CallbackTypeEnum typeEnum = CallbackTypeEnum.fromCode(callbackType);
        log.info("处理{}回调", typeEnum.getDescription());
        
        switch (typeEnum) {
            case PAYMENT:
                return handlePaymentCallback(callbackDTO);
            case REFUND:
                return handleRefundCallback(callbackDTO);
            case FULFILLMENT:
                return handleFulfillmentCallback(callbackDTO);
            case SETTLEMENT:
                return handleSettlementCallback(callbackDTO);
            default:
                log.warn("未实现的回调类型处理: {}", callbackType);
                return PaymentCallbackResponseDTO.error("未实现的回调类型处理: " + callbackType);
        }
    }
    
    /**
     * 处理支付回调
     * 
     * @param callbackDTO 回调参数
     * @return 处理结果
     */
    private PaymentCallbackResponseDTO handlePaymentCallback(PaymentCallbackDTO callbackDTO) {
        log.info("处理支付回调");
        return callbackService.handlePaymentCallback(callbackDTO.getMsg());
    }
    
    /**
     * 处理退款回调
     * 
     * @param callbackDTO 回调参数
     * @return 处理结果
     */
    private PaymentCallbackResponseDTO handleRefundCallback(PaymentCallbackDTO callbackDTO) {
        log.info("处理退款回调");
        return callbackService.handleRefundCallback(callbackDTO.getMsg());
    }
    
    /**
     * 处理履约回调
     * 
     * @param callbackDTO 回调参数
     * @return 处理结果
     */
    private PaymentCallbackResponseDTO handleFulfillmentCallback(PaymentCallbackDTO callbackDTO) {
        log.info("处理履约回调");
        return callbackService.handleFulfillmentCallback(callbackDTO.getMsg());
    }
    
    /**
     * 处理结算回调
     * 
     * @param callbackDTO 回调参数
     * @return 处理结果
     */
    private PaymentCallbackResponseDTO handleSettlementCallback(PaymentCallbackDTO callbackDTO) {
        log.info("处理结算回调");
        return callbackService.handleSettlementCallback(callbackDTO.getMsg());
    }
    
    /**
     * 回调测试接口
     * 
     * @return 测试结果
     */
    @GetMapping("/test")
    public String test() {
        return "抖音通用回调服务测试成功";
    }
    
    /**
     * 健康检查接口
     * 
     * @return 健康状态
     */
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
