package com.blsign;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.blsign.common.RequestParamDTO;
import com.blsign.dto.UnionOrderExtraInfoReqDTO;
import com.blsign.util.BlSignUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderExtraInfo {

    private static final String url = "https://beta.bolink.club/unionapi/binternal/applet/orderExtraInfo";
//    private static final Long unionId = 201018L;
    private static final Long unionId = 200712L;
//    private static final String uKey = "06BE6FCB92A64961";
    private static final String uKey = "4F80BF35DF83858D";

    public static void main(String[] args) {

        try {
            RequestParamDTO<UnionOrderExtraInfoReqDTO> paramDTO = new RequestParamDTO<>();
            paramDTO.setUnionId(unionId);

            UnionOrderExtraInfoReqDTO reqDTO = new UnionOrderExtraInfoReqDTO();
            reqDTO.setOutTradeNo("21202602281013556783083314564");
            reqDTO.setPayTime(1772244836L);

            paramDTO.setSign(BlSignUtil.createSign(JSON.toJSONString(reqDTO, SerializerFeature.MapSortField), unionId, uKey));
            paramDTO.setData(reqDTO);

            log.info("请求参数: {}", JSON.toJSONString(paramDTO));

            String post = HttpUtil.post(url, JSON.toJSONString(paramDTO));
            
            log.info("响应结果: {}", post);

        } catch (Exception e) {
            log.error("执行过程中发生异常", e);
        }
    }
}
