package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 绳子覆盖最多点数问题
 */
public class MostPointsCoveredByRope {

    public static int get(int[] points, int ropeLength) {
        if (points == null || points.length == 0 || ropeLength <= 0) {
            return 0;
        }
        // 首先对points进行排序
        HeapSort.sort(points);
        int ans = 0;
        // 双指针, 窗口[left, right)包左不包右
        int left = 0, right = 1;
        while (right < points.length) {
            while (right < points.length && points[right] <= points[left] + ropeLength) {
                right++;
            }
            ans = Math.max(ans, right - left++);
        }
        return ans;
    }

    public static int get2(int[] points, int ropeLength) {
        if (points == null || points.length == 0 || ropeLength <= 0) {
            return 0;
        }
        // 首先对points进行排序
        HeapSort.sort(points);
        int ans = 0;
        // 绳子右边置于points[right]上
        for (int right = points.length - 1; right >= 0; right--) {
            // 看绳子最左边能到达多少
            int mostLeft = points[right] - ropeLength;
            // 左半部分二分去找大于等于mostLeft的最左位置
            mostLeft = BinarySearch.greatOrEquals(points, 0, right, mostLeft);
            ans = Math.max(ans, right - mostLeft + 1);
        }
        return ans;
    }

}
