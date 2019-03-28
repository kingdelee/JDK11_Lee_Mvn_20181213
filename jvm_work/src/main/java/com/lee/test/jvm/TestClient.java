package com.lee.test.jvm;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * @Title: TestClient
 * @Description: TODO
 * @Author: design
 * @Create: 2019-01-17 14:42
 * @Modified By: Who(When)
 * @Version v1.0
 **/
public class TestClient {


    private static final String str1 = "aaaa";

    public static void t1() throws InterruptedException {
        String str2 = "2";
        String str3 = "3";
        String str4 = "4";

        System.out.println(str2 + str3);
        System.out.println("55" + "66");
        System.out.println(str2 + "111");



        System.out.println(str2);
        System.out.println(str1);

        TimeUnit.SECONDS.sleep(100);

    }

    public static void main(String[] args) throws InterruptedException {


        t1();


    }

}
