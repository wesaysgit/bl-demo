package com.blsign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    
    private static final Logger logger = LoggerFactory.getLogger(LogTest.class);
    
    public static void main(String[] args) {
        System.out.println("=== 开始日志测试 ===");
        
        // 测试不同级别的日志
        logger.trace("这是 TRACE 级别的日志");
        logger.debug("这是 DEBUG 级别的日志");
        logger.info("这是 INFO 级别的日志");
        logger.warn("这是 WARN 级别的日志");
        logger.error("这是 ERROR 级别的日志");
        
        // 测试异常日志
        try {
            throw new RuntimeException("测试异常");
        } catch (Exception e) {
            logger.error("捕获到异常", e);
        }
        
        System.out.println("=== 日志测试结束 ===");
    }
}
