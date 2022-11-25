package com.xiaoxiaoyi.random;

/**
 * @author 20609
 * 等概率返回范围内的随机整数
 */
public class RandomRange {

    /**
     * 原始函数, 等概率返回1~5
     */
    public static int f() {
        return (int) (Math.random() * 5) + 1;
    }

    /**
     * 01发生器函数, 由f函数加工出等概率返回0和1
     */
    public static int f2() {
        int tmp;
        do {
            tmp = f();
        } while (tmp == 3);
        return tmp < 3 ? 0 : 1;
    }

    /**
     * 目标函数, 由f2函数加工出1~7等概率返回
     */
    public static int g() {
        int ans;
        do {
            ans = (f2() << 2) | (f2() << 1) | f2();
            // 0~6
        } while (ans > 6);
        return ans + 1;
    }

    /**
     * 0.83概率返回0, 0.17返回1
     * 使用这个不公平随机加工出公平随机
     */
    public static int rand01p() {
        double p = 0.83;
        return Math.random() < p ? 0 : 1;
    }

    /**
     * 从rand01p加工出等概率01发生器
     */
    public static int rand01() {
        int ans1, ans2;
        do {
            ans1 = rand01p();
            ans2 = rand01p();
        } while ((ans1 ^ ans2) == 0);
        // 只可能产生10或者01, 10返回1, 01返回0
        return ans1;
    }

}
