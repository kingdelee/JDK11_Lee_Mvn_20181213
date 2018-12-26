package com.lee.test.jdk11.classloader.test1;


public class MyClassLoader3 extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
}
