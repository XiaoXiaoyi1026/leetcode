package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 无序数组的局部最小值
 * @date 3/20/2023 3:28 PM
 */
public class JuBuZuiXiao {

    public static void main(String[] args) {
        int[] input;
        for (int i = 0; i < 100; i++) {
            input = RandomUtils.array((int) (1 + Math.random() * 20), 50);
            System.out.println(Arrays.toString(input));
            System.out.println(find(input));
        }
    }

    @Contract(pure = true)
    public static int find(@NotNull int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            // 一个数没有比它大的数
            return -1;
        }
        // 首先判断头尾是不是局部最小, 头只需要比后面的小就算
        if (nums[0] < nums[1]) {
            return nums[0];
        }
        // 然后判断为部是不是局部最小, 尾值需要比它前面的小
        int n = nums.length;
        if (nums[n - 1] < nums[n - 2]) {
            return nums[n - 1];
        }
        // 如果都不是, 那么单调性必呈现出 \  ...  / 的趋势, 说明0~n-1范围上必存在局部最小(线改变了方向)
        int left = 0, right = n - 1, mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (nums[mid - 1] < nums[mid]) {
                // 那么在区间0~mid上也存在 \  ...  / 的趋势
                right = mid;
            } else if (nums[mid] < nums[mid + 1]) {
                return nums[mid];
            } else {
                left = mid;
            }
        }
        return nums[left];
    }

}
