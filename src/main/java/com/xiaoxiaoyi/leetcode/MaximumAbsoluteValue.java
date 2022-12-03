package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 刚给你一个数组, 要求你从某个位置将该数组分成2个,
 * 使得左边数组的最大值减去右边数组的最大值的绝对值最大并返回
 * 分析: 可以优化为O(1)复杂度的算法
 * 根据题目要求, 如果先从左往右遍历一遍数组, 找到其最大值
 * 然后分情况讨论: 如果最大值被划分到左边, 那么左边的max可以确定
 * 此时问题转化为如何切能让右边的最大值最小, 因为右边一定会包含arr[n-1]
 * 所以让右边只包含arr[n-1]可以使得右边的最大值为固定的最小值arr[n-1]
 * 同理如果让数组最大值划分到右边, 那么就要让左边的最大值最小
 * 因为左边一定会包含arr[0], 所以只让左边包含arr[0]即可
 */
public class MaximumAbsoluteValue {

    public static int maximumAbsoluteValue(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }

}
