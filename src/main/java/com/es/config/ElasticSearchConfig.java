package com.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ElasticSearchConfig {

    @Value("${wesays.es.hosts}")
    private String hosts;

    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient() {
        String[] hostArray = hosts.split(",");
        HttpHost[] httpHosts = new HttpHost[hostArray.length];
        for (int i = 0; i < hostArray.length; i++) {
            String host = hostArray[i];
            String[] split = host.split(":");
            httpHosts[i] = new HttpHost(split[0], Integer.parseInt(split[1]), "http");
        }
        return new RestHighLevelClient(RestClient.builder(httpHosts));
    }
}
