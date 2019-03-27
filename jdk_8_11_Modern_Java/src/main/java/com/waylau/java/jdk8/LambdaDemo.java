/**
 * Welcome to https://waylau.com
 */
package com.waylau.java.jdk8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * JDK8:Lambda.
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2018年12月25日
 */
public class LambdaDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {

        List<Apple> apples = new ArrayList<>();

        apples.add(new Apple("A", 30));
        apples.add(new Apple("B", 20));
        apples.add(new Apple("C", 60));

        // JDK8之前
        Comparator<Apple> comparator1 = new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        };

        // 对apples按weight进行排序
        Collections.sort(apples, comparator1);

        for (Apple apple : apples) {
            System.out.println(apple);
        }

        // JDK8之后
        Comparator<Apple> comparator2 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

        // 对apples按weight进行排序
        Collections.sort(apples, comparator2);

        for (Apple apple : apples) {
            System.out.println(apple);
        }

        Comparator<Apple> comparator3 = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());

        // 对apples按weight进行排序
        Collections.sort(apples, comparator3);

        apples.stream().forEach((x) -> System.out.println(x));

        apples.stream().forEach(System.out::println);
    }

}