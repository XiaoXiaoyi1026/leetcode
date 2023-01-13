package com.xiaoxiaoyi.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoxiaoyi
 */
public class TargetSum {

    /**
     * 方法1: 暴力尝试
     *
     * @param nums   原数组
     * @param target 目标
     * @return arr每个数前面添加加号或者减号能使得表达式结果等于target的方法数
     */
    public static int findTargetSumWays1(int[] nums, int target) {
        return process(nums, 0, target);
    }

    /**
     * @param nums  原数组
     * @param index 当前选择到的arr下标
     * @param rest  要达成的目标
     * @return 可以使用arr[index] ~ 最后的所有数字, 每一个数组在前面添+或-能够得到rest的方法数
     */
    private static int process(int[] nums, int index, int rest) {
        // base case
        if (index == nums.length) {
            // 选完所有数字后, 如果要达成的目标时0, 则可以达成
            return rest == 0 ? 1 : 0;
        }
        // nums[index]前选加号或者减号2种方法达成目标的总方法数
        return process(nums, index + 1, rest + nums[index]) +
                process(nums, index + 1, rest - nums[index]);
    }

    /**
     * 方法2: 记忆化搜索
     *
     * @param nums   原数组
     * @param target 目标
     * @return arr每个数前面添加加号或者减号能使得表达式结果等于target的方法数
     */
    public static int findTargetSumWays2(int[] nums, int target) {
        return process(nums, 0, target, new HashMap<>());
    }

    private static int process(int[] nums, int index, int rest, Map<Integer, Map<Integer, Integer>> cache) {
        if (cache.containsKey(index) && cache.get(index).containsKey(rest)) {
            // 先查缓存, 如果缓存命中则直接返回结果
            return cache.get(index).get(rest);
        }
        int ans;
        if (index == nums.length) {
            ans = rest == 0 ? 1 : 0;
        } else {
            ans = process(nums, index + 1, rest + nums[index], cache) +
                    process(nums, index + 1, rest - nums[index], cache);
        }
        cache.computeIfAbsent(index, k -> new HashMap<>());
        // 将计算结果加入缓存
        cache.get(index).put(rest, ans);
        return ans;
    }

    /**
     * 方法3: 严格表结构动态规划
     * 优化点1: 由于每个数前面要加上+或者-, 所以方法数和数的正负无关, 可以全部看成正数
     * 优化点2: 数组中所有数变成非负后, 求出sum, 如果target>sum或者target<-sum, 那么没有方法达成目标
     * 优化点3: 由于加减并不改变结果的奇偶性, 就是说 a + b 和 a - b 的结果奇偶性相同, 那么可以知道target和sum如果奇偶性不同, 则无法达成目标
     * 优化点4: 假设最终前面添+号的数组成的集合为P, 添-号组成的集合为Q
     * 那么target = sum(P) - sum(Q), 两边同时加上sum(P) + sum(Q)
     * 得到: target + sum(P) + sum(Q) = 2 * sum(P)
     * target + sum = 2 * sum(P)
     * sum(P) = (target + sum) / 2
     * 即如果有一个集合P能够使得上面的等式成立, 那么必然可以达成结果, 至此原问题转化为了纯背包问题
     * 由于-sum <= target <= sum, 所以 0 <= sum(P) <= sum
     * 优化点5: 由严格位置依赖可以优化为一维数组滚动更新得到结果
     * 从暴力递归的过程可以得到结果只和index与rest有关
     * index的变化范围从0~nums.length
     *
     * @param nums   原数组
     * @param target 目标
     * @return arr每个数前面添加加号或者减号能使得表达式结果等于target的方法数
     */
    public static int findTargetSumWays3(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 数组全是正数, 则-sum <= target <= sum, 然后还要求sum和target的奇偶性一致, 这条件一个不满足于就返回0, 都满足则去找子数组满足和 = (target + sum) / 2的有几个
        return target > sum || target < -sum || ((sum & 1) ^ (target & 1)) != 0 ? 0 : subset(nums, (target + sum) >> 1);
    }

    private static int subset(int[] nums, int sum) {
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = sum; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }
        return dp[sum];
    }

}