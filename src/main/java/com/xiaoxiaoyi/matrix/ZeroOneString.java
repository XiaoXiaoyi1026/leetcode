package com.xiaoxiaoyi.matrix;

/**
 * @author xiaoxiaoyi
 * 字符串只有0和1组成
 * 在一个这样的字符串中, 如果每一个0的左边都挨着一个1
 * 那么称该字符串为达标字符串
 * 给定字符串长度n, 问n长度的01字符串可能性中, 有多少种达标的
 * 可以通过打表法发现f(n) = f(n - 1) + f(n - 2), 同斐波拉契数列;
 */
public class ZeroOneString extends Fibonacci {

    public static int upToStandard1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n < 3) {
            return n;
        }
        // 第一个位置只能选1
        return process(n, 1, 1);
    }

    private static int process(int n, int pre, int cur) {
        if (cur == n) {
            // cur进行到了n, 说明找到了一种有效的
            return 1;
        }
        int res = 0;
        // 如果上一次选择的字符为0, 那么后续只有1一种选择
        res += process(n, 1, cur + 1);
        if (pre == 1) {
            // 如果前面选择了1, 那么后续可以选0或者1
            res += process(n, 0, cur + 1);
        }
        return res;
    }

    public static int upToStandard2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n < 3) {
            return n;
        }
        return Fibonacci.fibonacci(n + 1);
    }

}
