package com.lee.test.jdk11.jvm.equals;

import org.junit.jupiter.api.Test;

public class EqualsTest {

    /**
     * 目标：测试两个对象通过==是否相等
     * 期望：因为创建的对象内存地址不一样，所以不相等
     * 结果：false
     */
    @Test
    public void t1() {
        A a = new A("1");
        A b = new A("1");

        System.out.println(a == b);
    }

    /**
     * 疑问：==的内存地址是否与equals相关
     * 判断：不相关，equals判断的是内容，==判断的是地址；==如果是基本数据类型，则才是值，否则是引用地址
     * equals默认也是使用object.equals里的==判断，所以本质上是一致的
     * 但是可以通过重写equals实现想要的内容一致则结果一致的判断。
     * 期望：a.equals(b)默认情况下是false，重写使用自己的判断逻辑，则可以实现true
     * 结果: yes
     */
    @Test
    public void t2() {

        A a1 = new A("1");
        A a2 = new A("1");

        System.out.println(a1.equals(a2));

        B b1 = new B("1");
        B b2 = new B("1");

        System.out.println(b1.equals(b2));
    }

}


class A {
    String name;

    public A(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class B {
    String name;

    public B(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            if (this.getName() == ((B) obj).getName()) {
                return true;
            }
        }
        return super.equals(obj);
    }

}