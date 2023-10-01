package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomUtils;

import java.util.Arrays;

/**
 * 火柴拼正方形问题
 * 火柴数量不超过15, 每根火柴的长度[1, 10^8]
 * 所有火柴都必须使用, 问能否拼出一个正方形, 如果可以, 返回true, 否则返回false
 */
public class MatchStickToSquare {

    public static boolean makeSquare(int[] matchSticks) {
        if (matchSticks == null || matchSticks.length == 0) {
            return false;
        }
        int sum = 0;
        for (int matchStick : matchSticks) {
            sum += matchStick;
        }
        if (sum % 4 != 0) {
            return false;
        }
        return process(matchSticks, 0, 0, sum >> 2, 4);
    }

    /**
     * @param matchSticks 火柴长度数组
     * @param cur         当前边已经拼出的长度
     * @param status      火柴的使用情况
     * @param target      目标边长
     * @param edges       剩余几条边还没拼好
     * @return 能否在使用所有火柴的情况下拼出正方形
     */
    private static boolean process(int[] matchSticks, int cur, int status, int target, int edges) {
        if (edges == 0) {
            return status == (1 << matchSticks.length) - 1;
        }
        // 遍历每根火柴
        boolean ans = false;
        for (int i = 0; i < matchSticks.length; i++) {
            // 如果当前火柴没用过且用上当前火柴拼出的长度不会超出目标边长, 那么当前火柴可用
            if ((status & (1 << i)) == 0 && cur + matchSticks[i] <= target) {
                if (cur + matchSticks[i] == target) {
                    ans = process(matchSticks, 0, status | (1 << i), target, edges - 1);
                } else {
                    ans = process(matchSticks, cur + matchSticks[i], status | (1 << i), target, edges);
                }
            }
            if (ans) {
                break;
            }
        }
        return ans;
    }

    /**
     * @param matchSticks 火柴的长度数组
     * @return 能否拼出正方形
     */
    public static boolean makeSquare2(int[] matchSticks) {
        if (matchSticks == null || matchSticks.length == 0) {
            return false;
        }
        int sum = 0;
        for (int matchStick : matchSticks) {
            sum += matchStick;
        }
        if (sum % 4 != 0) {
            return false;
        }
        return process2(matchSticks, 0, 0, sum >> 2, 4, new int[(1 << matchSticks.length)]);
    }

    /**
     * @param matchSticks 火柴的长度
     * @param cur         当前拼出的长度
     * @param status      火柴的使用情况
     * @param target      目标长度
     * @param edges       剩余边数
     * @param cache       缓存
     * @return 能否拼出正方形
     */
    private static boolean process2(int[] matchSticks, int cur, int status, int target, int edges, int[] cache) {
        if (edges == 0) {
            // base case
            return status == (1 << matchSticks.length) - 1;
        }
        if (cache[status] != 0) {
            // 如果缓存中的当前状态已经计算过了, 那么直接返回
            return cache[status] == 1;
        }
        boolean ans = false;
        for (int i = 0; i < matchSticks.length; i++) {
            if ((status & (1 << i)) == 0 && (cur + matchSticks[i] <= target)) {
                if (cur + matchSticks[i] == target) {
                    ans = process2(matchSticks, 0, status | (1 << i), target, edges - 1, cache);
                } else {
                    ans = process2(matchSticks, cur + matchSticks[i], status | (1 << i), target, edges, cache);
                }
            }
            if (ans) {
                break;
            }
        }
        cache[status] = ans ? 1 : -1;
        return ans;
    }

    public static boolean makeSquare3(int[] matchSticks) {
        if (matchSticks == null || matchSticks.length == 0) {
            return false;
        }
        int sum = 0;
        for (int matchStick : matchSticks) {
            sum += matchStick;
        }
        if (sum % 4 != 0) {
            return false;
        }
        // dp[i]代表的是以i这种状态为起始, 能否拼出正方形
        int[] dp = new int[(1 << matchSticks.length)];
        int n = dp.length;
        // 在所有火柴都选过了的情况下, 可以拼出正方形, base case
        dp[n - 1] = 1;
        // 当前边拼出来了的长度, 可以根据status求得
        int cur;
        // 剩余需要满足的边数, 可以根据status求得
        int edges;
        // 临时变量, 用于记录选了火柴后的边数变化
        int curEdges;
        // 正方形的边长
        int target = sum >> 2;
        // 临时变量, 用于记录选了火柴后的状态变化
        int curStatus;
        // 遍历每一种火柴的选择状态
        for (int status = n - 2; status >= 0; status--) {
            // 默认该种状态拼不出正方形
            dp[status] = -1;
            // 从所有火柴都被选中了的状态往下减
            cur = sum;
            // 由于status是循环变量, 不可变, 所以用临时变量进行遍历
            curStatus = status;
            for (int i = 0; i < matchSticks.length; i++) {
                if ((status & (1 << i)) == 0) {
                    // 如果当前这根火柴在status状态下是没被选中的, 那么cur就应该减去这根火柴的长度
                    cur -= matchSticks[i];
                    // 标记这根火柴判断过了
                    curStatus |= (1 << i);
                    if (curStatus == n - 1) {
                        // 如果所有火柴都判断过了, 直接退出循环
                        break;
                    }
                }
            }
            // 求出边数和当前的长度
            edges = 4 - (cur / target);
            cur %= target;
            // 边数计入临时变量
            curEdges = edges;
            // 在当前status下去遍历每一根火柴
            for (int i = 0; i < matchSticks.length; i++) {
                if ((status & (1 << i)) == 0 && cur + matchSticks[i] <= target) {
                    // 如果当前火柴可选, 则当前状态应该或上(1 << i)
                    curStatus = status | (1 << i);
                    if (cur + matchSticks[i] == target) {
                        // 刚好凑够一条边, 边数-1
                        curEdges--;
                        if (curEdges == 0) {
                            // base case
                            dp[status] = curStatus == n - 1 ? 1 : -1;
                        } else {
                            dp[status] = dp[curStatus];
                        }
                    } else {
                        dp[status] = dp[curStatus];
                    }
                }
                if (dp[status] == 1) {
                    // 如果已经可以拼出正方形, 那么就直接退出循环
                    break;
                } else {
                    // 如果要判断下一种可能性, 则恢复临时变量
                    curEdges = edges;
                }
            }
        }
        // 返回在所有火柴都没选的情况下, 可不可以拼出正方形
        return dp[0] == 1;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int maxLength = 15;
        int maxValue = 25;
        int[] matchSticks;
        System.out.println("测试开始!!!");
        for (int i = 0; i < testTimes; i++) {
            matchSticks = RandomUtils.array(maxLength, maxValue, 1, true);
            boolean res1 = makeSquare(matchSticks);
            boolean res2 = makeSquare2(matchSticks);
            boolean res3 = makeSquare3(matchSticks);
            if (res1 != res2 || res2 != res3) {
                System.out.println("oops!!!");
                System.out.println("matchSticks: " + Arrays.toString(matchSticks));
                System.out.println("res1: " + res1);
                System.out.println("res2: " + res2);
                System.out.println("res3: " + res3);
                break;
            }
        }
        System.out.println("测试结束!!!");
    }

    private static void test(int[] matchSticks) {
        System.out.println(makeSquare(matchSticks));
        System.out.println(makeSquare2(matchSticks));
        System.out.println(makeSquare3(matchSticks));
    }

}
