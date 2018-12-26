package com.lee.test.jdk11.classloader.test2;

import com.lee.test.jdk11.classloader.test1.MyClassLoader;
import com.lee.test.jdk11.classloader.test1.MyClassLoader2;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestClient {

    public static void main(String[] args) {

        String classDataRootPath = "/Users/kingdelee/IdeaProjects/Jdk11_Lee_Mvn_Project/target/classes";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
        String className = "com.lee.test.jdk11.classloader.test2.Sample";


        try {
            Class<?> class1 = fscl1.loadClass(className);
            Object obj1 = class1.newInstance();
            Class<?> class2 = fscl2.loadClass(className);
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setSample", Object.class);
            setSampleMethod.invoke(obj1, obj2);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t1() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassLoader classLoader1 = new MyClassLoader2();
        ClassLoader classLoader2 = new MyClassLoader();

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        Class aClass = classLoader1.loadClass("com.lee.test.jdk11.classloader.test2.Sample");
        Class bClass = classLoader2.loadClass("com.lee.test.jdk11.classloader.test2.Sample");


        System.out.println(aClass.getClassLoader());
        System.out.println(bClass.getClassLoader());


        Object o1 = aClass.getConstructor().newInstance();
        Object o2 = aClass.getConstructor().newInstance();

        Method setSampleMethod = aClass.getMethod("setSample", Object.class);
        setSampleMethod.invoke(o1, o2);

        System.out.println(o1);
        System.out.println(o2);

    }

}
