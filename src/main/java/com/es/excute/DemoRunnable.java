package com.es.excute;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("11111111111111111");
        log.info("=======================");
    }

}
