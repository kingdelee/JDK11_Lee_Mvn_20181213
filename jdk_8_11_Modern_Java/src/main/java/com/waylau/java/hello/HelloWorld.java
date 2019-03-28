/**
 * Welcome to https://waylau.com
 */
package com.waylau.java.hello;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Hello World
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2018年12月22日
 */
public class HelloWorld {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    /*
     * 第一个JUnit5测试用例
     */
    @Test
    void testUnit() {
        String name = "Way Lau";
        assertEquals("Way Lau", name);
    }

}
