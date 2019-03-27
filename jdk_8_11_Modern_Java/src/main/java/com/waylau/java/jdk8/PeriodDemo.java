/**
 * Welcome to https://waylau.com
 */
package com.waylau.java.jdk8;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JDK8:Period.
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2019年1月15日
 */
public class PeriodDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {


    }

    @Test
    void testDuration() {

        LocalDate day1 = LocalDate.of(2015, 10, 2);
        LocalDate day2 = LocalDate.of(2019, 1, 3);

        Period period = Period.between(day1, day2);

        assertEquals(1, period.getDays());

        assertEquals(3, period.getMonths());

        assertEquals(3, period.getYears());
    }
}
