package com.douyin.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.douyin.dto.ByteAuthorizationDTO;
import com.douyin.dto.RequestOrderDTO;
import com.douyin.openapi.client.Client;
import com.douyin.openapi.client.models.*;
import com.douyin.openapi.credential.models.Config;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.UUID;

/**
 * 抖音支付工具类
 * 提供抖音支付相关的工具方法
 */
public class DouyinPayUtil {

    private static final Logger logger = Logger.getLogger(DouyinPayUtil.class);

    /**
     * 获取应用授权调用凭证
     */
    public static String clientToken(String appId, String appSecret) {
        try {
            Config config = new Config().setClientKey(appId)
                    .setClientSecret(appSecret);
            Client client = new Client(config);
            OauthClientTokenRequest sdkRequest = new OauthClientTokenRequest();
            sdkRequest.setClientKey(appId);
            sdkRequest.setClientSecret(appSecret);
            sdkRequest.setGrantType("client_credential");
            OauthClientTokenResponse sdkResponse = client.OauthClientToken(sdkRequest);
            OauthClientTokenResponseData data = sdkResponse.getData();
            return data.getAccessToken();
        } catch (Exception e) {
            logger.error("获取应用授权调用凭证异常", e);
        }
        return "";
    }

    /**
     * 查询订单信息
     *
     * @param appId         应用ID
     * @param privateKeyStr 应用私钥
     * @return 查询结果
     */
    public static DeveloperOrderQueryResponse queryOrder(String appId, String privateKeyStr,String outOrderNo, String accessToken) {
        try {
            if (!StrUtil.isAllNotBlank(appId, privateKeyStr, outOrderNo, accessToken)) {
                logger.info(outOrderNo + "订单查询参数校验失败");
                return null;
            }
            Config config = new Config().setClientKey(appId)
                    .setClientSecret(privateKeyStr);
            Client client = new Client(config);

            DeveloperOrderQueryRequest queryRequest = new DeveloperOrderQueryRequest();
            queryRequest.setAccessToken(accessToken);
            queryRequest.setOutOrderNo(outOrderNo);

            DeveloperOrderQueryResponse queryResponse = client.DeveloperOrderQuery(queryRequest);
            logger.info(outOrderNo + "订单查询返回: " + JSON.toJSONString(queryResponse));
            return queryResponse;
        } catch (Exception e) {
            logger.error("查询订单信息失败", e);
            return null;
        }
    }


    /**
     * 发起退款
     */
    public static DeveloperRefundCreateResponse createRefund(String appId, String privateKeyStr, String outOrderNo,
                                                             String refundReason, String accessToken, long refundAmountInCents,
                                                             String refundTradeNo) {
        try {
            Config config = new Config().setClientKey(appId)
                    .setClientSecret(privateKeyStr);
            Client client = new Client(config);

            // 创建退款请求
            DeveloperRefundCreateRequest refundRequest = new DeveloperRefundCreateRequest();
            refundRequest.setAccessToken(accessToken);
            refundRequest.setOutRefundNo(refundTradeNo);
            refundRequest.setOrderId("motb75599777630635481875109");
            refundRequest.setRefundAll(false);

            // 设置订单入口Schema
            DeveloperRefundCreateRequestOrderEntrySchema entrySchema = new DeveloperRefundCreateRequestOrderEntrySchema();
            entrySchema.setPath("subpackages/airport-parking/order-detail/index");
            refundRequest.setOrderEntrySchema(entrySchema);

            // 设置退款原因
            DeveloperRefundCreateRequestRefundReasonItem reasonItem = new DeveloperRefundCreateRequestRefundReasonItem();
            reasonItem.setCode(101L);
            reasonItem.setText(refundReason);
            refundRequest.setRefundReason(Collections.singletonList(reasonItem));
            refundRequest.setRefundTotalAmount(refundAmountInCents);

            DeveloperRefundCreateRequestItemOrderDetailItem itemOrderDetailItem = new DeveloperRefundCreateRequestItemOrderDetailItem();
            itemOrderDetailItem.setItemOrderId("motb75599777630635809555109");
            itemOrderDetailItem.setRefundAmount(refundAmountInCents);
            refundRequest.setItemOrderDetail(Collections.singletonList(itemOrderDetailItem));

            // 调用退款接口
            DeveloperRefundCreateResponse refundResponse = client.DeveloperRefundCreate(refundRequest);


            logger.info(outOrderNo + "退款响应: " + JSON.toJSONString(refundResponse));

            // 7. 解析响应
            return refundResponse;

        } catch (Exception e) {
            logger.error(outOrderNo + "发起退款异常", e);
        }
        return null;
    }

