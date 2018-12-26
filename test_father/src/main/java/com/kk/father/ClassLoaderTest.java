package com.kk.father;

public class ClassLoaderTest {

    public static ClassLoader sonClassLoader = new SonClassLoader();

    public static void main(String[] args)
            throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException {

//		Father father = Father.class.cast(Class.forName("com.kk.son.Son", false, sonClassLoader).newInstance());
//		System.out.println(father);

        Object o = Class.forName("com.kk.son.Son", false, sonClassLoader).newInstance();
        System.out.println(o.getClass().getClassLoader().getName());

    }

}
