package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 将一个数分解为多个数, 要求多个数都为正数且非降序, 问有几种分裂方式
 * 比如4: 可以分裂成 {1, 1, 1, 1}, {1, 1, 2}, {1, 3}, {2, 2}, {4} 5种
 */
public class SplitNumber {

    public static int schemes(int num) {
        if (num < 1) {
            return 0;
        }
        // 裂开的第一部分从1开始, 即保证裂开的每一个数不小于1
        return process(1, num);
    }

    /**
     * @param pre  之前分裂出去的那个数
     * @param rest 剩余需要进行分裂的数
     * @return 裂开的方法数
     */
    private static int process(int pre, int rest) {
        if (rest == 0) {
            // base case: 当剩下的数为0时, 代表找到了1种有效的分裂
            return 1;
        }
        if (pre > rest) {
            // 如果之前分裂出的数已经比剩余的数还大了, 那么剩余的数无法再分裂出大于前数的了, 返回0
            return 0;
        }
        // 从rest中分裂出大于等于pre的部分split
        int ans = 0;
        // 存在枚举行为(斜率优化)
        for (int split = pre; split <= rest; split++) {
            ans += process(split, rest - split);
        }
        return ans;
    }

    public static int schemes2(int num) {
        if (num < 1) {
            return 0;
        }
        /*
        根据递归暴力递归过程分析pre和rest的变化范围
        1 <= pre <= num, 0 <= rest <= num
        由此构建dp表, 由范围分析可知, pre == 0时无效
         */
        int[][] dp = new int[num + 1][num + 1];
        /*
        研究base case: 当rest == 0时, dp[pre][rest] = 1
        当pre > rest时, dp[pre][rest] = 0
        当pre == 0时, 无效
         */
        for (int pre = 1; pre <= num; pre++) {
            dp[pre][0] = 1;
        }
        /*
        研究普遍位置的依赖情况: dp[i][j]代表pre == i, 且rest == j的解
        假设i<=j, 那么split的范围为i <= split <= j
        根据暴力递归的过程可以推导出转移方程为:
        dp[i][j] = dp[i][j-i] + dp[i+1][j-i-1] ... + dp[j][0]
        由base case可知: dp[j][0] = 1
        根据依赖关系可以得出填表顺序: 从下往上, 从左往右
        num = n - 1
         */
        for (int pre = num; pre > 0; pre--) {
            // pre从下往上
            for (int rest = pre; rest <= num; rest++) {
                // rest从左往右
                for (int split = pre; split <= rest; split++) {
                    // 枚举split
                    dp[pre][rest] += dp[split][rest - split];
                }
            }
        }
        return dp[1][num];
    }

    public static int schemes3(int num) {
        if (num < 1) {
            return 0;
        }
        /*
        根据递归暴力递归过程分析pre和rest的变化范围
        1 <= pre <= num, 0 <= rest <= num
        由此构建dp表, 由范围分析可知, pre == 0时无效
         */
        int[][] dp = new int[num + 1][num + 1];
        /*
        研究base case: 当rest == 0时, dp[pre][rest] = 1
        当pre > rest时, dp[pre][rest] = 0
        当pre == 0时, 无效
         */
        for (int pre = 1; pre <= num; pre++) {
            dp[pre][0] = 1;
        }
        /*
        研究普遍位置的依赖情况: dp[i][j]代表pre == i, 且rest == j的解
        假设i<=j, 那么split的范围为i <= split <= j
        根据暴力递归的过程可以推导出转移方程为:
        dp[i][j] = dp[i][j-i] + dp[i+1][j-i-1] + ... + dp[j][0]
        由base case可知: dp[j][0] = 1
        根据依赖关系可以得出填表顺序: 从下往上, 从左往右
        当pre == num时, rest只能取0, 而dp[num][0]为1
         */
        dp[num][num] = 1;
        for (int pre = num - 1; pre > 0; pre--) {
            // pre从下往上, rest从对角线开始往右
            for (int rest = pre; rest <= num; rest++) {
                /*
                斜率优化:
                dp[i][j] = dp[i][j-i] + dp[i+1][j-i-1] + ... + dp[j][0]
                dp[i+1][j] = dp[i+1][j-i-1] + ... + dp[j][0]
                所以dp[i][j]可以表示为dp[i][j-i] + dp[i+1][j]
                 */
                dp[pre][rest] = dp[pre][rest - pre] + dp[pre + 1][rest];
            }
        }
        return dp[1][num];
    }

}
