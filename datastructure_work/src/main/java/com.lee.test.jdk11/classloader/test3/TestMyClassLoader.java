package com.lee.test.jdk11.classloader.test3;

import org.junit.jupiter.api.Test;

public class TestMyClassLoader {
    @Test
    public void test() throws Exception {
//        MyClassLoader loader = new MyClassLoader("/Users/kingdelee/lee/test");
//        Class<?> c = loader.loadClass("testOthers.HelloWorld2");
//        System.out.println(c.getClassLoader());

        Class<?> helloWorld2 = ClassLoader.getSystemClassLoader().loadClass("");
        Object o = helloWorld2.getConstructor().newInstance();
        System.out.println(helloWorld2.getClassLoader());
        System.out.println(o);


        //com.lee.test.jdk11.classloader.test3.MyClassLoader@45018215
    }

    @Test
    public void test2() throws Exception {
        MyClassLoader loader = new MyClassLoader("/Users/kingdelee/IdeaProjects/Jdk11_Lee_Mvn_Project/target/classes");
        Class<?> c = loader.loadClass("com.lee.test.jdk11.classloader.test3.HelloWorld2");
        System.out.println(c.getClassLoader());

        // jdk.internal.loader.ClassLoaders$AppClassLoader@2c13da15
    }
}
