package com.wangwenjun.concurrent.chapter10.test1.test1_class;

import com.wangwenjun.concurrent.chapter10.test1.Test;

public class HelloWorld {
    static {
        Test test = new Test();
        System.out.println(test.getClass().getClassLoader());
        System.out.println("Hello World Class is Initialized.");
    }

    public String welcome() {
        return "Hello World";
    }
}