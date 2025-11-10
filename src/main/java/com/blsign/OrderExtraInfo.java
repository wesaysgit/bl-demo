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
    private static final Long unionId = 201018L;
    private static final String uKey = "06BE6FCB92A64961";

    public static void main(String[] args) {

        try {
            RequestParamDTO<UnionOrderExtraInfoReqDTO> paramDTO = new RequestParamDTO<>();
            paramDTO.setUnionId(unionId);

            UnionOrderExtraInfoReqDTO reqDTO = new UnionOrderExtraInfoReqDTO();
            reqDTO.setOutTradeNo("2120250821095241669809315625");
            reqDTO.setPayTime(1755741161L);

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
