package com.wangwenjun.concurrent.chapter10;

import com.wangwenjun.concurrent.chapter10.test1.MyClassLoader;

/***************************************
 * @author:Alex Wang
 * @Date:2017/11/21
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class NameSpace {
    public static void main(String[] args)
            throws ClassNotFoundException {
        MyClassLoader classLoader1 = new MyClassLoader("G:\\classloader1", null);
        MyClassLoader classLoader2 = new MyClassLoader("G:\\classloader1", null);
        Class<?> aClass = classLoader1.loadClass("com.wangwenjun.concurrent.chapter10.test1.Test");
        Class<?> bClass = classLoader2.loadClass("com.wangwenjun.concurrent.chapter10.test1.Test");
        System.out.println(aClass.getClassLoader());
        System.out.println(bClass.getClassLoader());
        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());
        System.out.println(aClass == bClass);
    }
}
