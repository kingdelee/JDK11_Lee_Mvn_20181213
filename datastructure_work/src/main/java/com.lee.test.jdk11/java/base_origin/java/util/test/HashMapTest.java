package com.lee.test.jdk11.java.base_origin.java.util.test;

import com.lee.test.jdk11.java.base_origin.java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class HashMapTest {

    static Stream<Person> personStream;


    @Test
    public void t1() {
        HashMap<Person, Person> hashMap = new HashMap<>();
        personStream.forEach(person -> {
            hashMap.put(person, person);
            System.out.println("person:" + person.getName());
        });
    }

    /**
     * 初始化包含hashcode冲突的对象
     */
    @BeforeAll
    public static void t4_data() {
        personStream = Stream.of(new Person("a1"),
                new Person("a2"),
                new Person("a3"),
                new Person("b"),
                new Person("c"),
                new Person("d"),
                new Person("e"));

    }

    @Test
    public void t4_error2() {
        HashMap<Person, Person> hashMap = new HashMap<>();
        personStream.forEach(person -> hashMap.put(person, person));

        Set<Map.Entry<Person, Person>> entries = hashMap.entrySet();
        for (Map.Entry<Person, Person> entry : entries) {
            Person key = entry.getKey();
            Person value = entry.getValue();
            System.out.println("key:" + key.getName() + ",val:" + entry.getValue().getName());
        }
    }

    @Test
    public void t4_error3() {
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(3, 3);
        hashMap.put(4, 4);

//        IntStream.rangeClosed(1,6).forEach(i->hashMap.put(i, i));

        Set<Map.Entry<Integer, Integer>> entries = hashMap.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
            System.out.println("key:" + entry.getKey() + ",val:" + entry.getValue());

        }
    }

}
