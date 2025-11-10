package com.douyin.controller;

import com.douyin.dto.ByteAuthorizationDTO;
import com.douyin.dto.RequestOrderDTO;
import com.douyin.service.DouyinOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 抖音下单控制器
 */
@Slf4j
@RestController
@RequestMapping("/douyin/order")
public class DouyinOrderController {
    
    @Autowired
    private DouyinOrderService douyinOrderService;
    
    /**
     * 生成抖音下单签名
     * 
     * @param requestOrderDTO 下单请求参数
     * @return 包含签名的授权信息
     */
    @PostMapping("/generateSignature")
    public ByteAuthorizationDTO generateSignature(@RequestBody RequestOrderDTO requestOrderDTO) {
        // 这里应该从配置文件或数据库中获取
        String appId = "your_app_id";
        String privateKeyStr = "your_private_key";
        String keyVersion = "1";
        
        return douyinOrderService.generateOrderSignature(requestOrderDTO, appId, privateKeyStr, keyVersion);
    }
    
    /**
     * 测试接口
     * 
     * @return 测试结果
     */
    @GetMapping("/test")
    public String test() {
        return "抖音下单服务测试成功";
    }
}
