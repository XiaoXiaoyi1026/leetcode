package com.xiaoxiaoyi.leetcode;

import java.util.Arrays;

/**
 * @author xiaoxiaoyi
 */
public class MaxPairNumber {

    public static int maxPairNum1(int[] arr, int k) {
        if (k < 0) {
            return -1;
        }
        return process1(arr, 0, k);
    }

    public static int process1(int[] arr, int index, int k) {
        int ans = 0;
        if (index == arr.length) {
            for (int i = 1; i < arr.length; i += 2) {
                if (arr[i] - arr[i - 1] == k) {
                    ans++;
                }
            }
        } else {
            for (int r = 0; r < arr.length; r++) {
                swap(arr, index, r);
                ans = Math.max(ans, process1(arr, index + 1, k));
                swap(arr, r, index);
            }
        }
        return ans;
    }

    public static void swap(int[] arr, int from, int to) {
        int tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }

    public static int maxPairNum2(int[] arr, int k) {
        if (k < 0 || arr == null || arr.length < 2) {
            return 0;
        }
        Arrays.sort(arr);
        int n = arr.length;
        int left = 0;
        int right = 1;
        int ans = 0;
        boolean[] used = new boolean[n];
        while (right < n && left < n) {
            if (used[left]) {
                left++;
            } else if (left >= right) {
                right++;
            } else {
                int distance = arr[right] - arr[left];
                if (distance == k) {
                    ans++;
                    left++;
                    used[right++] = true;
                } else if (distance < k) {
                    right++;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }

}
