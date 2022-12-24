package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.Arrays;

public class FindTheKthSmallestNumberIn2SortedArraysTest extends TestCase {

    public int[] generateSortedRandomArray(int length, int max) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = 1 + (int) (Math.random() * max);
        }
        Arrays.sort(arr);
        return arr;
    }

    public void testOuterSort() {
        int[] arr1 = {1, 2, 4, 7};
        int[] arr2 = {3, 5, 6, 8};
        for (int i = 0; i < arr1.length + arr2.length; i++) {
            System.out.println(FindTheKthSmallestNumberIn2SortedArrays
                    .outerSort(arr1, arr2, i + 1));
            System.out.println(FindTheKthSmallestNumberIn2SortedArrays
                    .binarySearchK(arr1, arr2, i + 1));
        }
    }

    public void testGetUpperMedian() {
        int[] arr1 = generateSortedRandomArray(5, 50);
        int[] arr2 = generateSortedRandomArray(5, 60);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(FindTheKthSmallestNumberIn2SortedArrays.getUpperMedian(arr1, arr2));
    }

    public void testGetKth() {
        int[] arr1 = generateSortedRandomArray(10, 50);
        int[] arr2 = generateSortedRandomArray(17, 60);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        // 由于binarySearchK的方法要求无重复值, 所以不参与测试
        System.out.println(FindTheKthSmallestNumberIn2SortedArrays.outerSort(arr1, arr2, 6));
        System.out.println(FindTheKthSmallestNumberIn2SortedArrays.getKth(arr1, arr2, 6));
        System.out.println(FindTheKthSmallestNumberIn2SortedArrays.outerSort(arr1, arr2, 15));
        System.out.println(FindTheKthSmallestNumberIn2SortedArrays.getKth(arr1, arr2, 15));
        System.out.println(FindTheKthSmallestNumberIn2SortedArrays.outerSort(arr1, arr2, 20));
        System.out.println(FindTheKthSmallestNumberIn2SortedArrays.getKth(arr1, arr2, 20));
    }

}
