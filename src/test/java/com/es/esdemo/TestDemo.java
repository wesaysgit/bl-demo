package com.es.esdemo;

import cn.hutool.http.HttpUtil;
import com.es.lsapp.dto.Clazz;
import com.es.lsapp.dto.Student;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDemo {

    @Test
    public void jhToString() {
        String str = "{\"orgId\":\"07911449\",\"reqData\":{\"amt\":\"0.03\",\"mno\":\"399250527287478\",\"notifyUrl\":\"https://paas.bolink.club/trade/notifysxf/pay\",\"ordNo\":\"24138202511261421381000021003034\",\"payType\":\"WECHAT\",\"payWay\":\"03\",\"subAppid\":\"wxe551bcb8271420f0\",\"subject\":\"车主权益-券包购买\",\"trmIp\":\"192.168.100.9\",\"userId\":\"oEIJU4yJ5ZAq0cSKEOh3EAZRnwQ8\"},\"reqId\":\"2e641fa6edda4ea797c0538a330b748b\",\"sign\":\"GQ2cNek1K+YWFuahgjGEjVSezyYVCFSh59rtc/Ue5nreZiecGFXwaIQgagbwV7ALh8koM/fLiwJxYJUhx/uHmPd84T4lQTn0lFQSVSbCotsIU66gXH1wXaQuR2Cno6AGjgcoznFMoeqBZ3iE1hUEt0XXfSN6gpfVjWW3EdNcphrZmAC1OCseR7rCuIUDXXJZnDuTDFm4I84vVM2A/yEDJtobobXVNy/GNbizILvL84K7F3KTRqr8hpkHKkpP8TwHhqJSDuKUCLOUx0Zw+7yQewYK1fIBPnlYiXp6ftGcHv5xS48sj80w7ssxmliEFOXULsgqlT0EQFKzZ4KzBHOUug==\",\"signType\":\"RSA\",\"timestamp\":1764138098255,\"version\":\"1.0\"}";
        String post = HttpUtil.post("https://openapi.tianquetech.com/order/jsapiScan", str);
        System.out.println(post);
    }

    public double mult2(BigDecimal b1, BigDecimal b2) {
        return b1.multiply(b2).setScale(5, RoundingMode.HALF_UP).doubleValue();
    }

    public double multi(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.multiply(b2).setScale(5, RoundingMode.HALF_UP).doubleValue();
    }

    @Test
    public void cycle() {
        Clazz clazz = new Clazz(1, "一班", new Student(1, "LIli"));
        System.out.println(clazz);
        Student student = clazz.getStudent();
        student.setName("wawa1");
        System.out.println(clazz);
    }

    @Test
    public void sum() {
        long l = System.currentTimeMillis();
        System.out.println(l);
    }

    @Test
    public void len() throws ParseException {
        SimpleDateFormat sdfStr_1 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date parse = sdfStr_1.parse("20230117142155");
        long time = parse.getTime();
        System.out.println("time = " + time);
    }

    public static void main(String[] args) throws Exception {
        RestHighLevelClient levelClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 9200)
        ));
        GetRequest request = new GetRequest("book", "1");
        GetResponse response = levelClient.get(request, RequestOptions.DEFAULT);
        System.out.println("response.getId() = " + response.getId());
        System.out.println("response.getIndex() = " + response.getIndex());
        System.out.println("response.getSource() = " + response.getSource());
        levelClient.getAsync(request, RequestOptions.DEFAULT, new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse documentFields) {
            }
            @Override
            public void onFailure(Exception e) {
            }
        });

    }
}
