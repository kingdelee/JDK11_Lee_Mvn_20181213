package com.lee.test.jdk11.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Log4jTest {

    private static final Logger logger = LogManager.getLogger("root");

    private static final String a = "aaaa";

    public static void main(String[] args) throws InterruptedException {
//        logger.info("Hello, World!");
        t1();

    }


    public static void t1() throws InterruptedException {

        String str1 = "111";
        String str2 = "222";
        String str3 = "333";

        System.out.println(str1 + str2);
        System.out.println(str1 + "2");
        System.out.println(str3 +  str2 + "3");
        System.out.println(a);

        TimeUnit.SECONDS.sleep(1000);

    }

}
