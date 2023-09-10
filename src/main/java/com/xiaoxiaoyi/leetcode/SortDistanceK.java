package com.xiaoxiaoyi.leetcode;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 每个数距离最终的位置距离(下标差值)不超过k
 * @date 3/22/2023 5:00 PM
 */
public class SortDistanceK {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 2, 7, 6, 5, 8};
        headSort(arr, 3);
        System.out.println(Arrays.toString(arr));
    }

    public static void headSort(@NotNull int[] nums, int k) {
        // 申请一个k + 1大小的小根堆(优先级队列)
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k + 1);
        int n = nums.length;
        int index = 0;
        // 先把数组先k+1个数放入小根堆
        for (; index < Math.min(n, k + 1); index++) {
            priorityQueue.add(nums[index]);
        }
        // 然后每次弹出一个数放到有序区(不包含)的最后一个位置
        int youXu = 0;
        for (; index < n; index++) {
            assert !priorityQueue.isEmpty();
            nums[youXu++] = priorityQueue.poll();
            priorityQueue.add(nums[index]);
        }
        // 最后再将堆中所有剩余数字按顺序弹出放到对应位置
        while (!priorityQueue.isEmpty()) {
            nums[youXu++] = priorityQueue.poll();
        }
    }

}
