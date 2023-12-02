package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * 给定一个01数组, 一定要翻转一个非空区间, 翻转使得区间范围内0变为1, 1变为0
 * 返回翻转后1的最多数量
 */
public class MaxOneNumbers {

    public static int maxOneNumbers2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int oneNumbers = 0;
        for (int i = 0; i < arr.length; i++) {
            oneNumbers += arr[i]; // 记录1的个数
            arr[i] = arr[i] == 0 ? 1 : -1; // 原始数组0变为1, 1变为-1
        }
        int max = 0;
        int ans = 0;
        for (int num : arr) { // 0变1, 1变-1后数组的任意区间和的最大值就是任意区间内0和1数量差的最大值
            ans += num;
            max = Math.max(max, ans); // 记录最大的差值
            if (ans < 0) {
                ans = 0;
            }
        }
        return oneNumbers + (max == 0 ? -1 : max); // 返回最大的1的个数, 如果ans == 0说明每个区间内0和1的数量差都为0, 由于必须翻转那么结果要-1
    }

    /**
     * 01数组翻转后1的最多个数
     *
     * @param arr 01数组
     * @return 1的最多个数
     */
    public static int maxOneNumbers(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int ans = 0;
        for (int start = 0; start < n; start++) {
            for (int end = start + 1; end <= n; end++) {
                reverse(arr, start, end);
                ans = Math.max(ans, countOne(arr));
                reverse(arr, start, end); // 恢复数组状态, 下次循环可以使用上次的状态
            }
        }
        return ans; // 返回最大的1的个数
    }

    /**
     * 统计01数组中1的个数
     *
     * @param arr 01数组
     * @return 1的个数
     */
    @Contract(pure = true)
    public static int countOne(@NotNull int[] arr) {
        int ans = 0;
        for (int i : arr) {
            ans += i;
        }
        return ans;
    }

    /**
     * 翻转01数组
     *
     * @param arr   01数组
     * @param start 起始位置
     * @param end   结束位置
     */
    public static void reverse(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            arr[i] ^= 1; // 1 ^ 1 = 0, 0 ^ 1 = 1
        }
    }

    public static void main(String[] args) {
        int length = 100;
        int testTimes = 10000;
        int[] arr;
        int ans1;
        int ans2;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            arr = RandomUtils.array(length, 1, 0, true); // 生成01数组
            ans1 = maxOneNumbers(arr);
            ans2 = maxOneNumbers2(arr);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                System.out.println("arr = " + Arrays.toString(arr));
                return;
            }
        }
        System.out.println("test end");
    }
}
