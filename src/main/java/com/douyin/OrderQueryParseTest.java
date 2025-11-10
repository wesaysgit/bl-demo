package com.douyin;

import com.alibaba.fastjson.JSON;
import com.douyin.dto.OrderQueryResponseDTO;

/**
 * 订单查询响应解析测试
 */
public class OrderQueryParseTest {
    
    public static void main(String[] args) {
        // 抖音真实响应数据
        String jsonResponse = "{\n" +
            "  \"log_id\": \"202510111643519A7DCEEF929174F1083F\",\n" +
            "  \"data\": {\n" +
            "    \"discount_amount\": 0,\n" +
            "    \"pay_status\": \"SUCCESS\",\n" +
            "    \"total_amount\": 2,\n" +
            "    \"trade_time\": 1760171999000,\n" +
            "    \"out_order_no\": \"149202510111635354020088\",\n" +
            "    \"pay_channel\": 10,\n" +
            "    \"pay_time\": 1760172007000,\n" +
            "    \"total_currency_amount\": 2,\n" +
            "    \"currency\": \"CNY\",\n" +
            "    \"item_order_list\": [\n" +
            "      {\n" +
            "        \"item_order_amount\": 2,\n" +
            "        \"item_order_currency_amount\": 2,\n" +
            "        \"item_order_id\": \"motb75598811693371742985109\",\n" +
            "        \"sku_id\": \"1\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"merchant_uid\": \"75542533284907768660\",\n" +
            "    \"order_id\": \"motb75598811693371415305109\",\n" +
            "    \"real_merchant_uid\": \"75542533284907768660\",\n" +
            "    \"app_id\": \"tta5f4d3493af8920701\",\n" +
            "    \"channel_pay_id\": \"2105012510110503451310995323\",\n" +
            "    \"promotion_list\": null,\n" +
            "    \"user_bill_pay_id\": \"2001022510110103345253095045\"\n" +
            "  },\n" +
            "  \"err_msg\": \"\",\n" +
            "  \"err_no\": 0\n" +
            "}";
        
        try {
            System.out.println("=== 开始解析抖音订单查询响应 ===\n");
            
            OrderQueryResponseDTO response = JSON.parseObject(jsonResponse, OrderQueryResponseDTO.class);
            
            System.out.println("✅ JSON 解析成功！\n");
            System.out.println("基础信息:");
            System.out.println("  Log ID: " + response.getLogId());
            System.out.println("  错误码: " + response.getErrNo());
            System.out.println("  错误信息: " + response.getErrMsg());
            System.out.println("  是否成功: " + response.isSuccess());
            System.out.println();
            
            if (response.getData() != null) {
                OrderQueryResponseDTO.OrderData data = response.getData();
                System.out.println("订单信息:");
                System.out.println("  订单ID: " + data.getOrderId());
                System.out.println("  外部订单号: " + data.getOutOrderNo());
                System.out.println("  应用ID: " + data.getAppId());
                System.out.println("  支付状态: " + data.getPayStatus());
                System.out.println("  订单总金额: " + data.getTotalAmount() + " 分");
                System.out.println("  优惠金额: " + data.getDiscountAmount() + " 分");
                System.out.println("  币种: " + data.getCurrency());
                System.out.println("  支付渠道: " + data.getPayChannel() + " (10=抖音支付)");
                System.out.println("  商户号: " + data.getMerchantUid());
                System.out.println("  真实商户号: " + data.getRealMerchantUid());
                System.out.println("  渠道支付单号: " + data.getChannelPayId());
                System.out.println("  用户账单支付ID: " + data.getUserBillPayId());
                System.out.println("  交易时间: " + data.getTradeTime());
                System.out.println("  支付时间: " + data.getPayTime());
                System.out.println();
                
                if (data.getItemOrderList() != null && !data.getItemOrderList().isEmpty()) {
                    System.out.println("商品订单列表:");
                    for (int i = 0; i < data.getItemOrderList().size(); i++) {
                        OrderQueryResponseDTO.OrderData.ItemOrder item = data.getItemOrderList().get(i);
                        System.out.println("  商品 " + (i + 1) + ":");
                        System.out.println("    商品订单ID: " + item.getItemOrderId());
                        System.out.println("    商品ID: " + item.getSkuId());
                        System.out.println("    商品金额: " + item.getItemOrderAmount() + " 分");
                        System.out.println("    商品币种金额: " + item.getItemOrderCurrencyAmount() + " 分");
                    }
                }
                System.out.println();
                System.out.println("=== 解析测试完成 ✅ ===");
            }
            
        } catch (Exception e) {
            System.err.println("❌ JSON 解析失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

