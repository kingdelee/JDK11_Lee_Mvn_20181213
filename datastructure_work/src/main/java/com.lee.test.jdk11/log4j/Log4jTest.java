package com.lee.test.jdk11.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jTest {

    private static final Logger logger = LogManager.getLogger("root");

    public static void main(String[] args) {
        logger.info("Hello, World!");
    }

}
