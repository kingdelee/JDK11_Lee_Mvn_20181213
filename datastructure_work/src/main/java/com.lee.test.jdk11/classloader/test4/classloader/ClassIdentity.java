package com.lee.test.jdk11.classloader.test4.classloader;

import java.lang.reflect.Method;


public class ClassIdentity {

    public static void main(String[] args) {
        new ClassIdentity().testClassIdentity();
    }

    public void testClassIdentity() {
//		String classDataRootPath = "C:\\Documents and Settings\\Administrator\\workspace\\Classloader\\classData";
        String classDataRootPath = "/Users/kingdelee/IdeaProjects/Jdk11_Lee_Mvn_Project/src/main/java";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
        String className = "com.lee.test.jdk11.classloader.test4.com.example.Sample";
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
}
