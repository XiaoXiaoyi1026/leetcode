package com.xiaoxiaoyi.mihoyo;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 寻找数字
 * @date 3/18/2023 11:15 AM
 */
public class FindNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = scanner.nextInt();
        }
        int start = 1;
        int tmp;
        while (true) {
            tmp = nums[start];
            if (tmp == start) {
                start++;
                continue;
            }
            if (nums[tmp] != tmp) {
                swap(nums, start, tmp);
                start = tmp;
            } else {
                break;
            }
        }
        System.out.println(tmp);
    }

    public static void swap(@NotNull int[] arr, int source, int target) {
        int tmp = arr[source];
        arr[source] = arr[target];
        arr[target] = tmp;
    }

}
