package com.blsign.common;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口请求对象 dto
 * @author pengzhuo
 */
@Data
public class RequestParamDTO<T> implements Serializable {
    private static final long serialVersionUID = -5816688447921213389L;

    /** 入参unixTs名称key, 用于签名拼接数据 */
    public static final String TIME_PARAM_KEY = "&unixTs=";

    /** 请求体数据; (对象请转json串传递, 若需要约定base64,请先之前算sign) */
    private T data;

    /** 数据签名类型, def:MD5 */
    private String type;

    /** 数据签名 */
    private String sign;

    /** unix时间戳(秒) */
    private Long unixTs;

    /** 厂商编号 */
    private Long unionId;

    /**
     * 校验入参是否空值
     * @return 参数是否存在
     */
    public boolean checkNotEmpty(){
        return data == null || sign == null || sign.trim().isEmpty() || !StrUtil.isNotEmpty(JSON.toJSONString(data));
    }

}

