package com.kk.father;

import java.io.FileInputStream;
import java.io.InputStream;

public class SonClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        Class<?> c = findLoadedClass(name);
        if (c == null) {
            try {
                ClassLoader parent = getParent();
                if (parent != null) {
                    c = parent.loadClass(name);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        if (c == null) {
            return this.findClass(name);
        }

        return c;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String primaryName = name;
            name = name.replace(".", "/");
            InputStream is = new FileInputStream("/Users/kingdelee/IdeaProjects/JDK11_Lee_Mvn_20181213/test_son/target/classes/" + name + ".class");
            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(primaryName, b, 0, b.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ClassNotFoundException();
    }
}
