package com.zgyz.callback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pfpj.sm.SM2Utils;
import com.pfpj.sm.SM4Utils;
import com.pfpj.sm.Signature;
import com.zgyz.common.OpenApiRequest;
import com.zgyz.common.OpenApiResponse;
import com.zgyz.util.SMTool;
import com.zgyz.util.SMUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author demoAuthor
 * @Description 回调ApiDemo 响应无head body
 * @Version V2.0.3
 * @Notice 服开 -> 合作方
 */
public class CallBackDemo {

    private static final Logger logger = LoggerFactory.getLogger(CallBackDemo.class);
    static String fmerchantId = "tradeGroupPre001";
    static String fappID = "1095757516090363904001";
    static String fsopPublicKey = "040485CEFE14C7AF854C66D5279239E88F2E8B881C3EB1B393003D2B9F09E7064447C1A3615875B05A9164F7F637151F115B89E70DFCCD0C25CF83268E21576921";
    static String fprivateKey = "009378BDB7262E282910AAE680E0A83EE30EA2AB8D01E41FE880583D1DA512C51E";
    static String fpublicKey = "04F0F770FDD6E188E31A27A84AC9D6D820D33CF6088A78B305C948A6D98479AC3E71D0AF0356D3C93229C27C1B345B4110DEFBF86885876977573468063EFD8F4F";

