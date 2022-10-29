package com.xiaoxiaoyi.leetcode;

import java.util.Random;

/**
 * @author xiaoxiaoyi
 * 给定一个由非重叠的轴对齐矩形的数组 rects ，其中 rects[i] = [ai, bi, xi, yi] 表示 (ai, bi) 是第 i 个矩形的左下角点，(xi, yi) 是第 i 个矩形的右上角点。
 * 设计一个算法来随机挑选一个被某一矩形覆盖的整数点。
 * 矩形周长上的点也算做是被矩形覆盖。
 * 所有满足要求的点必须等概率被返回。
 * 在给定的矩形覆盖的空间内的任何整数点都有可能被返回。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/random-point-in-non-overlapping-rectangles">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code497 {

    private static int[][] rects;
    private static int n;
    /**
     * 矩阵面积的前缀和
     */
    private static int[] sum;

    private static final Random RANDOM = new Random();

    /**
     * 初始化
     *
     * @param rects 矩阵信息数组
     */
    public static void init(int[][] rects) {
        Code497.rects = rects;
        n = rects.length;
        sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 计算矩阵面积的前缀和
            sum[i] = sum[i - 1] + (rects[i - 1][2] - rects[i - 1][0] + 1) * (rects[i - 1][3] - rects[i - 1][1] + 1);
        }
    }

    public static int[] pick() {
        int val = RANDOM.nextInt(sum[n]) + 1;
        int l = 0, r = n;
        // 二分查找val在哪个矩阵中
        while (l < r) {
            int mid = (l + r) >> 1;
            if (sum[mid] > val) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        // 记录当前矩阵
        int[] cur = rects[r - 1];
        // 计算坐标值
        int x = RANDOM.nextInt(cur[2] - cur[0] + 1) + cur[0];
        int y = RANDOM.nextInt(cur[3] - cur[1] + 1) + cur[1];
        return new int[]{x, y};
    }
}
