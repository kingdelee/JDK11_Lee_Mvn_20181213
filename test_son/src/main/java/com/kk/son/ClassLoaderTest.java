package com.kk.son;

import com.kk.father.Father;

public class ClassLoaderTest {

    public static ClassLoader sonClassLoader = new SonClassLoader();

    public static void main(String[] args)
            throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        Father father = (Father) Class.forName("com.kk.son.Son", false, sonClassLoader).newInstance();
        System.out.println(father.getClass().getClassLoader().getName());

//		Class<?> aClass = Class.forName("com.kk.son.Son", false, sonClassLoader);
//		System.out.println(aClass.getClassLoader().getName());


    }

}
