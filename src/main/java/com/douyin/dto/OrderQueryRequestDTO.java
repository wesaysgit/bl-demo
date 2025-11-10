package com.douyin.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 抖音订单查询请求DTO
 */
@Data
public class OrderQueryRequestDTO {
    
    /**
     * 抖音开放平台侧订单id
     * 长度 <= 64byte
     * 与out_order_no二选一，都传时优先使用order_id
     */
    @JSONField(name = "order_id")
    private String orderId;
    
    /**
     * 开发者系统生成的订单号
     * 长度 <= 64byte
     * 与order_id二选一，都传时优先使用order_id
     */
    @JSONField(name = "out_order_no")
    private String outOrderNo;
    
    /**
     * 访问令牌
     * 通过抖音开放平台授权获取
     * 注意：该字段不会被序列化到JSON中，而是通过HTTP请求头传递
     */
    @NotBlank(message = "访问令牌不能为空")
    @JSONField(serialize = false)
    private String accessToken;
    
    /**
     * 应用ID
     * 可选，如果配置了默认值则不需要传
     */
    @JSONField(name = "app_id")
    private String appId;
}
