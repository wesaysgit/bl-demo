package com.es.lsapp.dto;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;


public class AES256 {

    public static final String CHARSET = "UTF-8";
    public static final String SIGN_ALGORITHM = "SHA1withRSA";
    public static final String FORM_ALGORITHM = "AES";
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";
    public static boolean initialized = false;
    public static Logger log = Logger.getLogger(AES256.class);



    /**
     * 私钥签名
     *
     * @param privateKey
     *            私钥
     * @param text
     *            加密的文本
     * @return
     * @throws Exception
     */
    public final static String Sign(final PrivateKey privateKey, final String text) throws Exception {
        Signature sign = Signature.getInstance(SIGN_ALGORITHM);
        sign.initSign(privateKey);
        sign.update(text.getBytes(CHARSET));
        String signStr = Base64.encodeBase64String(sign.sign());
        return signStr;
    }

    /**
     * 公钥验签
     *
     * @param publicKey
     *            公钥
     * @param text
     *            加密的文本
     * @param signStr
     *            签名
     * @return
     * @throws Exception
     */
    public final static boolean Verify(final PublicKey publicKey, final String text, final String signStr)
            throws Exception {
        Signature verifySign = Signature.getInstance(SIGN_ALGORITHM);
        verifySign.initVerify(publicKey);
        verifySign.update(text.getBytes(CHARSET));
        return verifySign.verify(Base64.decodeBase64(signStr.getBytes(CHARSET)));
    }

    /**
     * AES256加密

     *            key 加/解密要用的256位密钥
     * @return Base64后的加密文本
     */
    public final static String Aes256Encode(final String text, final String key) throws Exception {
        initialize();
        byte[] result = null;
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(CHARSET), "AES"); // 生成加密解密需要的Key
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        result = cipher.doFinal(text.getBytes(CHARSET));
        return Base64.encodeBase64String(result);
    }

    public static String stringToHexString(String jsonData) {
        int num = jsonData.length();
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int ch = (int) jsonData.charAt(i);
            str.append(Integer.toHexString(ch));
        }
        return str.toString();
    }

    /**
     * @Description 16进制字符串转换为字符串
     * @param jsonData
     * @return
     * @time 2018年1月24日 上午9:41:40
     * @author zhouxy
     */
    public static String hexStringToString(String jsonData) {
        if (StringUtils.isEmpty(jsonData)) {
            return null;
        }
        jsonData = jsonData.replace(" ", "");
        byte[] baKeyword = new byte[jsonData.length() / 2];
        try {
            for (int i = 0; i < baKeyword.length; i++) {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(jsonData.substring(i * 2, i * 2 + 2), 16));
            }
            jsonData = new String(baKeyword, "gbk");
        } catch (Exception e) {
            log.error("16进制字符串转换为字符串 出错!", e);
            throw new RuntimeException("16进制字符串转换为字符串 出错!");
        }
        return jsonData;
    }

    /**
     * AES256解密
     *
     * @param txet
     *            需要解密的Base64文本
     * @param key
     *            加/解密要用的256位密钥
     * @return
     * @throws Exception
     */
    public final static String Aes256Decode(final String txet, final String key) throws Exception {
        initialize();
        String result = null;
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(CHARSET), "AES"); // 生成加密解密需要的Key
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decoded = cipher.doFinal(Base64.decodeBase64(txet.getBytes(CHARSET)));
        result = new String(decoded, CHARSET);
        return result;
    }

    private static void initialize() {
        if (initialized)
            return;
        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }
}