    public static void main(String[] args) throws IOException {
        // 1.读取resource中指定环境商户信息
        Properties properties = new Properties();
        // 获取定版/预演/沙箱/生产环境测试商户信息 默认为定版环境
        //FileInputStream fis = new FileInputStream("resource/sit_config.properties");
        FileInputStream fis = new FileInputStream("/Users/xugan/work/project/ESDemo/src/main/resources/t1_config.properties");
        //FileInputStream fis = new FileInputStream("resource/sandbox_config.properties");
        //FileInputStream fis = new FileInputStream("resource/prod_config.properties");
        properties.load(fis);
        // 合作方编号
        String merchantId = properties.getProperty("merchantId");
        // 合作方私钥
        String privateKey = properties.getProperty("privateKey");
        // 合作方公钥
        String publicKey = properties.getProperty("publicKey");
        // 服开与合作方配对公钥
        String sopPublicKey = properties.getProperty("sopPublicKey");
        if (true) {
            merchantId = fmerchantId;
            privateKey = fprivateKey;
            publicKey = fpublicKey;
            sopPublicKey = fsopPublicKey;
        }
        // 接到的服开请求密文
//        String respJson = "{\"accessToken\":\"\",\"encryptKey\":\"04E8937DAE5E11AC31EC89C6FBA801661A36B5B72CC315F0DE91AE7690DD799A23788C1208586851EC199C47158ACCFB5E8BB3A1B5DEAAF338A004C75D99C2F38ADE698F55460AB8293941CA765F907EF02729D6A6D123F7113A957AB5EEA0861D3C23CA61771E1E0E711B751D357707C1\",\"request\":\"xteGMenTcToroYMLCEhlLB8+MEhXY7NjrcaArJoa2DCcBt7eXqxjzyZtD3l9oFJftl60aApxNiQC\\nytQb0cFNj3JJWMOtGyg54yeLDqFsXjUP5MqON5Yr3/gzABvB8j7nwKTpjzmCHtJ3PbxLLALGL1Nb\\nVJbfe+2PYYLulf00qNZMX+w2MAjropPCeq1Ng//LhD3WQd4ZvEtyRGX2SdOp0yWUKdS87OmlrGyY\\nctvmXmDS3o7KdK9f6FIzbw22K7bWuQZVfLsxpDNUo+CMVBaBnhMl+SPNsICeVzqGLYmLozWwRtm8\\n2YKurppn7y8aEGiP72H1aSb4iY/tMUzMqHVHEzmufeAIe0HIJ/26WzWlBboBcqCfBM4S0DonleSv\\nMgr9zOTe2d8mxPmOZbni7tB3IQ==\",\"signature\":\"5bd01ba68c0f9cfff071519e88b527a91f0893f8ef5bfef5962a8201840394bd#4b1959dc8d3447f3d631f4a5b49d26fdf5e462d94ff2136c04fe0af600fbfa09\"}"; // 解析接到的请求
        String respJson = "{\"request\":\"pLUSOEkCEvxNDhezL5VTKBJ/EmapcJG3iICyeDUttXln2T9tw4KolQUSKs/unhqR8g8vwYzNYwFb\\nWoIqH6WVHgv907kRYwruPuYgzA3mA8la3vUGoJvUC898IBC78HQ6ia7BRekkXUzfzivxupUPsTg1\\ngUyOqy7VOtjZuLjwfYTkra5BkG2XmfOW6LIqJ329y0Dub0bhGoXHsjg/fL7FuRT4ptWyonhsLz1G\\npcGP+V5Ya5RlitxRwqUhuEivYVYvtOtWHB2QGAHj0cNU3XWUm/lwwM8Tu+n4+BY9Ef3aVMSC2/aO\\n19+zHprqcg1d6K1loeAao2JB2s0va2qoxGEi4I9MTOa4m+1Cpf0Cc3PSqthsPKqS26WM5WuDcOIn\\n4B2eFyUZ7zlC01J4GI4AfhTOvLa3NUjMGq/9gNAWaYtAVUcFx8hyev1ioq8eX/iY68OYWDuzdrNn\\ns6Z/Si9uF/ExaFgImxd5LHTfCrOO7qu3IWCEq5ZulVX6Bi3Q/Oo5u5KIMX6WXjrm4xPDxiBnDrRh\\nCIh0KEgDluB24whytEs2jMsHuO5ZTK2XH3Qi3c+JCQiLgB/upkmhjmyEFPCyIrVVV8BmpuOC1NPC\\nhByIVT6gtyHgVGHNKdNFjh8aLL10OVRYBa9E4tOfElULjUs+qBj4h0xanqFzAjqhPJsWT5Aw4Ygl\\nevHJ1K2jj+Rx4fFrWHT8LvDf+NPH6dHc7n5/XUmavVjr3QV7ahAAO55Vzx9s2pp1toRHIWViTlvQ\\nmTsQJ9h8AbG8s/ExMQmfKXb/hKl7XTU4FyJgGprNHcNkvvZi3f/CXGzNrxZcesEGvzIr353L1pXm\\nqz9+Zs7gRfsquunBpXQnOO7UtGwOUl1Owx19K0dq3E9W4dbyvGxnG0cERk/IXoSgC0EcnuDhDZcw\\n0kTuj4RMNUALbfLrLjkm0mrPkhvgj0YqpPZWV/LZgh05UuG1/ywcCLqcLQmgkuNkKrJptZHQCtdd\\n8c9L1ggn19kc2VaEgo14Cr7RdmneFtkqZlZ6HVdMmOHiJ9J4VyRhuGoYjkk78u+oEUbfnMBQt0f8\\nVg7F3fJo3KjQ7gzDKMhNJDdPZG+21UkTlQfKKz5M/GVFs1LgxyH2sOiTV3UiWh0TfDyGjYg6rtJy\\nkiVwWe7z6iAoZW0Ug0CN92Bt4d/+VHLGSpN5OAR5icWH+PM4WiuI23tk81wdkST2CdJO3HNLxHga\\nydxSKzVD/oqhhU9fSmlG09xUpRveJ7LDUA83ms3Llwz/9phnLavx5ruxbhXI3PhhRYw92fx1kAwu\\nTfG/Wc3owrilEFhz4W1Mkc42uhska+sYPPweCzpJgKI2ewGs8Jk2Y2osF/jN1YXoPyONyQwB4/Fo\\nwCk5B06MiBTzps4KcIM4C7FZRIrrY+8x0DqbTSmvsbrIp0eLa90KqEhADOBiEOQHL+ScPwoS0Job\\n6T4HaKJjpxFjS3zqFtvvjGYlDasCjIa4gxLFRVhFDgYfALlI2jiRB3K4ytxmuP2ibHuVi6STHmeV\\nnIKUqrxNsk5K6aUZOKRvMfumtd8LRYPwGZX+Nfp/y2TdOhkbOa19WjJEUHWquSr+MZtyrcbWJrLn\\nMcfj/e0jKA1RtcuGzo9GrcCthhzt2Obg+t28cSMmijJgnOJtCeOJInaEIUkrLnJMoLHinB2mp0qs\\nYh6eQ1ZYtTqBYxs9KhJTZhrx1eH39Zy5vqphJP9uLChxuJXUfL2/axeJH5SIHZeNQTFA7yhPGUEt\\nsRkm0b9xuldTowlt3dZ4ZZkZcfRZaCkGidP4UnjbZY80fwDa1lf8hr0gV+oirXFp3HG6TE/R5n01\\nAMbhijn7hkhj4dOpR+oqw6bypr2zb+yFsuGfwqKl+6te2TVEmH5BpmOhVZv+3+1TnRXQduYEMtQ=\",\"signature\":\"e621b215428516946214248c2ccf1b13f18486711991adee76eadda9fc423577#d8993b4ad502bce42e329f7fbf096fa84347d6da3eb2c8cd79eeb01dac9329cd\",\"encryptKey\":\"04C355E48BEE544C1868C9E88D96C043AB45AD6B9E9FA871CAA4BBD0C4E9301BF6CAF2031DA4C3BCFBAE1E17F861A4C66D7DEFCE640401DFF9E6FDA64CDA3CBFEEC7DEF60549D111B362C5D32114995D6D0E5798B33C04B2CBF2F6DDC8A4683DBCE0B072C2C963D1487B24EE715879B5CD\"}"; // 解析接到的请求
//        String respJson = "";
        //解密请求
        String reqMsg = requestDecrypt(merchantId, privateKey, sopPublicKey, respJson);
        JSONObject responseJson = new JSONObject();
        //解析报文失败时
        if (StringUtils.isEmpty(reqMsg)) {
            responseJson.put("respCode", "900010");
            responseJson.put("respMsg", "报文解析异常");
            //加密响应报文
            responseEncrypt(merchantId, privateKey, publicKey, sopPublicKey, responseJson);
            return;
        }
        //构造响应报文
        JSONObject requestJsonObj = JSONObject.parseObject(reqMsg);
        String transSeq = (String) requestJsonObj.get("transSeq");
        responseJson.put("respCode", "000000");
        responseJson.put("respMsg", "交易成功");
        responseJson.put("transSeq", transSeq);
        // 加密响应
        OpenApiResponse response = responseEncrypt(merchantId, privateKey, publicKey, sopPublicKey, responseJson);
        logger.info("加密响应报文response: {}", JSONObject.toJSONString(response));
    }

