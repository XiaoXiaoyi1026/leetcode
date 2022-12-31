package com.xiaoxiaoyi.leetcode;

import java.util.Arrays;

/**
 * @author xiaoxiaoyi
 * 乘船过河问题, 给定一个正数数组, 代表每个人的体重
 * 每艘船的承重上限为limit, 且一艘船最多坐2个人
 * 求让所有人都过河最少需要多少艘船?
 */
public class CrossTheRiverByBoat {

    public static int needBoats(int[] weights, int limit) {
        // 对重量排序
        Arrays.sort(weights);
        int n = weights.length;
        if (weights[n - 1] > limit) {
            return -1;
        }
        if (weights[0] > limit >> 1) {
            return n;
        }
        if (weights[n - 1] <= limit >> 1) {
            return n >> 1;
        }
        // 二分查找小于等于limit>>1的最右位置
        int left = mostRight(weights, limit >> 1);
        int right = left + 1;
        /*
        case1: 2人1条船, 但是一个weight > limit / 2, 一个 < limit / 2
        case2: 2人1条船, 但是2个weight都 < limit / 2
        case3: 1人1条船, weight > limit / 2
         */
        int case1 = 0, case2 = 0;
        while (left >= 0 && right < n) {
            int sumWeight = weights[left] + weights[right];
            if (sumWeight <= limit) {
                case1++;
                right++;
            } else {
                case2++;
            }
            left--;
        }
        return case1 + ((case2 + left) >> 1) + 1 + (n - right);
    }

    public static int needBoats2(int[] weights, int limit) {
        // 对重量排序
        Arrays.sort(weights);
        int n = weights.length;
        if (weights[n - 1] > limit) {
            return -1;
        }
        if (weights[0] > limit >> 1) {
            return n;
        }
        if (weights[n - 1] <= limit >> 1) {
            return n >> 1;
        }
        int left = 0, right = n - 1;
        int ans = 0;
        while (left < right) {
            int sumWeight = weights[left] + weights[right];
            if (sumWeight <= limit) {
                left++;
            }
            right--;
            ans++;
        }
        if (left == right) {
            ans++;
        }
        return ans;
    }

    private static int mostRight(int[] sorted, int border) {
        int left = 0, right = sorted.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (sorted[mid] > border) {
                right = mid - 1;
            } else {
                ans = mid;
                left = mid + 1;
            }
        }
        return ans;
    }

}
