package classloader;

import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class UnDelegationClassLoader2 extends ClassLoader {
    private String classpath;

    public UnDelegationClassLoader2(String classpath, ClassLoader parent) {
        super(parent);
        this.classpath = classpath;
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
        com.sun.beans.TypeResolver main1 = new com.sun.beans.TypeResolver();
        UnDelegationClassLoader2 cl = new UnDelegationClassLoader2("/Users/kingdelee/IdeaProjects/JDK11_Lee_Mvn_20181213/spring_test/target/classes/",
                getSystemClassLoader());
        String name = "com.sun.beans.TypeResolver";
        Class<?> clz = cl.loadClass(name);
        Object main2 = clz.newInstance();

        System.out.println("main1 class: " + main1.getClass());
        System.out.println("main2 class: " + main2.getClass());
        System.out.println("main1 classloader: " + main1.getClass().getClassLoader());
        System.out.println("main2 classloader: " + main2.getClass().getClassLoader());
        ClassLoader itrCl = cl;
        while (itrCl != null) {
            System.out.println(itrCl);
            itrCl = itrCl.getParent();
        }
    }
}
