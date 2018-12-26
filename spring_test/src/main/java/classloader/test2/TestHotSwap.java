package classloader.test2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class TestHotSwap {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, MalformedURLException {
        A a = new A();
        B b = new B();
        a.setB(b);

        System.out.printf("A classLoader is %s n", a.getClass().getClassLoader());
        System.out.printf("B classLoader is %s n", b.getClass().getClassLoader());
        System.out.printf("A.b classLoader is %s n", a.getB().getClass().getClassLoader());

        HotSwapClassLoader c1 = new HotSwapClassLoader(new URL[]{new URL(null)}, a.getClass().getClassLoader());
        Class clazz = c1.load(" test.hotswap.A ");
        Object aInstance = clazz.newInstance();

        Method method1 = clazz.getMethod(" setB ", B.class);
        method1.invoke(aInstance, b);

        Method method2 = clazz.getMethod(" getB ", null);
        Object bInstance = method2.invoke(aInstance, null);

        System.out.printf(" reloaded A.b classLoader is %s n", bInstance.getClass().getClassLoader());
    }
}