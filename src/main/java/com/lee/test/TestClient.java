package com.lee.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TestClient {

    @Test
    public void t1(){
        String str = "";
        String[] split = str.split("\\,");
        System.out.println(split.length);
        System.out.println(split[0]);

        String [] arr = new String[]{};
        System.out.println(arr.length);

    }

    @Test
    public void t2() throws CloneNotSupportedException {
        A a = new A("1");
        A b = new A("2");

        a.setA(new ArrayList<>());
        A clone = (A) a.clone();
//        a.getA().add(clone);

        System.out.println(a == clone);

    }

    @Test
    public void testFastjson(){
        A a = new A("a");
        A b = new A("c");
        A c = new A("d");
        a.setA(new ArrayList<>());
        a.getA().add(b);
        a.getA().add(c);

        String s = JSON.toJSONString(a);

        String s1 = JSONObject.toJSONString(a);

        System.out.println(s1);

        List<A> a1 = a.getA();

        System.out.println(JSON.toJSONString(a1));
    }

}

class A implements Cloneable{
    String name;
    List<A> a;

    public A(String name) {
        this.name = name;
    }

    public List<A> getA() {
        return a;
    }

    public void setA(List<A> a) {
        this.a = a;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", A.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("a=" + a)
                .toString();
    }


    @Test
    public void testMap(){
        Map<String, List<String>> map = new HashMap<>();


        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");

        map.put("外观", list1);
//        map.put("中控", list1);
//        map.put("座椅", list2);
        map.put("细节", list2);

        System.out.println(JSON.toJSONString(map));




    }



}



class Pic{
    String type;

}