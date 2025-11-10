package com.es.bolink;

import com.es.lsapp.MD5Util;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.*;

/*
'============================================================================
'api说明：
'createSHA1Sign创建签名SHA1
'getSha1()Sha1签名
'============================================================================
'*/
@Slf4j
public class Sha1Util {



	public static String getNonceStr() {
		Random random = new Random();
		return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
	}
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	/**
	 * 创建签名SHA1
	 * @param signParams
	 * @return
	 * @throws Exception
	 */
	public static String createSHA1Sign(SortedMap<String, String> signParams) {
		try {
			StringBuffer sb = new StringBuffer();
			Set es = signParams.entrySet();
			Iterator it = es.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String k = (String) entry.getKey();
				String v = (String) entry.getValue();
				sb.append(k + "=" + v + "&");
				//要采用URLENCODER的原始值！
			}
			String params = sb.substring(0, sb.lastIndexOf("&"));
			log.info("string1>>>"+params);
			return getSha1(params);
		} catch (Exception e) {
			log.error("SHA1签名创建异常>>>"+e.getMessage());
			throw new RuntimeException("SHA1签名创建异常");
		}
	}
	//Sha1签名
	public static String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("GBK"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			StringBuilder sb = new StringBuilder(2 * j);
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				sb.append(hexDigits[byte0 >>> 4 & 0xf]).append(hexDigits[byte0 & 0xf]);
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}
}
