package com.es.bolink;


import com.es.BASE64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class DesUtils {

	//密钥
	private static final String PASSWORD_CRYPT_KEY ="tingchebao_push_interface";// Defind.getProperty("DESCPASS");
	//偏移量
	private static final String IV ="bolinkad";//Defind.getProperty("DES_IV");// "309VvJzn";
	private static final String DES = "DES/CBC/PKCS5Padding";


	/**
	 * 加密
	 * @param message
	 * @return
	 * @throws Exception
	 */

	public static String encrypt(String message) throws Exception {
		byte [] betys =null;
		DESKeySpec dks = new DESKeySpec(PASSWORD_CRYPT_KEY.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
		betys = cipher.doFinal(message.getBytes("utf-8"));
		return new String(BASE64.encode(betys));

	}

	/**
	 * 解密
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String value) throws Exception {
		DESKeySpec dks = new DESKeySpec(PASSWORD_CRYPT_KEY.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
		return new String(cipher.doFinal(BASE64.decode(value)),"utf-8");
	}

	/**
	 * 加密
	 * @param message
	 * @return
	 * @throws Exception
	 */

	public static String encrypt(String message,String key) throws Exception {
		byte [] betys =null;
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		IvParameterSpec iv = new IvParameterSpec(key.substring(0,8).getBytes());
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.ENCRYPT_MODE, securekey,iv);
		betys = cipher.doFinal(message.getBytes("utf-8"));
		return new String(BASE64.encode(betys));

	}

	/**
	 * 加密
	 * @param message
	 * @return
	 * @throws Exception
	 */

	public static String base64(String message) throws Exception {
		byte [] betys =message.getBytes("utf-8");
		return new String(BASE64.encode(betys));

	}

	/**
	 * 解密
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String value,String key) throws Exception {
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		IvParameterSpec iv = new IvParameterSpec(key.substring(0,8).getBytes());
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
		return new String(cipher.doFinal(BASE64.decode(value)),"utf-8");
	}


//	public static void main(String[] args) {
//		try {
//			String localId = "28b2bd519505_2.1.3.0_gg1_channels_A1_A2_B3_出口D3_出口D-B3_11_syncdata";
//			int index = StringUtils.getFirstCharAt(localId,"_");
//			String _localId = localId.substring(0,index);
//			String prelocalId = localId.substring(index+1);
//			_localId += prelocalId.substring(StringUtils.getFirstCharAt(prelocalId,"_"));
//
//			String tokenLocalId = "28b2bd519505_2.1.4.0_gg1_channels_A1_A2_B3_出口D3_出口D-B3_11_syncdata";
//			int tokenIndex = StringUtils.getFirstCharAt(tokenLocalId,"_");
//			String _tokenLocalId = tokenLocalId.substring(0,tokenIndex);
//			tokenLocalId = tokenLocalId.substring(tokenIndex+1);
//			_tokenLocalId +=tokenLocalId.substring(StringUtils.getFirstCharAt(tokenLocalId,"_"));
//			//28b2bd519505_2.1.3.0_gg1_channels_A1_A2_B3_出口D3_出口D-B3_11_syncdata,
//			//28b2bd519505_dudu1_lalaa_出口-B3_11_syncdata,是否匹配：false
//			System.out.println("queryprice check token>>>orderlocalId:"+_localId+",tokenlocalId:"+_tokenLocalId+",是否匹配："+(_localId.equals(_tokenLocalId)));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
