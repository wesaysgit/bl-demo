package com.douyin.service;

import com.douyin.dto.ByteAuthorizationDTO;
import com.douyin.dto.RequestOrderDTO;
import com.douyin.util.DouyinPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 抖音下单服务
 * 使用DouyinPayUtil工具类处理下单逻辑
 */
@Slf4j
@Service
public class DouyinOrderService {
    
    /**
     * 生成下单参数与签名
     * 
     * @param requestOrderDTO 下单请求参数
     * @param appId 应用appid
     * @param privateKeyStr 应用私钥
     * @param keyVersion 公钥版本
     * @return 包含签名的授权信息
     */
    public ByteAuthorizationDTO generateOrderSignature(RequestOrderDTO requestOrderDTO, 
                                                      String appId, 
                                                      String privateKeyStr, 
                                                      String keyVersion) {
        // 验证请求参数
        if (!DouyinPayUtil.validateRequestOrder(requestOrderDTO)) {
            throw new IllegalArgumentException("请求参数验证失败");
        }
        
        // 使用工具类生成签名
        return DouyinPayUtil.generateOrderSignature(requestOrderDTO, appId, privateKeyStr, keyVersion);
    }
}