    /**
     * 生成下单参数与签名
     *
     * @param requestOrderDTO 下单请求参数
     * @param appId           应用appid
     * @param privateKeyStr   应用私钥
     * @param keyVersion      公钥版本
     * @return 包含签名的授权信息
     */
    public static ByteAuthorizationDTO generateOrderSignature(RequestOrderDTO requestOrderDTO,
                                                              String appId,
                                                              String privateKeyStr,
                                                              String keyVersion) {
        try {
            if (!validateRequestOrder(requestOrderDTO)) {
                logger.info("参数校验失败" + JSON.toJSONString(requestOrderDTO));
                return null;
            }
            // 1. 生成请求参数JSON
            String data = JSON.toJSONString(requestOrderDTO);
//            System.out.println("生成的data参数: " + data);
//            String escaped = JSON.toJSONString(data);
//            String dataStr = escaped.substring(1, escaped.length() - 1);
//            logger.info("生成的data参数: " + dataStr);
//            System.out.println("生成的data参数转义: " + dataStr);

            // 2. 生成随机字符串
            String nonceStr = generateNonceStr();

            // 3. 生成时间戳
            long timestamp = generateTimestamp();

            // 4. 生成签名
            String signature = DouyinSignUtil.generateSignature(
                    privateKeyStr, "POST", "/requestOrder", timestamp, nonceStr, data
            );

            // 5. 构造byteAuthorization
            ByteAuthorizationDTO byteAuthorizationDTO = new ByteAuthorizationDTO();
            byteAuthorizationDTO.setAppId(appId);
            byteAuthorizationDTO.setNonceStr(nonceStr);
            byteAuthorizationDTO.setTimestamp(timestamp);
            byteAuthorizationDTO.setKeyVersion(keyVersion);
            byteAuthorizationDTO.setSignature(signature);
            byteAuthorizationDTO.setData(data);

            // 6. 生成完整的byteAuthorization字符串
            String byteAuthorization = DouyinSignUtil.generateByteAuthorization(
                    appId, nonceStr, timestamp, keyVersion, signature
            );
            byteAuthorizationDTO.setByteAuthorization(byteAuthorization);

            logger.info("生成的byteAuthorization: " + byteAuthorization);
            return byteAuthorizationDTO;

        } catch (Exception e) {
            logger.error("生成抖音下单签名失败", e);
        }
        return null;
    }

    /**
     * 生成随机字符串
     *
     * @return 随机字符串
     */
    private static String generateNonceStr() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成时间戳（秒）
     *
     * @return 时间戳
     */
    private static long generateTimestamp() {
        return System.currentTimeMillis() / 1000L;
    }

    /**
     * 验证请求参数
     *
     * @param requestOrderDTO 请求参数
     * @return 验证结果
     */
    public static boolean validateRequestOrder(RequestOrderDTO requestOrderDTO) {
        if (requestOrderDTO == null) {
            logger.error("请求参数不能为空");
            return false;
        }

        if (requestOrderDTO.getOutOrderNo() == null || requestOrderDTO.getOutOrderNo().trim().isEmpty()) {
            logger.error("外部订单号不能为空");
            return false;
        }

        if (requestOrderDTO.getTotalAmount() == null || requestOrderDTO.getTotalAmount() <= 0) {
            logger.error("订单总金额必须大于0");
            return false;
        }

        if (requestOrderDTO.getSkuList() == null || requestOrderDTO.getSkuList().isEmpty()) {
            logger.error("商品列表不能为空");
            return false;
        }

        // 验证商品信息
        for (RequestOrderDTO.SkuInfo skuInfo : requestOrderDTO.getSkuList()) {
            if (skuInfo.getSkuId() == null || skuInfo.getSkuId().trim().isEmpty()) {
                logger.error("商品ID不能为空");
                return false;
            }
            if (skuInfo.getPrice() == null || skuInfo.getPrice() <= 0) {
                logger.error("商品价格必须大于0");
                return false;
            }
            if (skuInfo.getQuantity() == null || skuInfo.getQuantity() <= 0 || skuInfo.getQuantity() > 100) {
                logger.error("商品数量必须在1-100之间");
                return false;
            }
        }

        return true;
    }

    /**
     * 格式化金额（分转元）
     *
     * @param amountInCents 金额（分）
     * @return 金额（元）
     */
    public static String formatAmount(Long amountInCents) {
        if (amountInCents == null) {
            return "0.00";
        }
        return String.format("%.2f", amountInCents / 100.0);
    }

    /**
     * 格式化金额（元转分）
     *
     * @return 金额（分）
     */
    public static Long parseAmount(double amount) {
        try {
            return Math.round(amount * 100);
        } catch (NumberFormatException e) {
            logger.error("金额格式错误: " + amount);
            return 0L;
        }
    }

}
