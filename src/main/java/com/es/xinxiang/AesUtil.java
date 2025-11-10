package com.es.xinxiang;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author lb
 */
public class AesUtil {
	private static String ivParameter = "abghtrkilpggdefg";
	private static String encodingFormat = "utf-8";



	/**
	 * 加密-CBC模式
	 *
	 * @param sSrc
	 *            待加密字符串
	 * @param sKey
	 *            私钥
	 * @return 加密结果字符串
	 * @throws Exception
	 *             异常
	 */
	public static String encrypt(String sSrc, String sKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] raw = sKey.getBytes("ASCII");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
		return  Base64.encodeBase64String(encrypted);
	}

	/**
	 * 解密-CBC模式
	 *
	 * @param sSrc
	 *            待解密字符串
	 * @param sKey
	 *            私钥
	 * @return 解密结果字符串
	 * @throws Exception
	 *             异常信息
	 */
	public static String decrypt(String sSrc, String sKey) throws Exception {
		try {
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new Base64().decode(sSrc);
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, encodingFormat);
			return originalString;
		} catch (Exception ex) {
			throw new Exception("decrypt异常",ex);
		}
	}
}
