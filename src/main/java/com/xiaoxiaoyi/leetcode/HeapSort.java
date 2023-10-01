package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author xiaoxiaoyi
 * 额外空间复杂度O(1)的排序算法, 堆排序
 */
public class HeapSort {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int[] arr = RandomUtils.array((int) (1 + Math.random() * 20), 50);
            System.out.println(Arrays.toString(arr));
            sort(arr);
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(i, arr);
//        }

        for (int i = arr.length - 1; i >= 0; i--) {
            heapIfy(arr, arr.length, i);
        }

        int heapSize = arr.length;
        while (heapSize > 0) {
            swap(arr, 0, --heapSize);
            heapIfy(arr, heapSize, 0);
        }
    }

    /**
     * 插入元素时堆从插入位置开始向上调整
     * 将其与父元素的下标((index - 1) >> 1)进行比较
     * 如果当前元素的值大于父元素的值, 则需要进行交换
     *
     * @param index 进堆元素原来所在的下标
     */
    private static void heapInsert(int index, @NotNull int[] arr) {
        while (index > 0 && arr[index] > arr[(index - 1) >> 1]) {
            swap(arr, index, (index - 1) >> 1);
            index = (index - 1) >> 1;
        }
    }

    /**
     * 在堆上由上往下的调整
     *
     * @param heapSize 当前堆的大小, arr的0~heapSize-1范围为堆内
     * @param index    开始调整的下标
     */
    private static void heapIfy(int[] arr, int heapSize, int index) {
        int left = (index << 1) + 1;
        while (left < heapSize) {
            int largest = arr[index] >= arr[left] ? index : left;
            if (((index + 1) << 1) < heapSize) {
                largest = arr[largest] >= arr[(index + 1) << 1] ? largest : (index + 1) << 1;
            }
            if (largest == index) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = (index << 1) + 1;
        }
    }

    /**
     * 交换数组内元素
     */
    private static void swap(@NotNull int[] arr, int from, int to) {
        int tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }

}
