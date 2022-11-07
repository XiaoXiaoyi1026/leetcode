package com.xiaoxiaoyi.bit;

/**
 * @author xiaoxiaoyi
 * 使用位运算实现加、减、乘、除运算
 */
public class Operation {

    /**
     * num1 + num2溢出不用管
     */
    public static int add(int num1, int num2) {
        int sum = num1;
        // num2存储进位信息
        while (num2 != 0) {
            // 无进位相加
            sum = num1 ^ num2;
            // 进位信息
            num2 = (num1 & num2) << 1;
            num1 = sum;
        }
        return sum;
    }

    /**
     * 取负
     */
    public static int negative(int num) {
        // 一个数的负数等于它取反 + 1
        return add(~num, 1);
    }

    /**
     * 减去一个数字 = 加上它的负数
     */
    public static int sub(int num1, int num2) {
        return add(num1, negative(num2));
    }

    /**
     * 乘法
     * 不考虑溢出
     */
    public static int multiply(int num1, int num2) {
        int ans = 0;
        while (num2 != 0) {
            // ans = ans + (num2 & 1) * num1 左移 move位
            if ((num2 & 1) == 1) {
                ans = add(ans, num1);
            }
            // num2逻辑右移1位, 左边用符号位补
            num2 >>>= 1;
            // num1左移1位
            num1 <<= 1;
        }
        return ans;
    }

    /**
     * 判断num是否为负
     */
    public static boolean isNegative(int num) {
        return num < 0;
    }

    /**
     * 除法
     */
    public static int div(int num1, int num2) {
        if (num2 == 0) {
            throw new RuntimeException("Divide by zero");
        }
        // 保证是2个正数再相除
        int x = isNegative(num1) ? negative(num1) : num1;
        int y = isNegative(num2) ? negative(num2) : num2;
        int res = 0;
        for (int i = 31; i > -1; i = sub(i, 1)) {
            if ((x >> i) >= y) {
                // 如果x右移i位够除y
                res |= (1 << i);
                // x = x - y左移i位的值
                x = sub(x, y << i);
            }
        }
        // 如果两个数的符号相同, 则直接返回, 不相同则结果取负
        return isNegative(num1) ^ isNegative(num2) ? negative(res) : res;
    }

}
