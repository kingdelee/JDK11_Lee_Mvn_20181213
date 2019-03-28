/**
 * Welcome to https://waylau.com
 */
package com.waylau.java.jdk8;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2019年1月15日
 */
public class DurationDemo {

    /**
     *
     */
    public DurationDemo() {

    }

    @Test
    void testDuration() {

        Instant instant = Instant.ofEpochSecond(60 * 24L); // 1440秒

        Instant instant2 = Instant.ofEpochSecond(60 * 25L); // 1500秒

        Duration duration = Duration.between(instant, instant2); // 60

        assertEquals(60, duration.getSeconds());

        // 累加秒
        Duration duration2 = duration.plusSeconds(100);
        assertEquals(60 + 100, duration2.getSeconds());

        // 累加日
        Duration duration3 = duration.plusDays(1);
        assertEquals(60 + 24 * 60 * 60, duration3.getSeconds());
    }
}
