package com.lee.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FastJsonTest {

    @Test
    public void t1(){

        List<Person> list = new ArrayList<>();
        list.add(new Person("1", 1));
        list.add(new Person("2", 2));
        list.add(new Person("3", 3));

        String s = JSONObject.toJSONString(list);

        System.out.println(s);


//        List<String> objects = JSONObject.parseArray(s, String.class);
        JSONArray objects = JSONObject.parseArray(s);

        JSONObject jsonObject = JSONObject.parseObject(s);



    }




    class Person {
        private String name;
        private int age;


        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    class Person2 {
        private String name2;
        private int age;

        public Person2(String name2, int age) {
            this.name2 = name2;
            this.age = age;
        }

        public String getName2() {
            return name2;
        }

        public void setName2(String name2) {
            this.name2 = name2;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }


}


