package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 摆动排序问题: 给定一个无序数组arr, 要求将原数组变成num1<=num2>=num3<=num4...
 * 要求算法的额外空间复杂度为O(1)
 */
public class WiggleSort extends PerfectShuffle {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 堆排序, 空间复杂度O(1)
        HeapSort.sort(arr);
        int n = arr.length;
        if ((n & 1) == 0) {
            // 数组长度为偶数, 则直接交换前半部分较小的数和后半部分较大的数
            exchange(arr, 0, (n >> 1) - 1, n >> 1, n - 1);
            // 然后进行完美洗牌即可
            shuffle(arr);
        } else {
            // 如果数组长度为奇数, 则0位置的数不动, 后面1~n-1部分进行一次完美洗牌即可
            shuffle(arr, 1, n - 1);
        }
    }
}
