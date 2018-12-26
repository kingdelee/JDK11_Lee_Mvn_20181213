package com.lee.test.jdk11.util;

import org.junit.jupiter.api.Test;

public class MathUtil {

    /**
     * 输出32位2进制数
     *
     * @return
     */
    public String get2X32(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 31; i >= 0; i--)
            sb.append(num >>> i & 1);
        return sb.toString();
    }

    @Test
    public void t1() {
        System.out.println(Integer.toBinaryString(16));
        System.out.println(get2X32(16));
    }

}
