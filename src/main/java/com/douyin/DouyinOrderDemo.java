package com.douyin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.douyin.dto.ByteAuthorizationDTO;
import com.douyin.dto.RequestOrderDTO;
import com.douyin.util.DouyinPayUtil;
import com.es.lsapp.TradeNoUtil;

import java.util.Collections;

/**
 * 抖音下单Demo
 * 演示如何使用抖音下单API
 */
public class DouyinOrderDemo {
    
    public static void main(String[] args) {
        // 构建下单请求参数
        RequestOrderDTO requestOrderDTO = buildRequestOrderDTO();
        
        // 配置参数（实际使用时应该从配置文件读取）
        String appId = DYConstants.appId;
        String privateKeyStr = DYConstants.privateKeyStr;
        String keyVersion = "2";
        
        try {
            // 使用工具类生成签名
            ByteAuthorizationDTO result = DouyinPayUtil.generateOrderSignature(
                requestOrderDTO, appId, privateKeyStr, keyVersion
            );
            
            // 输出结果
            System.out.println("=== 抖音下单签名生成成功 ===");
            System.out.println("AppId: " + result.getAppId());
            System.out.println("NonceStr: " + result.getNonceStr());
            System.out.println("Timestamp: " + result.getTimestamp());
            System.out.println("KeyVersion: " + result.getKeyVersion());
            System.out.println("Signature: " + result.getSignature());
            System.out.println("Data: " + result.getData());
            System.out.println("ByteAuthorization: " + result.getByteAuthorization());

        } catch (Exception e) {
            System.err.println("生成抖音下单签名失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 构建下单请求参数
     * 
     * @return 下单请求参数
     */
    private static RequestOrderDTO buildRequestOrderDTO() {
        RequestOrderDTO requestOrderDTO = new RequestOrderDTO();

        String tradeNo = TradeNoUtil.getTradeNo(149);
//        requestOrderDTO.setIndustry("交通出行-交通出行-停车");
        // 设置外部订单号
        requestOrderDTO.setOutOrderNo(tradeNo);

        // 设置订单总金额（单位：分）
        requestOrderDTO.setTotalAmount(2L);
        
        // 设置支付超时时间（300秒）
        requestOrderDTO.setPayExpireSeconds(300);
        
        // 设置支付结果通知地址
        requestOrderDTO.setPayNotifyUrl("https://beta.bolink.club/unionapi/douyin/notify");
        requestOrderDTO.setMerchantUid("75542533284907768660");

        // 设置商品信息
        RequestOrderDTO.SkuInfo skuInfo = new RequestOrderDTO.SkuInfo();
        skuInfo.setSkuId("1");
        skuInfo.setPrice(2L);
        skuInfo.setQuantity(1);
        skuInfo.setTitle("曾天劭自动化进件1753340831");
        skuInfo.setImageList(Collections.singletonList("https://image.bolink.com/yima/parking-icon.png"));
        skuInfo.setType(703); // 虚拟商品
        skuInfo.setTagGroupId("tag_group_7443548955339669558");
        
        requestOrderDTO.setSkuList(Collections.singletonList(skuInfo));
        
        // 设置订单详情页
        RequestOrderDTO.OrderEntrySchema orderEntrySchema = new RequestOrderDTO.OrderEntrySchema();
        orderEntrySchema.setPath("subpackages/airport-parking/order-detail/index");
        JSONObject params = new JSONObject();
        params.put("orderId", tradeNo);
        params.put("fromPayment", true);  // 字符串类型，不是布尔值
        orderEntrySchema.setParams(JSON.toJSONString(params));
        
        requestOrderDTO.setOrderEntrySchema(orderEntrySchema);
        
        // 设置屏蔽的支付方式：[1] 表示屏蔽微信
        requestOrderDTO.setLimitPayWayList(Collections.emptyList());
        
        return requestOrderDTO;
    }
}




