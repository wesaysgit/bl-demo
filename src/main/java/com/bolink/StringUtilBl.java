package com.bolink;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtilBl {

	/** 签名拼接秘钥参数key */
	public static final String SIGN_SECRET_NAME_KEY = "&key=";

	public static void main(String[] args) {
	}

	public static boolean isNotNull(String value) {
		if (value == null || value.equals("")) {
			return false;
		}
		return true;
	}



	public static String intArrayToString(int[] values){
		if(values!=null){
			String ret = "";
			for(int i : values){
				ret +=i+",";

			}
			return ret.length()>0?ret.substring(0,ret.length()-1):ret;
		}
		return "";
	}


	/**
	 * 生成 xml文件流
	 */
	public static String createXML(Map<String, Object> info) {// 获取最大访客数XML
		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
		xml.append("<content>");
		for (String key : info.keySet()) {
			xml.append("<" + key + ">" + info.get(key) + "</" + key + ">");
		}
		xml.append("</content>");
		return xml.toString();
	}

	/**
	 * @Description：将请求参数转换为xml格式的string
	 * @param parameters  请求参数
	 * @return
	 */
	public static String getRequestXml(SortedMap<Object,Object> parameters){
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
				sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
			}else {
				sb.append("<"+k+">"+v+"</"+k+">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}




	public static String createJson(List<Map<String, Object>> info) {
		String json = "[";
		int i = 0;
		int j = 0;
		if (info != null && info.size() > 0) {
			for (Map<String, Object> map : info) {
				if (i != 0) {
					json += ",";
				}
				json += "{";
				for (String key : map.keySet()) {
					if (j != 0) {
						json += ",";
					}
					Object v = map.get(key);
					if (v != null) {
						v = v.toString().trim();
					}
					// if(v instanceof Long||v instanceof Integer)
					// json +="\""+key+"\":"+map.get(key);
					// else {
					json += "\"" + key + "\":\"" + v + "\"";
					// }
					j++;
				}
				json += "}";
				i++;
				j = 0;
			}

		}
		json += "]";
		return json;
	}

	public static String createJson3(List<Map<String, Object>> info) {
		String json = "[";
		int i = 0;
		int j = 0;
		if (info != null && info.size() > 0) {
			for (Map<String, Object> map : info) {
				if (i != 0) {
					json += ",";
				}
				json += "{";
				for (String key : map.keySet()) {
					if (j != 0) {
						json += ",";
					}
					Object v = map.get(key);
					if (v != null) {
						v = v.toString().trim();
					}
					if(key.equals("in_time") || key.equals("out_time")) {
						json +="\""+key+"\":"+map.get(key);
					} else {
						json += "\"" + key + "\":\"" + v + "\"";
					}
					j++;
				}
				json += "}";
				i++;
				j = 0;
			}

		}
		json += "]";
		return json;
	}

	public static String createJson(List<Map<String, Object>> info,
			String charset,String exculdeFiled[]) {
		String json = "[";
		int i = 0;
		int j = 0;
		if (info != null && info.size() > 0) {
			for (Map<String, Object> map : info) {
				if (i != 0) {
					json += ",";
				}
				json += "{";
				for (String key : map.keySet()) {
					boolean isExculde = false;
					if(exculdeFiled!=null){
						for(String ef : exculdeFiled){
							if(ef.equals(key)){
								isExculde=true;
								break;
							}
						}
					}
					if(isExculde) {
						continue;
					}
					if (j != 0) {
						json += ",";
					}
					Object v = map.get(key);
					v=v==null?"":v;
					if (v != null) {
						v = v.toString().trim();
					}
					json += "\"" + key + "\":\""
							+ encodeUTF8(v.toString()) + "\"";
					j++;
				}
				json += "}";
				i++;
				j = 0;
			}

		}
		json += "]";
		return json;
	}



	public static String createJson(Map<String, Object> info) {
		String json = "";
		int j = 0;
		if (info != null && info.size() > 0) {
			json += "{";
			for (String key : info.keySet()) {
				// System.out.println(key);
				if (j != 0) {
					json += ",";
				}
				Object value = info.get(key);
				if (value == null) {
					value = "";
				}
				if (value instanceof Long || value instanceof Integer || value instanceof Double) {
					json += "\"" + key + "\":" + value;
				} else {
					json += "\"" + key + "\":\"" + value+ "\"";
				}
				j++;
			}
			json += "}";
		} else {
			json = "{}";
		}
		return json;
	}

	public static String createJsonUtf8(Map<String, Object> info) {
		String json = "";
		int j = 0;
		if (info != null && info.size() > 0) {
			json += "{";
			for (String key : info.keySet()) {
				// System.out.println(key);
				if (j != 0) {
					json += ",";
				}
				Object value = info.get(key);
				if (value == null) {
					value = "";
				}
				if (value instanceof Long || value instanceof Integer || value instanceof Double) {
					json += "\"" + key + "\":" + value;
				} else {
					json += "\"" + key + "\":\"" + encodeUTF8(value+"")+ "\"";
				}
				j++;
			}
			json += "}";
		} else {
			json = "{}";
		}
		return json;
	}


	public static String createLinkString(Map<String, Object> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			Object value = params.get(key);
			if (value == null || value.toString().trim().equals("")||value.toString().trim().equals("null")) {
				continue;
			}
			prestr += key + "=" + value + "&";
		}
		if (prestr.endsWith("&")) {
			prestr = prestr.substring(0, prestr.length() - 1);
		}
		return prestr;
	}

	public static String createLinkString2(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			Object value = params.get(key);
			if (value == null || value.toString().trim().equals("")||value.toString().trim().equals("null")) {
				continue;
			}
			prestr += key + "=" + value + "&";
		}
		if (prestr.endsWith("&")) {
			prestr = prestr.substring(0, prestr.length() - 1);
		}
		return prestr;
	}

	public static String createLinkString(String data) {
		JSONObject jsonObject = JSONObject.parseObject(data);
		List<String> keys = new ArrayList<String>(jsonObject.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			Object value = jsonObject.get(key);
			if (value == null || value.toString().trim().equals("")||value.toString().trim().equals("null")) {
				continue;
			}
			prestr += key + "=" + value + "&";
		}
		if (prestr.endsWith("&")) {
			prestr = prestr.substring(0, prestr.length() - 1);
		}
		return prestr;
	}





	public static String objArry2String(Object[] values) {
		StringBuffer rBuffer = new StringBuffer();
		if (values != null && values.length > 0) {
			for (Object o : values) {
				rBuffer.append(o + ",");
			}
		}
		return rBuffer.toString();
	}


	public static List<Object> array2List(Object[] arrays) {
		if (arrays != null) {
			List<Object> list = new ArrayList<Object>();
			for (int i = 0; i < arrays.length; i++) {
				list.add(arrays[i]);
			}
			return list;
		}
		return new ArrayList<Object>();
	}


	/**
	 * 生成MD5
	 */
	public static String MD5(String s) {
		//System.err.println(s);
		try {
			MessageDigest messagedigest = MessageDigest.getInstance("MD5");
			messagedigest.reset();
			byte abyte0[] = messagedigest.digest(s.getBytes("utf-8"));
			return byteToString(abyte0);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String byteToString(byte abyte0[]) {
		int i = abyte0.length;
		char ac[] = new char[i * 2];
		int j = 0;
		for (int k = 0; k < i; k++) {
			byte byte0 = abyte0[k];
			ac[j++] = hexDigits[byte0 >>> 4 & 0xf];
			ac[j++] = hexDigits[byte0 & 0xf];
		}

		return new String(ac);
	}

	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };


	/**
	 * 解码Ajax urf-8编码后的url形式中文参数 返回UTF-8结果
	 *
	 */
	public static String decodeUTF8(String someStr) {
		String newStr = null;
		if (someStr != null && someStr.equals("")) {
			return "";
		}
		if (someStr != null && !someStr.equals("")) {
			try {
				newStr = URLDecoder.decode(someStr, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return newStr;
	}

	/**
	 * 编码Ajax urf-8编码后的url形式中文参数 返回UTF-8结果
	 *
	 */
	public static String encodeUTF8(String someStr) {
		String newStr = null;
		if (someStr != null && someStr.equals("")) {
			return "";
		}
		if (someStr != null && !someStr.equals("")) {
			try {
				newStr = URLEncoder.encode(someStr, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return newStr;
	}

	public static void ajaxOutput(HttpServletResponse response,
			String outputString)  {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST,GET");
			PrintWriter printWriter = response.getWriter();
			printWriter.write(outputString);
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 多层代理获取客户端真实IP
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip != null && !"".equals(ip)) {
			if (ip.indexOf(",") > 0) {
				ip = ip.split(",")[0];
			}
		}
		// System.out.println("Redirecting com_ip 01 ==> " + ip);
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			// System.out.println("Redirecting com_ip 02 ==> " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			// System.out.println("Redirecting com_ip 03 ==> " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			// System.out.println("Redirecting com_ip 04 ==> " + ip);
		}
		return ip;
	}


	public static String getTimeString(Integer minute) {
		if(minute==null||minute==0){
			return "";
		}
		Integer hour =  minute/ 60;
		if (hour == 0 && minute == 0) {
			minute = 0;
		}
		String result = "";
		int day = 0;
//		if (hour == 0)
//			result = minute + "分钟";
//		else
//			result = hour + "小时" + minute%60 + "分钟";
		result = hour + "小时" + minute%60 + "分钟";
		if (hour > 24) {
			day = hour / 24;
			hour = hour % 24;
			result = day + "天 " + hour + "小时" + minute%60 + "分钟";
		}
		// System.out.println(">>>>>>>>>>>>b:"+start+",e:"+end+",duration:"+result);
		return result;
	}


	public static String createXML(Map<String, Object> info,String uKey) {//
		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		xml.append("<MessageSuit version=\"v1.0.0\">");
		xml.append("<Message>");
		xml.append("<Plain>");
		StringBuffer signData = new StringBuffer();
		signData.append("<Plain>");
		for (String key : info.keySet()) {
			xml.append("<" + key + ">" + info.get(key) + "</" + key + ">");
			signData.append("<" + key + ">" + info.get(key) + "</" + key + ">");
		}
		xml.append("</Plain>");
		signData.append("</Plain>");
		String sign="";
		try {
			Base64.encodeBase64String(signData.toString().getBytes("GBK"));
			sign = Base64.encodeBase64URLSafeString(DigestUtils.sha((signData.toString()+uKey).getBytes("GBK")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//HSA1 加密,bease64转码
		xml.append("<Signature>");
		xml.append("<SignatureValue>");
		xml.append(sign);
		xml.append("</SignatureValue>");
		xml.append("</Signature>");
		xml.append("</Message>");
		xml.append("</MessageSuit>");
		return xml.toString();
	}

	public static int getFirstCharAt(String content,String sub){
		int index = 0;
		try {
			String presub="";
			while(!presub.equals(sub)){
				presub = content.charAt(index)+"";
				index++;
				if(index>=content.length()) {
					break;
				}
			}
			if(index>1) {
				return index -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return index;
	}

	public static boolean isJson(String content){
		try {
			if("".equals(content)) {
				return false;
			}
			JSONObject.parseObject(content);
			return  true;
		} catch (Exception e) {
			return false;
		}
	}
    public static Map createJson(String param) {
        Map map = new HashMap();
        if ("".equals(param)) {
            String[] params = param.split("&");
            for(String info : params){
                String[] infos = info.split("=");
                map.put(infos[0],infos[1]);
            }
        }
        return map;
    }

    public static String createWcLinkString(String data) {
        JSONObject jsonObject = JSONObject.parseObject(data);
        List<String> keys = new ArrayList<String>(jsonObject.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = jsonObject.get(key);
            if (value == null || value.toString().trim().equals("")||value.toString().trim().equals("null")) {
				continue;
			}
            if(value instanceof JSONArray){
                JSONArray jsonArray = (JSONArray) value;
                for (int j=0;j < jsonArray.size();j++){

                    JSONObject subJson = jsonArray.getJSONObject(j);

                    List<String> subKeys = new ArrayList<String>(subJson.keySet());
                    Collections.sort(subKeys);
                    for(int k=0;k<subKeys.size();k++){
                        String subKey = subKeys.get(k);
                        Object subValue = subJson.get(subKey);

                        if(subValue == null){
                            continue;
                        }

                        prestr += key+"["+j+"]."+subKey + "=" + subValue + "&";
                    }
                }
              continue;
            }
            prestr += key + "=" + value + "&";
        }
        if (prestr.endsWith("&")) {
            prestr = prestr.substring(0, prestr.length() - 1);
        }
        return prestr;
    }

//	public static void projectGBK2UTF8() {
//		//GBK编码格式源码路径
//		String srcDirPath = "D:\\ideaprojects\\tcb\\java_zldi\\src";
//		//转为UTF-8编码格式源码路径
//		String utf8DirPath = "D:\\ideaprojects\\tcb\\java_zldi\\nsrc";
//		//获取所有java文件
//		Collection<File> javaGbkFileCol = FileUtils.listFiles(new File(srcDirPath), new String[]{"java"}, true);
//		for (File javaGbkFile : javaGbkFileCol) {
//			//UTF8格式文件路径
//			String utf8FilePath = utf8DirPath + javaGbkFile.getAbsolutePath().substring(srcDirPath.length());
//			//使用GBK读取数据，然后用UTF-8写入数据
//			try {
//				FileUtils.writeLines(new File(utf8FilePath), "UTF-8", FileUtils.readLines(javaGbkFile, "GBK"));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}



//	}
	public static boolean isCarNo(String carNo){
//		Pattern p = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{1}[A-Z]{1}(?:(?![A-Z]{4})[A-Z0-9]){4,5}[A-Z0-9挂学警港澳]{1}$");
		Pattern p = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{1}[A-Z0-9]{6,7}$");
		Matcher m = p.matcher(carNo);
		if (!m.matches()) {
			return false;
		}
		return true;
	}

	public static String  Double2Str(Double value){
		BigDecimal v = BigDecimal.valueOf(value).setScale(2,BigDecimal.ROUND_HALF_UP);
		return v.toString();
	}

	public static String  Money2Min(Double value){
		BigDecimal v = BigDecimal.valueOf(value).multiply(BigDecimal.valueOf(100)).setScale(0,BigDecimal.ROUND_HALF_UP);
		return v.toString();
	}

	public static String createLinkString(JSONObject jsonObject) {
		List<String> keys = new ArrayList<String>(jsonObject.keySet());
		Collections.sort(keys);
		StringBuffer plain = new StringBuffer();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			Object value = jsonObject.get(key);
			if (value == null || value.toString().trim().equals("")||value.toString().trim().equals("null")) {
				continue;
			}
			if(value instanceof JSONArray){
				JSONArray jsonArray = (JSONArray) value;
				plain.append(key + "=[");
				for(int j=0; j<jsonArray.size(); j++) {
					plain.append("{").append(createLinkString(String.valueOf(jsonArray.get(j))));
					plain.append(j == jsonArray.size() - 1 ? "}" : "}&");
				}
				plain.append("]&");
			}else {
				plain.append(key + "=" + value + "&")  ;
			}
		}
		if (plain.toString().endsWith("&")) {
			return plain.substring(0, plain.length() - 1);
		}
		return plain.toString();
	}

	/**
	 * 根据字节长度截取字符串
	 *
	 * @param str
	 * @param length
	 * @return
	 */
	public static String subStrByByteLength(String str, int length) {

		StringBuffer sb = new StringBuffer();

		try {
			char[] array = str.toCharArray();
			for (int i = 0; i < array.length; i++) {
				sb.append(array[i]);
				if (sb.toString().getBytes("utf-8").length > length) {
					sb.deleteCharAt(sb.length() - 1);
					break;
				}

			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * unicode编码转中文
	 */
	public static String unicodeToString(String str) {

		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return str;
	}
}
