package com.douyin;

import com.alibaba.fastjson.JSON;
import com.douyin.openapi.client.Client;
import com.douyin.openapi.client.models.*;
import com.douyin.openapi.credential.models.Config;

import java.util.Collections;

/**
 * 使用官方SDK的退款示例
 * 这是推荐的做法，使用官方提供的类而不是自定义DTO
 */
public class SdkRefundDemo {

    public static String assessToken = "clt.4f4fa939f23ad674651fa0d545b74f8852XlQsoY8mE38LeYubObGM7AkTNx_lf";
    public static String appId = "tta5f4d3493af8920701";
    public static String appSecret = "98ada26bef8c67cd555ff8dd0cc8e43bf8108cef";

    public static void main(String[] args) {
        System.out.println("=== 使用官方SDK的退款和查询订单示例 ===");
        
        try {
            // 1. 创建Client实例
            Config config = new Config()
                .setClientKey(appId)
                .setClientSecret(appSecret);
            
            Client client = new Client(config);
            
            // 2. 查询订单示例
            System.out.println("\n=== 查询订单示例 ===");
//            queryOrderWithSdk(client);
            
            // 3. 退款示例
//            System.out.println("\n=== 退款示例 ===");
            createRefundWithSdk(client);
            
        } catch (Exception e) {
            System.err.println("操作失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 使用SDK查询订单
     */
    private static void queryOrderWithSdk(Client client) {
        try {
            // 创建订单查询请求
            DeveloperOrderQueryRequest queryRequest = new DeveloperOrderQueryRequest();
            queryRequest.setAccessToken("clt.4f4fa939f23ad674651fa0d545b74f8852XlQsoY8mE38LeYubObGM7AkTNx_lf");

            queryRequest.setOutOrderNo("149202510112254488026265");

            // 调用查询订单接口
            DeveloperOrderQueryResponse queryResponse = client.DeveloperOrderQuery(queryRequest);
            
            // 处理响应
            System.out.println("订单查询结果: " + JSON.toJSONString(queryResponse));
            
            if (queryResponse != null && queryResponse.getErrNo() == 0) {
                System.out.println("✅ 订单查询成功！");
                System.out.println("📋 订单详情请查看上面的JSON结果");
            } else {
                System.out.println("❌ 订单查询失败！");
                if (queryResponse != null) {
                    System.out.println("   错误码: " + queryResponse.getErrNo());
                    System.out.println("   错误信息: " + queryResponse.getErrMsg());
                }
            }
            
        } catch (Exception e) {
            System.err.println("查询订单失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 使用SDK创建退款
     */
    private static void createRefundWithSdk(Client client) {
        try {
            // 创建退款请求
            DeveloperRefundCreateRequest refundRequest = new DeveloperRefundCreateRequest();
            refundRequest.setAccessToken(assessToken);
            refundRequest.setOutRefundNo("REFUND_" + System.currentTimeMillis());
            refundRequest.setOrderId("motb75599777630635481875109");
            refundRequest.setRefundAll(false);
            
            // 设置订单入口Schema
            DeveloperRefundCreateRequestOrderEntrySchema entrySchema = new DeveloperRefundCreateRequestOrderEntrySchema();
            entrySchema.setPath("subpackages/airport-parking/order-detail/index");
            refundRequest.setOrderEntrySchema(entrySchema);

            // 设置退款原因
            DeveloperRefundCreateRequestRefundReasonItem reasonItem = new DeveloperRefundCreateRequestRefundReasonItem();
            reasonItem.setCode(101L);
            reasonItem.setText("测试");
            refundRequest.setRefundReason(Collections.singletonList(reasonItem));
            refundRequest.setRefundTotalAmount(4L);

            DeveloperRefundCreateRequestItemOrderDetailItem itemOrderDetailItem = new DeveloperRefundCreateRequestItemOrderDetailItem();
            itemOrderDetailItem.setItemOrderId("motb75599777630635809555109");
            itemOrderDetailItem.setRefundAmount(4L);
            refundRequest.setItemOrderDetail(Collections.singletonList(itemOrderDetailItem));
            
            // 调用退款接口
            DeveloperRefundCreateResponse refundResponse = client.DeveloperRefundCreate(refundRequest);
            
            // 处理响应
            System.out.println("退款结果: " + JSON.toJSONString(refundResponse));
            
            if (refundResponse != null && refundResponse.getErrNo() == 0) {
                System.out.println("✅ 退款成功！");
                System.out.println("📋 退款详情请查看上面的JSON结果");
            } else {
                System.out.println("❌ 退款失败！");
                if (refundResponse != null) {
                    System.out.println("   错误码: " + refundResponse.getErrNo());
                    System.out.println("   错误信息: " + refundResponse.getErrMsg());
                }
            }
            
        } catch (Exception e) {
            System.err.println("退款失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

}
