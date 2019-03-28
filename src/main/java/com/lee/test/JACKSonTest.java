package com.lee.test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

public class JACKSonTest {

    @Test
    public void t1() {


    }

    @Test
    public void testMap() throws IOException {
        Map<ImgEnum, String> map = new HashMap<>();


//        map.put(ImgEnum.Detail, list2);
        map.put(ImgEnum.Banner, "1,2,3");
//        map.put("中控", list1);
//        map.put("座椅", list2);
//        map.put(ImgEnum.Facade, list2);
//        map.put(ImgEnum.Seat, list2);

        System.out.println(map);

        ObjectMapper mapper = new ObjectMapper();
        String text = mapper.writeValueAsString(map);
        System.out.println(text);

        Map<ImgEnum, String> map2 = mapper.readValue(text, new TypeReference<Map<ImgEnum, String>>() {
        });

        map2.get(ImgEnum.Seat);

        System.out.println(map2);

        System.out.println(ImgEnum.Detail.en);
        System.out.println(ImgEnum.Detail.cn);

    }

    @Test
    public void testEnum() {
        System.out.println(ImgEnum.Banner.en);
    }

    /**
     * @author lijiandong
     * 图片的类型
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum ImgEnum {

        Banner("banner", "轮播"), Facade("facade", "外观"), Seat("seat", "座椅"), Center("center", "中控"), Detail("detail", "细节");

        String en;
        String cn;

        ImgEnum(String en, String cn) {
            this.en = en;
            this.cn = cn;
        }

        @Override
        public String toString() {
            return this.en;
        }

        @JsonValue
        public String getEn() {
            return en;
        }

        public void setEn(String en) {
            this.en = en;
        }

        public String getCn() {
            return cn;
        }

        public void setCn(String cn) {
            this.cn = cn;
        }
    }


    @Test
    public void t3List() throws JsonProcessingException {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        ObjectMapper mapper = new ObjectMapper();
        String text = mapper.writeValueAsString(list);
        System.out.println(text);
    }

    // {"name":"a","age":1}
    @Test
    public void t4() throws IOException {
        Person p1 = new Person();
        p1.setName("a");
        p1.setAge(1);

        ObjectMapper mapper = new ObjectMapper();
        String text = mapper.writeValueAsString(p1);
        System.out.println(text);


        Person map2 = mapper.readValue(text, new TypeReference<Person>() {
        });

        JsonNode jsonNode = mapper.readTree(text);


        String age = jsonNode.get(0).get("age").textValue();
        System.out.println(age);


    }

    @Test
    public void t5() throws IOException {


        List<Person> list = new ArrayList<>();

        list.add(new Person("1", 1));
        list.add(new Person("2", 2));
        list.add(new Person("3", 3));

        ObjectMapper mapper = new ObjectMapper();
        String text = mapper.writeValueAsString(list);
        System.out.println(text);


        JsonNode jsonNode = mapper.readTree(text);


        String age = jsonNode.get(0).get("age").asText();
        System.out.println(age);


    }

    @Test
    public void t6(){
    }

}

class Person {
    private String name;
    private int age;


    public Person() {
    }

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
