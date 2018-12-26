package classloader;

import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class UnDelegationClassLoader extends ClassLoader {
    private String classpath;

    public UnDelegationClassLoader(String classpath) {
        super(null);
        this.classpath = classpath;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clz = findLoadedClass(name);
        if (clz != null) {
            return clz;
        }

        // jdk 目前对"java."开头的包增加了权限保护，这些包我们仍然交给 jdk 加载
        if (name.startsWith("java.")) {
            return ClassLoader.getSystemClassLoader().loadClass(name);
        }
        return findClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream is = null;
        try {
            String classFilePath = this.classpath + name.replace(".", "/") + ".class";
            is = new FileInputStream(classFilePath);
            byte[] buf = new byte[is.available()];
            is.read(buf);
            return defineClass(name, buf, 0, buf.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new IOError(e);
                }
            }
        }
    }

    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException,
            MalformedURLException {
//        sun.applet.Main main1 = new sun.applet.Main();

        UnDelegationClassLoader cl = new UnDelegationClassLoader("/Users/kingdelee/IdeaProjects/JDK11_Lee_Mvn_20181213/spring_test/target/classes/");
        String name = "com.sun.beans.TypeResolver";
        Class<?> clz = cl.loadClass(name);
        Object main2 = clz.newInstance();
//
//        System.out.println("main1 class: " + main1.getClass());
        System.out.println("main2 class: " + main2.getClass());
//        System.out.println("main1 classloader: " + main1.getClass().getClassLoader());
        System.out.println("main2 classloader: " + main2.getClass().getClassLoader());
    }
}

