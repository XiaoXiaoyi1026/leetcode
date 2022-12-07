package com.xiaoxiaoyi.other;

/**
 * @author xiaoxiaoyi
 * 最多的装备包方法, 给定背包容量n, 给定一个数组代表每个零食的体积
 * 问一共有多少种将零食装进背包的方法（不装任何零食也算一种方法）
 */
public class MostPackingMethods {

    public static int mostPackingMethods(int[] arr, int n) {
        int m = arr.length;
        int[][] dp = new int[m + 1][n + 1];
        // cur记录当前遍历到哪个零食, dosage记录遍历到当前零食的空间用量
        int cur, dosage;
        for (cur = 0; cur <= m; cur++) {
            // base case 不装任何零食的方法为1
            dp[cur][0] = 1;
        }
        for (cur = 1; cur <= m; cur++) {
            for (dosage = 0; dosage <= n; dosage++) {
                /*
                选择到当前零食, 要刚好装够dosage容量
                1. 不装当前零食, 那么就需要前面的0...cur-1个达成dosage的目标
                2. 装当前零食, 那么就需要前面的0...cur-1个达成dosage - arr[cur]的目标
                 */
                dp[cur][dosage] = dp[cur - 1][dosage];
                if (dosage - arr[cur - 1] >= 0) {
                    dp[cur][dosage] += dp[cur - 1][dosage - arr[cur - 1]];
                }
            }
        }
        int res = 0;
        for (dosage = 0; dosage <= n; dosage++) {
            res += dp[m][dosage];
        }
        return res;
    }
}
