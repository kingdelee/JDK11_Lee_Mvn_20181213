package com.lee.test.jdk11.classloader.test4.classloader;

public class ClassLoaderTree {

    public static void main(String[] args) {
        ClassLoader loader = ClassLoaderTree.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
    }
}
