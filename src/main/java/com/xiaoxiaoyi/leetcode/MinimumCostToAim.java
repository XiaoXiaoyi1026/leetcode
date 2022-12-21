package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 达成目标的最少代价(使用平凡解或者从题目中挖掘出更多限制)
 */
public class MinimumCostToAim {

    /**
     * 让cur变成aim的最少代价
     *
     * @param add   方法1, 可以使cur + 2, 代价是way1
     * @param times 方法2, 可以使cur * 2, 代价是way2
     * @param del   方法3, 可以使cur - 2, 代价是way3
     * @param cur   初始值
     * @param aim   目标值
     * @return 最少代价
     */
    public static int costToAim(int add, int times, int del, int cur, int aim) {
        if (aim == cur) {
            return 0;
        }
        // 差距不能大于目标的2倍
        int aimLimit = aim << 1;
        // 差距 / 2为平凡解, 那么解的花费限制应该为 平凡解 * del
        int costLimit = ((aim - cur) >>> 1) * add;
        return trialProcess(add, times, del,
                cur, aim, 0,
                aimLimit, costLimit);
    }

    /**
     * @param cur       当前来到的人去
     * @param cost      当前花费的代价
     * @param aimLimit  目的限制
     * @param costLimit 花费限制
     */
    public static int trialProcess(int add, int times, int del,
                                   int cur, int aim, int cost,
                                   int aimLimit, int costLimit) {
        if (cur == aim) {
            return cost;
        }
        if (cur < 0 || cur >= aimLimit || cost > costLimit) {
            // 如果当前的值或者花费超出了限制, 直接判断无解
            return Integer.MAX_VALUE;
        }
        // 分别尝试3种方法
        int ans = trialProcess(add, times, del,
                cur, aim - 2, cost + add,
                aimLimit, costLimit);
        if ((aim & 1) == 0) {
            ans = Math.min(ans,
                    trialProcess(add, times, del,
                            cur, aim >>> 1, cost + times,
                            aimLimit, costLimit)
            );
        }
        ans = Math.min(ans,
                trialProcess(add, times, del,
                        cur, aim + 2, cost + del,
                        aimLimit, costLimit)
        );
        // 返回弥补gap差距的最小方法
        return ans;
    }

    public static int costToAim2(int add, int times, int del, int cur, int aim) {
        if (aim == cur) {
            return 0;
        }
        // 差距不能大于目标的2倍
        int aimLimit = aim << 1;
        // 差距 / 2为平凡解, 那么解的花费限制应该为 平凡解 * way3
        int costLimit = ((aim - cur) >>> 1) * add;
        return dpProcess(add, times, del, cur, aim, aimLimit, costLimit);
    }

    /**
     * 2个可变参数, 当前值cur和当前花费的代价cost
     * cur的变化范围为 0 ~ aimLimit - 1, 依赖cur + 2, cur - 2 和 cur << 1
     * cost的变化范围从 0 ~ costLimit, 递增
     * 可以发现cost只依赖cost+add, cost+times, cost+del
     */
    public static int dpProcess(int add, int times, int del,
                                int cur, int end, int aimLimit, int costLimit) {
        // cur到不了aimLimit, cost可以达到costLimit
        int[][] dp = new int[costLimit + 1][aimLimit];
        for (int cost = 0; cost <= costLimit; cost++) {
            for (int aim = 0; aim < aimLimit; aim++) {
                // base case, 当aim靠到cur时, 返回有效值cost, 其余位置均无效
                dp[cost][aim] = aim == cur ? cost : Integer.MAX_VALUE;
            }
        }
        for (int cost = costLimit; cost >= 0; cost--) {
            for (int aim = 0; aim < aimLimit; aim++) {
                if (aim - 2 > 0 && cost + add <= costLimit) {
                    // 让cur+2等价于让aim-2
                    dp[cost][aim] = Math.min(dp[cost][aim], dp[cost + add][aim - 2]);
                }
                if ((aim & 1) == 0 && (aim >>> 1) > 0 && cost + times <= costLimit) {
                    dp[cost][aim] = Math.min(dp[cost][aim], dp[cost + times][aim >>> 1]);
                }
                if (aim + 2 < aimLimit && cost + del <= costLimit) {
                    dp[cost][aim] = Math.min(dp[cost][aim], dp[cost + del][aim + 2]);
                }
            }
        }
        return dp[0][end];
    }
}