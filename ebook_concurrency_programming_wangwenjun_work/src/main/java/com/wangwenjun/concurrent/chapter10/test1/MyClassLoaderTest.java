package com.wangwenjun.concurrent.chapter10.test1;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/***************************************
 * @author:Alex Wang
 * @Date:2017/11/20
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class MyClassLoaderTest {
    public static void main(String[] args)
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException, NoSuchMethodException,
            InvocationTargetException, InterruptedException {/*
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> bClass = classLoader
                .loadClass("com.wangwenjun.concurrent.chapter10.test1.HelloWorld");


        System.out.println(bClass.getClassLoader());

        //
        Object helloWorld = bClass.newInstance();
        System.out.println(helloWorld);
        Method welcomeMethod = bClass.getMethod("welcome");
        String result = (String) welcomeMethod.invoke(helloWorld);
        System.out.println("Result:" + result);*/

        MyClassLoader classLoader = new MyClassLoader("G:\\classloader1", null);
        Class<?> aClass = classLoader.loadClass("com.wangwenjun.concurrent.chapter10.test1.HelloWorld");
        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());


        MyClassLoader classLoader2 = new MyClassLoader("G:\\classloader1", null);
        Class<?> bClass = classLoader2.loadClass("com.wangwenjun.concurrent.chapter10.test1.HelloWorld");
        System.out.println(bClass);
        System.out.println(bClass.getClassLoader());

        System.out.println(aClass == bClass);

 /*       ClassLoader contextClassLoader = currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);

        currentThread().setContextClassLoader(classLoader);
        System.out.println(currentThread().getContextClassLoader());

        Thread thread = new Thread(() ->
        {
            HelloWorld helloWorld = new HelloWorld();
            System.out.println(helloWorld.getClass().getClassLoader());
        });

        thread.setContextClassLoader(classLoader);
        thread.start();

        Thread.currentThread().join();*/
    }


    /**
     * loadClass的加载方式仅执行到加载阶段，并没有到初始化阶段
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void t1() throws ClassNotFoundException {
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> bClass = classLoader.loadClass("com.wangwenjun.concurrent.chapter10.test1.HelloWorld");

        System.out.println(bClass.getClassLoader());
    }

    @Test
    public void t2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> bClass = classLoader.loadClass("com.wangwenjun.concurrent.chapter10.test1.HelloWorld");

        System.out.println(bClass.getClassLoader());

        Object helloWorld = bClass.newInstance();
        System.out.println(helloWorld);
        Method welcomeMethod = bClass.getMethod("welcome");
        String result = (String) welcomeMethod.invoke(helloWorld);
        System.out.println("Result:" + result);
    }

    @Test
    public void t3_1() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        ClassLoader extParent = MyClassLoaderTest.class.getClassLoader().getParent();
        MyClassLoader myClassLoader
                = new MyClassLoader(
                "/Users/kingdelee/IdeaProjects/JDK11_Lee_Mvn_20181213/ebook_concurrency_programming_wangwenjun_work/src/main/java",
                extParent);
        Class<?> aClass = myClassLoader.loadClass("com.wangwenjun.concurrent.chapter10.test1.HelloWorld");
        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());

    }

    @Test
    public void t3_2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        MyClassLoader myClassLoader =
                new MyClassLoader(
                        "/Users/kingdelee/IdeaProjects/JDK11_Lee_Mvn_20181213/ebook_concurrency_programming_wangwenjun_work/src/main/java",
                        null);
        Class<?> aClass = myClassLoader.loadClass("com.wangwenjun.concurrent.chapter10.test1.HelloWorld");
        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());

    }

}