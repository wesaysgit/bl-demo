package com.es.esdemo.pojo;

import com.SearchApplication;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest(classes = SearchApplication.class)
@RunWith(SpringRunner.class)
public class TestDoc {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void testFlag() {

    }

    @Test
    public void testGet() throws IOException {
        GetRequest request = new GetRequest("book", "1");
//        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        ActionListener<GetResponse> listener = new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse response) {
                System.out.println("response.getSourceAsString() = " + response.getSourceAsString());
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        };
        restHighLevelClient.getAsync(request, RequestOptions.DEFAULT, listener);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
