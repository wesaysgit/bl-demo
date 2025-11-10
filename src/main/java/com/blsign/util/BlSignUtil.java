package com.blsign.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlSignUtil {

    public static String createSign(String data, Long unionId, String key) {
        if(StrUtil.isEmpty(key)){
            log.error("厂商平台编号" + unionId + "无效或不存在");
            return null;
        }
        String sign = DigestUtil.md5Hex(data + "key=" + key, "utf-8").toUpperCase();
        log.info("生成签名值>>>unionId:"+unionId+"; 计算sign:"+ sign);
        return sign;
    }

}
