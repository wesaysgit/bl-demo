package com.es.base64;

import okhttp3.*;

import java.io.IOException;

public class OkClient {
    public static void main(String[] args) {
        String url = "https://68_40_9a_0_2_cb.tdzntech.com:9696/ftpdir/pic/Recognize/20221220/192.168.1.31_2022122010301641833_6863.jpg";

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println("response = " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
