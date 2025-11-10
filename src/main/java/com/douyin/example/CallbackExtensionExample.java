package com.douyin.example;

import com.douyin.dto.PaymentCallbackResponseDTO;
import com.douyin.enums.CallbackTypeEnum;
import com.douyin.service.DouyinCallbackService;
import lombok.extern.slf4j.Slf4j;

/**
 * 回调扩展示例
 * 展示如何扩展新的回调类型处理
 */
@Slf4j
public class CallbackExtensionExample {
    
    /**
     * 示例：如何扩展新的回调类型
     */
    public static void main(String[] args) {
        System.out.println("=== 抖音回调扩展示例 ===");
        
        // 1. 检查支持的回调类型
        System.out.println("\n--- 支持的回调类型 ---");
        for (CallbackTypeEnum type : CallbackTypeEnum.values()) {
            System.out.println(type.getCode() + " - " + type.getDescription());
        }
        
        // 2. 验证回调类型
        System.out.println("\n--- 回调类型验证 ---");
        String[] testTypes = {"payment", "refund", "fulfillment", "settlement", "unknown"};
        for (String type : testTypes) {
            boolean supported = CallbackTypeEnum.isSupported(type);
            System.out.println(type + " 是否支持: " + supported);
        }
        
        // 3. 展示如何添加新的回调类型
        System.out.println("\n--- 如何添加新的回调类型 ---");
        showHowToAddNewCallbackType();
        
        System.out.println("\n=== 示例完成 ===");
    }
    
    /**
     * 展示如何添加新的回调类型
     */
    private static void showHowToAddNewCallbackType() {
        System.out.println("1. 在 CallbackTypeEnum 中添加新的枚举值");
        System.out.println("   NEW_TYPE(\"new_type\", \"新类型回调\")");
        
        System.out.println("2. 在 DouyinCallbackService 接口中添加新方法");
        System.out.println("   PaymentCallbackResponseDTO handleNewTypeCallback(String msgJson);");
        
        System.out.println("3. 在 DouyinPaymentCallbackService 中实现新方法");
        System.out.println("   @Override");
        System.out.println("   public PaymentCallbackResponseDTO handleNewTypeCallback(String msgJson) {");
        System.out.println("       // 实现具体业务逻辑");
        System.out.println("       return PaymentCallbackResponseDTO.success();");
        System.out.println("   }");
        
        System.out.println("4. 在控制器中添加新的处理分支");
        System.out.println("   case NEW_TYPE:");
        System.out.println("       return handleNewTypeCallback(callbackDTO);");
        
        System.out.println("5. 更新测试类，添加新类型的测试用例");
    }
    
    /**
     * 示例：自定义回调服务实现
     */
    public static class CustomCallbackService implements DouyinCallbackService {
        
        @Override
        public PaymentCallbackResponseDTO handlePaymentCallback(String msgJson) {
            log.info("自定义支付回调处理: {}", msgJson);
            // 实现自定义的支付回调逻辑
            return PaymentCallbackResponseDTO.success();
        }
        
        @Override
        public PaymentCallbackResponseDTO handleRefundCallback(String msgJson) {
            log.info("自定义退款回调处理: {}", msgJson);
            // 实现自定义的退款回调逻辑
            return PaymentCallbackResponseDTO.success();
        }
        
        @Override
        public PaymentCallbackResponseDTO handleFulfillmentCallback(String msgJson) {
            log.info("自定义履约回调处理: {}", msgJson);
            // 实现自定义的履约回调逻辑
            return PaymentCallbackResponseDTO.success();
        }
        
        @Override
        public PaymentCallbackResponseDTO handleSettlementCallback(String msgJson) {
            log.info("自定义结算回调处理: {}", msgJson);
            // 实现自定义的结算回调逻辑
            return PaymentCallbackResponseDTO.success();
        }
    }
}
