package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomGenerate;
import junit.framework.TestCase;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MaxIncreasingSubarrayCanDeleteContinuousPartTest extends TestCase {

    private int stupid(@NotNull int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                ans = Math.max(ans, maxLen(delete(arr, i, j)));
            }
        }
        return ans;
    }

    /**
     * @param arr  原始数组
     * @param from 删除起始下标(包含)
     * @param to   删除结束下标(不包含)
     * @return arr删除掉[from, to)范围上的数后的数组
     */
    @NotNull
    @Contract(pure = true)
    private int[] delete(@NotNull int[] arr, int from, int to) {
        int[] newArr = new int[arr.length - (to - from)];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i < from || i >= to) {
                newArr[index++] = arr[i];
            }
        }
        return newArr;
    }

    /**
     * 求数组的最长连续严格递增子数组长度
     *
     * @return 最长连续严格递增子数组长度
     */
    @Contract(pure = true)
    private int maxLen(@NotNull int[] arr) {
        int max = 0;
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i] <= arr[i - 1]) {
                len = 0;
            }
            len++;
            max = Math.max(max, len);
        }
        return max;
    }

    public void test() {
        int testTimes = 1000;
        int maxSize = 50;
        int maxValue = 100;
        int[] arr;
        int ans1, ans2;
        System.out.println("测试开始!!!");
        for (int i = 0; i < testTimes; i++) {
            arr = RandomGenerate.set(maxSize, maxValue,  true);
            ans1 = stupid(arr);
            ans2 = MaxIncreasingSubarrayCanDeleteContinuousPart.maxLen(arr);
            if (ans1 != ans2) {
                System.out.println("测试数组为: " + Arrays.toString(arr));
                System.out.println();
                System.out.println("ans1: " + ans1 + " ans2: " + ans2);
                break;
            }
        }
        System.out.println("测试结束!!!");
    }

}
