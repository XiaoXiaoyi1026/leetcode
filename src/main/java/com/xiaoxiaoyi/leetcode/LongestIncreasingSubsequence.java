package com.xiaoxiaoyi.leetcode;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author xiaoxiaoyi
 * 最长递增子序列问题
 */
public class LongestIncreasingSubsequence {

    public static int longestIncreasingSubsequence1(int[] array) {
        // 要第1个
        int ans1 = trialProcess(array, 1, array[0], 1);
        // 不要第1个
        int ans2 = trialProcess(array, 1, 0, 0);
        return Math.max(ans1, ans2);
    }

    /**
     * @param array  数组
     * @param cur    当前遍历到的下标
     * @param pre    前面过程中最后加入的数
     * @param preAns 前面过程中加入的长度
     */
    public static int trialProcess(@NotNull int[] array, int cur, int pre, int preAns) {
        if (cur == array.length) {
            return preAns;
        }
        int curNum = array[cur], ans1 = -1;
        if (curNum > pre) {
            // 可以加入当前数
            ans1 = trialProcess(array, cur + 1, curNum, preAns + 1);
        }
        // 不加当前数字
        int ans2 = trialProcess(array, cur + 1, pre, preAns);
        return Math.max(ans1, ans2);
    }

    public static int longestIncreasingSubsequence2(int[] array) {
        return dpProcess(array);
    }

    public static int dpProcess(@NotNull int[] array) {
        int n = array.length;
        // dp[i]代表必须以i结尾, 最长递增子序列的长度
        int[] dp = new int[n];
        // 第0个元素结尾最长为1
        dp[0] = 1;
        // 记录结果
        int ans = 0;
        for (int i = 1; i < n; i++) {
            // 记录i位置前面的最长递增自序列长度
            int max = 0;
            // 遍历当前位置前面的所有
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] < array[i]) {
                    // 如果前面某个位置的数 < 当前位置的数
                    // 比较一下最长的递增子序列
                    max = Math.max(max, dp[j]);
                }
            }
            // 当前位置的最长递增子序列为前面位置的最长递增子序列 + 1
            dp[i] = max + 1;
            // 更新结果
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    @Contract(pure = true)
    public static int longestIncreasingSubsequence3(@NotNull int[] array) {
        int n = array.length;
        /*
         ends[i]代表长度为i+1的递增子序列中, 最小的末尾数是多少
         ends中的数字一定递增
         因为长度为n的递增子序列的最小结尾一定>=长度为n - 1的递增子序列的最小结尾
         */
        int[] ends = new int[n];
        // 长度为1的递增子序列的最小结尾一开始就是array[0]
        ends[0] = array[0];
        // 记录ends中有效区的右边界
        int area = 0;
        for (int cur = 1; cur < array.length; cur++) {
            // 去ends的有效区域二分查找大于当前数字的最左位置
            int curNum = array[cur], start = 0, end = area, ans = -1;
            while (start <= end) {
                int mid = (start + end) >> 1;
                if (ends[mid] < curNum) {
                    // 小于当前数字, 去右边找
                    start = mid + 1;
                } else {
                    // 大于当前数字, 记一下答案然后尝试去左边找
                    ans = mid;
                    end = mid - 1;
                }
            }
            if (ans == -1) {
                // 说明没找到比当前数字大的, 扩充ends, 将当前数字加入
                ends[++area] = curNum;
            } else {
                // 如果找到了, 那么用当前的数字更新ans位置
                ends[ans] = curNum;
            }
        }
        // 因为area代表的是下标, 所以结果要 + 1
        return area + 1;
    }

}
