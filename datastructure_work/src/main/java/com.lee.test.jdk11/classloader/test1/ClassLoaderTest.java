package com.lee.test.jdk11.classloader.test1;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassLoaderTest {

    public static void main(String[] args) {
        try {
            //查看当前系统类路径中包含的路径条目  
            System.out.println(System.getProperty("java.class.path"));

//            /Users/kingdelee/IdeaProjects/Jdk11_Lee_Mvn_Project/target/classes:/Users/kingdelee/workstation/repo/org/springframework/spring-context/5.1.2.RELEASE/spring-context-5.1.2.RELEASE.jar:/Users/kingdelee/workstation/repo/org/springframework/spring-aop/5.1.2.RELEASE/spring-aop-5.1.2.RELEASE.jar:/Users/kingdelee/workstation/repo/org/springframework/spring-beans/5.1.2.RELEASE/spring-beans-5.1.2.RELEASE.jar:/Users/kingdelee/workstation/repo/org/springframework/spring-core/5.1.2.RELEASE/spring-core-5.1.2.RELEASE.jar:/Users/kingdelee/workstation/repo/org/springframework/spring-jcl/5.1.2.RELEASE/spring-jcl-5.1.2.RELEASE.jar:/Users/kingdelee/workstation/repo/org/springframework/spring-expression/5.1.2.RELEASE/spring-expression-5.1.2.RELEASE.jar:/Users/kingdelee/workstation/repo/org/junit/jupiter/junit-jupiter-api/5.3.1/junit-jupiter-api-5.3.1.jar:/Users/kingdelee/workstation/repo/org/apiguardian/apiguardian-api/1.0.0/apiguardian-api-1.0.0.jar:/Users/kingdelee/workstation/repo/org/opentest4j/opentest4j/1.1.1/opentest4j-1.1.1.jar:/Users/kingdelee/workstation/repo/org/junit/platform/junit-platform-commons/1.3.1/junit-platform-commons-1.3.1.jar:/Users/kingdelee/workstation/repo/org/junit/jupiter/junit-jupiter-engine/5.3.1/junit-jupiter-engine-5.3.1.jar:/Users/kingdelee/workstation/repo/org/junit/platform/junit-platform-engine/1.3.1/junit-platform-engine-1.3.1.jar


            //调用加载当前类的类加载器（这里即为系统类加载器）加载TestBean  
            Class typeLoaded = Class.forName("com.lee.test.jdk11.classloader.test1.TestBean");
            Class typeLoaded2 = Class.forName("com.lee.test.jdk11.classloader.test1.TestBean");

            //查看被加载的TestBean类型是被那个类加载器加载的  
            System.out.println(typeLoaded.getClassLoader());
            System.out.println(typeLoaded2.getClassLoader());

//            jdk.internal.loader.ClassLoaders$AppClassLoader@512ddf17
//            jdk.internal.loader.ClassLoaders$AppClassLoader@512ddf17

            System.out.println(typeLoaded.getClass().getClassLoader() == typeLoaded2.getClass().getClassLoader());
            System.out.println(typeLoaded.getClass() == typeLoaded2.getClass());

//            true
//            true

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 不同实例的类加载器加载同一个类，其生成的类的加载器当然是不一样的，类也是不一样的
    @Test
    public void t1() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassLoader myLoaderA = new MyClassLoader();
        ClassLoader myLoaderB = new MyClassLoader();
        Object objA = myLoaderA.loadClass("com.lee.test.jdk11.classloader.test1.TestBean").getDeclaredConstructor().newInstance();
        Object objA2 = myLoaderA.loadClass("com.lee.test.jdk11.classloader.test1.TestBean").getDeclaredConstructor().newInstance();
        Object objB = myLoaderB.loadClass("com.lee.test.jdk11.classloader.test1.TestBean").getDeclaredConstructor().newInstance();
        System.out.println(objA.getClass().getClassLoader() == objB.getClass().getClassLoader());
        System.out.println(objA.getClass() == objB.getClass());

        System.out.println(objA.getClass().getClassLoader() == objA2.getClass().getClassLoader());
        System.out.println(objA.getClass() == objA2.getClass());

    }

    @Test
    public void t2() throws ClassNotFoundException {
//        ClassLoader myLoaderA = new MyClassLoader();
        ClassLoader myLoaderA = new MyClassLoader2();
        Class classA = myLoaderA.loadClass("com.lee.test.jdk11.classloader.test1.TestBean");
        Class classA2 = myLoaderA.loadClass("com.lee.test.jdk11.classloader.test1.TestBean");
        Class classA3 = Class.forName("com.lee.test.jdk11.classloader.test1.TestBean");

        System.out.println(classA.getClassLoader() == classA3.getClassLoader());
        System.out.println(classA == classA3);


    }

    // 1.安全性 2.隔离性 3.共享性

    // 区分自定义类加载器和Class.forName
    // 结论，自定义类加载器用到是自己定义的加载器，Class用的是jdk的应用加载器
    @Test
    public void t3() throws ClassNotFoundException {
        ClassLoader classA = new MyClassLoader();

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        Class aClass = classA.loadClass("com.lee.test.jdk11.classloader.test1.TestBean");
        Class bClass = Class.forName("com.lee.test.jdk11.classloader.test1.TestBean");
        Class cClass = Class.forName("com.lee.test.jdk11.classloader.test1.TestBean");
        Class dClass = systemClassLoader.loadClass("com.lee.test.jdk11.classloader.test1.TestBean");

        System.out.println(aClass.getClassLoader());
        System.out.println(bClass.getClassLoader());
        System.out.println(cClass.getClassLoader());
        System.out.println(dClass.getClassLoader());

//        com.lee.test.jdk11.classloader.test1.MyClassLoader@30b7c004
//        jdk.internal.loader.ClassLoaders$AppClassLoader@2c13da15


        assertTrue(bClass == cClass);
        assertFalse(aClass.getClassLoader() == bClass.getClassLoader());
        assertFalse(aClass == bClass);


    }

    // 如果自定义的类加载器加载系统类会怎么样？
    @Test
    public void t4() throws ClassNotFoundException {
        ClassLoader classA = new MyClassLoader();


        Class aClass = classA.loadClass("java.lang.Object");
        Class bClass = Class.forName("java.lang.Object");

        System.out.println(aClass.getClassLoader());
        System.out.println(bClass.getClassLoader());

        assertTrue(aClass == bClass);

    }


}  