package com.lee.test.jdk11.jvm.map.primitive;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapTest {

    @Test
    public void t1() {
        HashMap<Integer, Person> hashMap = new HashMap<>();
        hashMap.put(1, new Person("1", 1));
        hashMap.put(2, new Person("2", 2));
        hashMap.put(3, new Person("3", 3));
        Set<Map.Entry<Integer, Person>> entries = hashMap.entrySet();
        for (Map.Entry<Integer, Person> entry : entries) {
            Integer key = entry.getKey();
            Person value = entry.getValue();
        }
    }

    @Test
    public void t2() {
        HashMap<Person2, Person2> hashMap = new HashMap<>();
        hashMap.put(new Person2("a1"), new Person2("a1"));
        hashMap.put(new Person2("a2"), new Person2("a2"));
        hashMap.put(new Person2("a3"), new Person2("a3"));
        hashMap.put(new Person2("b"), new Person2("b"));
        hashMap.put(new Person2("c"), new Person2("c"));
        hashMap.put(new Person2("d"), new Person2("d"));
        Set<Map.Entry<Person2, Person2>> entries = hashMap.entrySet();
        for (Map.Entry<Person2, Person2> entry : entries) {
            Person2 key = entry.getKey();
            Person2 person = entry.getValue();
            System.out.println("person:" + person.getName());
        }
    }

    @Test
    public void t3() {
        HashMap<Person2, Person2> hashMap = new HashMap<>();
        hashMap.put(new Person2("a1"), new Person2("a1"));
        hashMap.put(new Person2("a2"), new Person2("a2"));
        hashMap.put(new Person2("a3"), new Person2("a3"));
        hashMap.put(new Person2("b"), new Person2("b"));
        hashMap.put(new Person2("c"), new Person2("c"));
        hashMap.put(new Person2("d"), new Person2("d"));


        Set<Map.Entry<Person2, Person2>> entries = hashMap.entrySet();

        Iterator<Map.Entry<Person2, Person2>> iterator = entries.iterator();

        for (; iterator.hasNext(); ) {
            Map.Entry<Person2, Person2> entry = iterator.next();
            Person2 key = entry.getKey();
            Person2 person = entry.getValue();
            System.out.println("person:" + person.getName());
        }


    }

}

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Person2 {
    private String name;

    public Person2(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hashcode = super.hashCode();
        if (name.startsWith("a")) {
            hashcode = 2;
        } else if (name.startsWith("b")) {
            hashcode = 3;
        } else if (name.startsWith("c")) {
            hashcode = 4;
        }
        return hashcode;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            String name = ((Person2) obj).getName();
            if (this.getName().equals(name)) {
                return true;
            }
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Person2{" +
                "name='" + name + '\'' +
                '}';
    }
}