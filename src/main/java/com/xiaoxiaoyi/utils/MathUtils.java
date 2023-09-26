package com.xiaoxiaoyi.utils;

/**
 * 数学相关工具类
 */
public class MathUtils {

    private MathUtils() {

    }

    public static long pow(long base, long exp) {
        long result = 1;
        long tmp = base;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result *= tmp;
            }
            exp >>= 1;
            tmp *= base;
        }
        return result;
    }

}
