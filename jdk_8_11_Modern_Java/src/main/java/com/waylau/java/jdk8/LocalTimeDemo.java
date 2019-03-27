/**
 * Welcome to https://waylau.com
 */
package com.waylau.java.jdk8;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JDK8:LocalTime
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2019年1月9日
 */
public class LocalTimeDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        System.out.println(now);
    }

    @Test
    void testLocalTime() {
        // 实例化时间
        LocalTime time = LocalTime.of(13, 45, 20);

        // 获取小时
        int hour = time.getHour();
        assertEquals(13, hour);

        // 获取分钟
        int minute = time.getMinute();
        assertEquals(45, minute);

        // 获取秒
        int second = time.getSecond();
        assertEquals(20, second);
    }

}
