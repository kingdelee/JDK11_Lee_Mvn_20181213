package com.lee.test.jdk11.java.base.java.lang;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;


public class A {

    private static final Logger logger = LogManager.getLogger(A.class);


    public static void main(String[] args) {
        logger.info("11");
        File configFile = new File("log4j2.xml");
        System.out.println(configFile.getAbsoluteFile());

    }

}
