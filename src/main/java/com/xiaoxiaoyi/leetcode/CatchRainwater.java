package com.xiaoxiaoyi.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoxiaoyi
 * 接雨水问题, 给你一个数组, 假设根据这个数组画出直方图, 问直方图最多可以接住多少雨水?
 */
public class CatchRainwater {

    public static int catchRainwater1(int[] heights) {
        int n = heights.length;
        Map<Integer, int[]> max = new HashMap<>(n);
        int leftMax = 0, rightMax = 0;
        for (int i = 0; i < n; i++) {
            max.put(i, new int[]{leftMax, rightMax});
            if (heights[i] > leftMax) {
                leftMax = heights[i];
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            max.get(i)[1] = rightMax;
            if (heights[i] > rightMax) {
                rightMax = heights[i];
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int[] maxes = max.get(i);
            ans += Math.max(Math.min(maxes[0], maxes[1]) - heights[i], 0);
        }
        return ans;
    }

    public static int catchRainwater2(int[] heights) {
        int n = heights.length;
        // 双指针优化空间复杂度, 不用考虑首尾
        int left = 1, right = n - 2;
        int leftMax = heights[0], rightMax = heights[n - 1];
        int ans = 0;
        while (left <= right) {
            if (leftMax > rightMax) {
                ans += Math.max(rightMax - heights[right], 0);
                if (heights[right] > rightMax) {
                    rightMax = heights[right];
                }
                right--;
            } else {
                ans += Math.max(leftMax - heights[left],  0);
                if (heights[left] > leftMax) {
                    leftMax = heights[left];
                }
                left++;
            }
        }
        return ans;
    }

}