    /**
     * 加密响应报文
     *
     * @param merchantId   合作方编号
     * @param privateKey   合作方私钥
     * @param publicKey    合作方公钥
     * @param sopPublicKey 服开与合作方配对公钥
     * @param responseJson 响应报文
     * @return OpenApiResponse 加密响应对象
     * @throws IOException
     */
    private static OpenApiResponse responseEncrypt(String merchantId, String privateKey, String publicKey, String sopPublicKey, JSONObject responseJson) throws IOException {
        OpenApiResponse openApiResponse = new OpenApiResponse();
        String orignRespJsonStr = JSON.toJSONString(responseJson);
        logger.debug("原始响应报文: {}", orignRespJsonStr);
        String sm4Key = SMUtil.getSM4Key();
        String encryptResponse = SM4Utils.encrypt(orignRespJsonStr, "CBC", sm4Key, "");
        openApiResponse.setResponse(encryptResponse);
        // 加密sm4密钥
        SM2Utils sm2Utils = new SM2Utils();
        String encryptKey = sm2Utils.encrypt(sopPublicKey, sm4Key);
        openApiResponse.setEncryptKey(encryptKey);
        openApiResponse.setAccessToken("");
        // 计算签名
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.defaultString(openApiResponse.getResponse(), ""));
        sb.append(StringUtils.defaultString(openApiResponse.getEncryptKey(), ""));
        sb.append(StringUtils.defaultString(openApiResponse.getAccessToken(), ""));
        Signature sign = sm2Utils.sign(merchantId, privateKey, sb.toString(), publicKey);
        String signature = SMUtil.toSignStr(sign);
        openApiResponse.setSignature(signature);
        logger.info(signature);
        logger.debug("加密响应报文: {}", JSON.toJSONString(openApiResponse));
        return openApiResponse;
    }

    /**
     * 解密服开的请求
     *
     * @param merchantId   合作方编号
     * @param privateKey   合作方私钥
     * @param sopPublicKey 服开与合作方配对公钥
     * @param respJson     接到的服开请求密文
     * @return 接到的服开请求报文明文
     * @throws IOException
     */
    private static String requestDecrypt(String merchantId, String privateKey, String sopPublicKey, String respJson) throws IOException {
        OpenApiRequest openApiRequest = JSON.parseObject(respJson, OpenApiRequest.class);
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.defaultString(openApiRequest.getRequest(), ""));
        sb.append(StringUtils.defaultString(openApiRequest.getEncryptKey(), ""));
        sb.append(StringUtils.defaultString(openApiRequest.getAccessToken(), ""));
        logger.info("解析得到的请求密文：{}", sb);
        // 验签
        SM2Utils sm2Utils = new SM2Utils();
        boolean checked = sm2Utils.verifySign(merchantId, sopPublicKey, sb.toString(), SMTool.fromString(openApiRequest.getSignature()));
        String respMsg = null;
        if (checked) {
            logger.info("验签成功");
            //解析密钥
            String sm4Key = sm2Utils.decrypt(privateKey, openApiRequest.getEncryptKey());
            logger.info("请求报文 sm4 密钥:{}", sm4Key);
            //解析报文
            respMsg = SM4Utils.decrypt(openApiRequest.getRequest(), "CBC", sm4Key, "");
            logger.info("请求报文明文：{}", respMsg);
        } else {
            logger.info("验签失败");
        }
        return respMsg;
    }

}
