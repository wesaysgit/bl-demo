package com.es.tcp;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class YushiTest {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1",6796);
        socket.setKeepAlive(true);
        boolean connected = socket.isConnected();
        OutputStream outputStream = socket.getOutputStream();
        String ss="太长了...";

        byte[] bytes = ss.getBytes("utf-8");
        System.out.println(bytes.length);
        outputStream.write(bytes);
        outputStream.flush();

        Thread.sleep(50000);

    }
}
