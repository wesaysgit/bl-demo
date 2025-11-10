package com.es.esdemo;

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
        double multi = mult2(BigDecimal.valueOf(2.85005),BigDecimal.valueOf( 0.3));
        System.out.println(multi);
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
