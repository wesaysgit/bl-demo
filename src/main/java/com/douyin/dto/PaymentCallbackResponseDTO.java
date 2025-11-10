package com.douyin.dto;

import lombok.Data;

/**
 * 抖音支付回调响应DTO
 */
@Data
public class PaymentCallbackResponseDTO {
    
    /**
     * 错误码
     * 0表示成功
     */
    private Long errNo;
    
    /**
     * 错误提示
     * success表示成功
     */
    private String errTips;
    
    /**
     * 成功响应
     */
    public static PaymentCallbackResponseDTO success() {
        PaymentCallbackResponseDTO response = new PaymentCallbackResponseDTO();
        response.setErrNo(0L);
        response.setErrTips("success");
        return response;
    }
    
    /**
     * 失败响应
     */
    public static PaymentCallbackResponseDTO error(String message) {
        PaymentCallbackResponseDTO response = new PaymentCallbackResponseDTO();
        response.setErrNo(1L);
        response.setErrTips(message);
        return response;
    }
}
