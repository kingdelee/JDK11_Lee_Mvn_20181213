package com.wangwenjun.concurrent.chapter06;

import org.junit.jupiter.api.Test;

import java.util.Objects;

public class HashCode {

    @Test
    public void t1() {
        Person person1 = new Person("1");
        Person person2 = new Person("1");

        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
        System.out.println(System.identityHashCode(person1));
        System.out.println(System.identityHashCode(person2));
    }

    class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

}
