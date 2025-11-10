package com.es.lsapp.sign;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LsSignResVo {

    private Integer state;

    private String msg;

    private Object data;

}
