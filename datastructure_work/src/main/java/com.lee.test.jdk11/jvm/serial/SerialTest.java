package com.lee.test.jdk11.jvm.serial;

import org.junit.jupiter.api.Test;

import java.io.*;

public class SerialTest {

    @Test
    public void t1() throws IOException {
        Person person = new Person("a", 1, 1);

        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("UserInfo.txt"));
        o.writeObject(person);
        o.close();
    }

    @Test
    public void t2() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("UserInfo.txt"));
        Person readUserInfo = (Person) in.readObject();
        System.out.println(Person.name);
        System.out.println(Person.age);

    }

}

/**
 * 序列化 static修饰时，属性值为null
 * transient修饰时，直接忽略该属性
 */
class Person implements Serializable {
    static String name;
    static int age;
    transient int sex;

    public Person(String name, int age, int sex) {
        Person.name = name;
        Person.age = age;
        this.sex = sex;
    }
}