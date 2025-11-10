package com.es.tcp;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TcpHandle {
    private Logger logger = LoggerFactory.getLogger(TcpHandle.class);



    public String heartBeat(String boxNo, String clientIp) {
        JSONObject heartBeatResponse = new JSONObject();
        heartBeatResponse.put("method", "etc.heartbeat.response");
        heartBeatResponse.put("code", 1);
        heartBeatResponse.put("msg", "error");
        try {
            String result = "bolinkApiFeginClient.doEtcBeat(StringUtils.encodeUTF8(boxNo), clientIp)";
            logger.info("bolink缓存心跳返回:" + result);
            JSONObject resultJson = JSONObject.parseObject(result);
            heartBeatResponse.put("code", resultJson.getInteger("state") == 1 ? 0 : 1);
            heartBeatResponse.put("msg", resultJson.getInteger("state") == 1 ? "success" : "error");
        } catch (Exception e) {
            logger.error("调用bolink缓存心跳异常", e);
        }
        return heartBeatResponse.toJSONString();
    }

    //发送obu信息到bolink, bolink缓存rusip,扣费使用
    public void cacheRsuip(JSONObject obu) {
        try {
            String boxNo = obu.getString("box_no");
            String plateNo = obu.getString("plate_no");
            String rsuip = obu.getString("rsuip");
            String result = "bolinkApiFeginClient.cacheRsuip(boxNo,plateNo,rsuip)";
            logger.info("bolink缓存rsuip返回:" + result);
        } catch (Exception e) {
            logger.error("调用bolink缓存心跳异常", e);
        }
    }

    public String jyBoxDeductBack(JSONObject msgJson) {
        logger.info("发送bolink扣费结果消息,tradeNo:" + msgJson.getString("serial_no"));
        JSONObject deductFinish = new JSONObject();
        deductFinish.put("method", "etc.deduct.finish");
        deductFinish.put("serial_no", msgJson.getString("serial_no"));
        deductFinish.put("rsuip", msgJson.getString("rsuip"));
        deductFinish.put("code", 1);
        try {
            String result = "bolinkApiFeginClient.jyBoxDeductBack(msgJson.toJSONString())";
            logger.info("bolink记录扣费结果返回:" + result);
            JSONObject bolinkResultJson = JSONObject.parseObject(result);
            deductFinish.put("code", bolinkResultJson.getInteger("state") == 1 ? 0 : 1);
            deductFinish.put("msg", bolinkResultJson.getInteger("state") == 1 ? "扣费完成" : "error");
        } catch (Exception e) {
            logger.error("调用bolink处理扣费结果异常", e);
        }
        return deductFinish.toJSONString();
    }
}
