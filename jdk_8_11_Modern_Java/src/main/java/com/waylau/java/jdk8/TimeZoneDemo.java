/**
 * Welcome to https://waylau.com
 */
package com.waylau.java.jdk8;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JDK8:TimeZone.
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2019年1月15日
 */
public class TimeZoneDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 区域标识
        ZoneId romeZone = ZoneId.of("Europe/Rome");

        // TimeZone转为ZoneId
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
    }

    @Test
    void testTimeZone() {
        // 区域标识
        ZoneId romeZone = ZoneId.of("Europe/Rome");

        // 实例化2019-1-21日期
        LocalDate date = LocalDate.of(2019, 1, 21);
        assertEquals("2019-01-21", date.toString());

        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
        assertEquals("2019-01-21T00:00+01:00[Europe/Rome]", zdt1.toString());

        // 实例化时间日期
        LocalDateTime dt1 = LocalDateTime.of(2019, 1, 21, 13, 45, 20);
        ZonedDateTime zdt2 = dt1.atZone(romeZone);
        assertEquals("2019-01-21T13:45:20+01:00[Europe/Rome]", zdt2.toString());

        Instant instant = Instant.ofEpochSecond(60 * 24L); // 1440秒
        ZonedDateTime zdt3 = instant.atZone(romeZone);
        assertEquals("1970-01-01T01:24+01:00[Europe/Rome]", zdt3.toString());

        // UTC/GMT
        ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");
        OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(dt1, newYorkOffset);
        assertEquals("2019-01-21T13:45:20-05:00", dateTimeInNewYork.toString());
    }
}
