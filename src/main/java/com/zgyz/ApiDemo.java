package com.zgyz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pfpj.sm.SM2Utils;
import com.pfpj.sm.SM4Utils;
import com.pfpj.sm.Signature;
import com.zgyz.common.OpenApiMessage;
import com.zgyz.common.OpenApiMessageHead;
import com.zgyz.common.OpenApiRequest;
import com.zgyz.common.OpenApiResponse;
import com.zgyz.ecny.HardwareWalletOpenRequest;
import com.zgyz.util.HttpClientUtils;
import com.zgyz.util.SMUtil;
import com.zgyz.util.SerialNoUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author demoAuthor
 * @Description 普通接口请求示例
 * @Version V2.0.3
 * @Notice 合作方 -> 服开
 */
public class ApiDemo {

    private static final Logger logger = LoggerFactory.getLogger(ApiDemo.class);
    private static final String PARTNER_TX_SRI_NO = "partnerTxSriNo";

    public static void main(String[] args) {
        try {
            // 1.读取resource中指定环境商户信息
            Properties properties = new Properties();
            // 获取定版/预演/沙箱/生产环境测试商户信息 默认为定版环境
            FileInputStream fis = new FileInputStream("resource/sit_config.properties");
//            FileInputStream fis = new FileInputStream("resource/t1_config.properties");
            //FileInputStream fis = new FileInputStream("resource/sandbox_config.properties");
            //FileInputStream fis = new FileInputStream("resource/prod_config.properties");
            properties.load(fis);
            // 合作方编号
            String merchantId = properties.getProperty("merchantId");
            // appID
            String appID = properties.getProperty("appID");
            // 模块名称
            String moduleName = properties.getProperty("moduleName");
            // url
            String url = properties.getProperty("url");
            // 合作方私钥
            String privateKey = properties.getProperty("privateKey");
            // 合作方公钥
            String publicKey = properties.getProperty("publicKey");
            // 服开与合作方配对公钥
            String sopPublicKey = properties.getProperty("sopPublicKey");


            // 2.请求报文报文头
            OpenApiMessageHead msgHead = new OpenApiMessageHead();
            // 合作放交易流水号
            msgHead.setPartnerTxSriNo(SerialNoUtil.getSerialNo());
            // 接口方法 例：服开门户网站-文档中心-API文档-数字人民币-钱包-个人硬钱包开立(V1)接口方法  注：调用的接口方法改动时需要同时更改下面的请求报文报文体
            msgHead.setMethod("ecny.openHardwareWallet");
            // 接口版本号 默认为1
            msgHead.setVersion("1");
            // 合作方编号
            msgHead.setMerchantId(merchantId);
            // 接入方式 API/H5
            msgHead.setAccessType("API");
            // 服开门户网站-用户中心-我的应用-APP_ID
            msgHead.setAppID(appID);
            // 报文发起时间
            msgHead.setReqTime(SerialNoUtil.getDateTime());
            logger.debug("原始请求报文头: {}", JSON.toJSONString(msgHead));
            // 3.请求报文报文体 不同的接口请求需要构造不同的请求体 请求体字段名称参照 例：服开门户网站-文档中心-API文档-数字人民币-钱包-个人硬钱包开立(V1)请求报文部分
            HardwareWalletOpenRequest request = new HardwareWalletOpenRequest();
            request.setPhone("13544441235");
            request.setAPDURespData("000000230080000000020759273137bdbc52f06dafa969784a579ace6fb3830e929e5a58f5381008e64641203e241944ac1703d94d866a4de86eb7d1ed535b964c1400414721db5223f0a5084736323f");
            request.setDeviceName("vivo");
            request.setBusiMainId(msgHead.getPartnerTxSriNo());
            request.setReqTransTime(SerialNoUtil.getDateTime());
//            String reqstr="{\"aiContractType\":\"01\",\"busiMainId\":\"20230414101020\",\"custName\":\"拓本预演测试\",\"mobileNo\":\"13822334418\",\"redpctActName\":\"蒙牛产业有限公司\",\"redpctActNo\":\"0082022111000010\",\"reqTransTime\":\"20230414101020\",\"txnAmt\":\"CNY0.10\",\"wltId\":\"0082000000216094\"}";
            String reqstr = JSONObject.toJSONString(request);
            JSONObject requestJson = JSONObject.parseObject(reqstr);
            // 4. 请求报文对象
            OpenApiMessage<JSONObject> reqMsg = new OpenApiMessage<JSONObject>();

            reqMsg.setHead(msgHead);
            reqMsg.setBody(requestJson);

            // 5. 加密加签后的请求报文对象
            OpenApiRequest openApiRequest = new OpenApiRequest();
            // 5.1 对称加密请求报文
            String orignReqJsonStr = JSON.toJSONString(reqMsg);
            logger.debug("原始请求报文: {}", orignReqJsonStr);
            String sm4Key = SMUtil.getSM4Key();
            String encryptRequest = SM4Utils.encrypt(orignReqJsonStr, "CBC", sm4Key, "");
            openApiRequest.setRequest(encryptRequest);
            // 5.2 加密sm4密钥
            SM2Utils sm2Utils = new SM2Utils();
            String encryptKey = sm2Utils.encrypt(sopPublicKey, sm4Key);
            openApiRequest.setEncryptKey(encryptKey);
            openApiRequest.setAccessToken("");
            // 5.3 计算签名
            StringBuilder sb = new StringBuilder();
            sb.append(StringUtils.defaultString(openApiRequest.getRequest(), ""));
            sb.append(StringUtils.defaultString(openApiRequest.getEncryptKey(), ""));
            sb.append(StringUtils.defaultString(openApiRequest.getAccessToken(), ""));
            Signature sign = sm2Utils.sign(merchantId, privateKey, sb.toString(), publicKey);
            String signature = SMUtil.toSignStr(sign);
            openApiRequest.setSignature(signature);
            logger.info(signature);

            // 5.4 发起请求
            // 响应
            logger.debug("请求报文: {}", JSON.toJSONString(openApiRequest));
            // 请求url 替换配置文件中的动态配置
            url = url.replace("${moduleName}", moduleName)
                    .replace("${merchantId}", merchantId)
                    .replace("${partnerTxSriNo}", msgHead.getPartnerTxSriNo());
            String respJson = HttpClientUtils.post(url, JSON.toJSONString(openApiRequest));
            // 5.5 解析响应
            OpenApiResponse openApiResponse = JSON.parseObject(respJson, OpenApiResponse.class);
            // 验签
            sb.setLength(0);
            sb.append(StringUtils.defaultString(openApiResponse.getResponse(), ""));
            sb.append(StringUtils.defaultString(openApiResponse.getEncryptKey(), ""));
            sb.append(StringUtils.defaultString(openApiResponse.getAccessToken(), ""));
            boolean checked = sm2Utils.verifySign(merchantId, sopPublicKey, sb.toString(), SMUtil.fromString(openApiResponse.getSignature()));
            if (checked) {
                // 解析密钥
                String respSm4Key = sm2Utils.decrypt(privateKey, openApiResponse.getEncryptKey());
                logger.info("------------------------------");
                logger.debug("响应报文sm4密钥:{}", respSm4Key);
                // 解析报文
                String respMessage = SM4Utils.decrypt(openApiResponse.getResponse(), "CBC", respSm4Key, "");
                logger.debug("响应报文:{}", respMessage);
            } else {
                logger.error("响应报文验签失败!");
            }

        } catch (Exception e) {
            logger.error("发起交易异常: ", e);
        }

    }
}
