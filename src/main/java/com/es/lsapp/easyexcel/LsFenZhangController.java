package com.es.lsapp.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.es.lsapp.HttpClientUtil;
import com.es.lsapp.MD5Util;
import com.es.lsapp.TradeNoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/ls")
@Slf4j
public class LsFenZhangController {

    @RequestMapping("/fenzhang")
    public void switchChannel(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        try {
            LsFenZhangListener listener = new LsFenZhangListener();
            EasyExcel.read(file.getInputStream(), LsFenZhangDto.class, listener).sheet().doRead();
            List<LsFenZhangDto> list = listener.getVos();
            System.out.println("------------------"+list.size());
            int i = 1;
            for (LsFenZhangDto dto : list) {
                String outTradeNo = dto.getOutTradeNo();
                Integer amount = changeY2F(dto.getMoney());
                Map<String, Object> retMap = multiApply("", "p" + TradeNoUtil.getTradeNo(21),
                        outTradeNo, dto.getTransactionId(), amount);
                dto.setStatus((String) retMap.get("msg"));
                Thread.sleep(300);
                System.out.println(outTradeNo+"--------结束"+i);
                i++;
            }

            String fileName = "Sss.xlsx";

            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            EasyExcel.write(response.getOutputStream(), LsFenZhangDto.class)
                    .registerWriteHandler(new HorizontalCellStyleStrategy())
                    .sheet()
                    .doWrite(list);
        } catch (Exception e) {
            log.error("分账异常"+e);
        }
    }

    public int changeY2F(double price) {
        DecimalFormat df = new DecimalFormat("#.00");
        price = Double.parseDouble(df.format(price));
        return (int)(price * 100);
    }

    private Map<String, Object> multiApply(String shopId, String profiTradeNo, String outTradeNo, String leshuaTradeNo, Integer amount) {

        Map<String, Object> retmap = new HashMap<>();
        retmap.put("success", false);
        try {
            SortedMap reqMap = new TreeMap();
            reqMap.put("merchantId", shopId);
            reqMap.put("thirdRoyaltyId", profiTradeNo);
            reqMap.put("thirdOrderId", outTradeNo);
            reqMap.put("leshuaOrderId", leshuaTradeNo);

            List<Map> shareDetail = new ArrayList<>();
            SortedMap shareDetailItem = new TreeMap();
            shareDetailItem.put("merchantId", shopId);
            shareDetailItem.put("amount", amount);
            shareDetailItem.put("remark", "手动分账");
            shareDetail.add(shareDetailItem);

            reqMap.put("shareDetail", shareDetail);

            JSONObject reqJson = new JSONObject(reqMap);

            Map requestMap = new HashMap();
            requestMap.put("agentId", 5919932);
            requestMap.put("version", "1.0");
            requestMap.put("reqSerialNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "00010");
            requestMap.put("data", reqJson.toString());
            String sign = Base64Utils.encodeToString(MD5Util.MD5Encode("lepos" + "5D5DC123FD9DE1B4984C9FF07E19EFCA" + reqJson.toString(), "utf-8").toLowerCase().getBytes());
            requestMap.put("sign", sign);

            System.out.println("分账请求" + JSON.toJSONString(requestMap));
            String s = HttpClientUtil.postParameters("https://saas-mch.leshuazf.com/api/share-merchant/multi-apply", requestMap);
            System.out.println(s);
            JSONObject jsonObject = JSONObject.parseObject(s);

            String respCode = jsonObject.getString("respCode");
            String respMsg = jsonObject.getString("respMsg");
            if ("000000".equals(respCode)) {
                JSONObject data = jsonObject.getJSONObject("data");
                //分账状态 0-初始化 1-成功 2-失败
                Integer royalty_status = data.getInteger("royalty_status");
                if (royalty_status != null && royalty_status == 1) {
                    retmap.put("success", true);
                }
                retmap.put("msg", data.getString("retmsg"));
            } else {
                retmap.put("msg", respMsg);
            }
        } catch (Exception e) {
            log.error("分账"+e);
        }
        return retmap;
    }
}

