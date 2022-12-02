package com.xiaoxiaoyi.greedy;

/**
 * @author xiaoxiaoyi
 * 初始化定义s = "a",m = s
 * 给定2个操作:
 * operation1: m = s; s = s + s;
 * operation2: s = s + m;
 * 求最小的操作步骤数, 可以使得s字符换拼接到长度n
 * 分析: 如果n为质数, 则使用n - 1次操作2就是最优解
 * 假设s = k * "a", 那么使用操作1会使得: m = k * "a", s = 2k * "a";
 * 往后无论调用那一个操作, 拼出来的长度l都会以k为因子
 * 如果n为质数, 则n的因子就只有1和n, 不会包含k, 所以无法拼出n
 * 如果n不为质数, 则假设n的质数因子为i * j * x * y
 * 即n = i * j * x * y, 那么要凑成i(质数)长度就得调用i次操作2, 而后复制成j份...
 * 例如n = 21 = 3 * 7 时, 要么先调用3次操作2, 然后复制7次, 要么调用7次操作2, 然后复制3次
 * 假设n的最优操作顺序就是i * j * x * y
 * 假设前面(i * j * x)个a已经搞定, 那么要复制y(质数)份, 则最优解就是调用y - 1次操作2
 * 以此类推, 则最终的操作次数为(i - 1) + (j - 1) + (x - 1) + (y - 1)次
 * = sum(质数因子) - count(质数因子)
 */
public class MinimumSteps {

    /**
     * @param n 合数
     * @return n的质数因子的和与数量
     */
    private static int[] primeNumbersSumAndCount(int n) {
        int sum = 0, count = 0;
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                // 找到1个质数因子
                sum += i;
                count++;
                n /= i;
            }
        }
        return new int[]{sum, count};
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int minimumSteps(int n) {
        if (n < 2) {
            return 0;
        }
        if (isPrime(n)) {
            // 如果n是质数
            return n - 1;
        }
        int[] sumAndCount = primeNumbersSumAndCount(n);
        return sumAndCount[0] - sumAndCount[1];
    }

}
