package com.xiaoxiaoyi.bit;

/**
 * @author xiaoxiaoyi
 * 位运算, 不用比较返回2个有符号整数中的较大值
 */
public class GetMax {

    public static int bitCompare(int num1, int num2) {
        // 可能会溢出, 导致结果错误
        int dif = num1 - num2;
        // 如果num1 >= num2 则 dif >= 0 getSign 返回 1 否则返回 0
        int sign = getSign(dif);
        // sign = 1 则 re = 0, 否则 re = 1;
        int re = flip(sign);
        // 如果num1 >= num2, 则sign = 1, re = 0, 返回的就是num1
        // 如果num1 < num2, 则sign = 0, re = 1, 返回的就是num2
        return num1 * sign + num2 *  re;
    }

    public static int bitCompare2(int num1, int num2) {
        int dif = num1 - num2;
        int signNum1 = getSign(num1);
        int signNum2 = getSign(num2);
        int signDif = getSign(dif);
        // 2个数的符号相同为0, 不同为1
        int difSign = signNum1 ^ signNum2;
        // 2个数符号相同为1, 不同为0
        int sameSign = flip(difSign);
        // 如果a和b符号不一样, 如果num1 >= 0 返回num1
        // 如果a和b符号一样, 如果差值 >= 0 返回num1
        int returnNum1 = difSign * signNum1 + sameSign * signDif;
        // 否则返回num2
        int returnNum2 = flip(returnNum1);
        return num1 * returnNum1 + num2 * returnNum2;
    }

    /**
     * 返回一个数字的符号位
     * 非负数返回1
     * 负数返回0
     */
    private static int getSign(int num) {
        return flip((num >> 31) & 1);
    }

    /**
     * 异或
     * 0 -> 1
     * 1 -> 0
     */
    private static int flip(int n) {
        return n ^ 1;
    }

}
