package com.xiaoxiaoyi.dynamic;

import org.jetbrains.annotations.NotNull;

/**
 * @author xiaoxiaoyi
 * 给定一个卡牌数组，规定2名玩家只能从数组两端拿牌，每个玩家都很聪明，问谁最后能够获胜
 */
public class BestWayToHoldPoker {

    /**
     * 随机生成卡牌数组
     *
     * @param len 卡牌数组的长度
     * @param max 卡牌的最大分数
     * @return 随机卡牌数组
     */
    @NotNull
    public static int[] getRandomPokers(int len, int max) {
        int[] pokers = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < pokers.length; i++) {
            pokers[i] = (int) (Math.random() * max) + 1;
        }
        return pokers;
    }

    /**
     * 主函数(暴力尝试)
     *
     * @param arr 牌的数组
     * @return 先手或者后手情况下的最大值
     */
    public static int bestWayToHoldPoker1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 返回当前先手或者后手拿的最大分数
        return Math.max(
                f1(arr, 0, arr.length - 1),
                s1(arr, 0, arr.length - 1)
        );
    }

    /**
     * 后手函数
     *
     * @param arr   牌
     * @param left  可选牌左边界
     * @param right 可选牌右边界
     * @return 先手的2种情况中的最小值
     */
    private static int s1(int[] arr, int left, int right) {
        if (left == right) {
            // 如果只有1张牌, 且我是后手, 则拿不到这张牌, 返回0
            return 0;
        }
        // 如果对手选的是left, 那么我先手时就只能在left+1到right上选
        // 如果对手选的是right, 那么我先手时就只能在left到right - 1上选
        // 2种情况中对手一定将最坏的情况留给自己, 因为是后手, 所以一定只能拿到最差的情况
        return Math.min(f1(arr, left + 1, right), f1(arr, left, right - 1));
    }

    /**
     * 先手函数
     *
     * @param arr   牌
     * @param left  可选牌左边界
     * @param right 可选牌右边界
     * @return 后手的2种情况中的最大值
     */
    private static int f1(int[] arr, int left, int right) {
        if (left == right) {
            // 只有1张牌且我是先手, 那我拿这张牌
            return arr[left];
        }
        // 否则尝试拿左边或者拿右边的最大分数
        return Math.max(arr[left] + s1(arr, left + 1, right), arr[right] + s1(arr, left, right - 1));
    }

    /**
     * 主函数(记忆化搜索)
     *
     * @param arr 牌的数组
     * @return 先手或者后手情况下的最大值
     */
    public static int bestWayToHoldPoker2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        // 状态由left和right共同确定, 它俩的范围都是0~n - 1
        // fdp记录先手状态
        int[][] fdp = new int[n][n];
        // sdp记录后手状态
        int[][] sdp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // 只有i <= j时有效
                if (i == j) {
                    // 只有1张牌, 先手能拿, 后手不能拿
                    fdp[i][j] = arr[i];
                    sdp[i][j] = 0;
                } else {
                    // 由于分数0是有效值, 所以初始填充-1
                    fdp[i][j] = sdp[i][j] = -1;
                }
            }
        }
        // 返回当前先手或者后手拿的最大分数
        return Math.max(
                f2(arr, 0, n - 1, fdp, sdp),
                s2(arr, 0, n - 1, sdp, fdp)
        );
    }

    /**
     * 后手函数
     *
     * @param arr   牌
     * @param left  可选牌左边界
     * @param right 可选牌右边界
     * @return 先手的2种情况中的最小值
     */
    private static int s2(int[] arr, int left, int right, @NotNull int[][] sdp, int[][] fdp) {
        if (sdp[left][right] != -1) {
            // 如果状态已经计算过则直接返回
            return sdp[left][right];
        }
        // 如果对手选的是left, 那么我先手时就只能在left+1到right上选
        // 如果对手选的是right, 那么我先手时就只能在left到right - 1上选
        // 2种情况中对手一定将最坏的情况留给自己, 因为是后手, 所以一定只能拿到最差的情况
        sdp[left][right] = Math.min(
                f2(arr, left + 1, right, fdp, sdp),
                f2(arr, left, right - 1, fdp, sdp)
        );
        return sdp[left][right];
    }

    /**
     * 先手函数
     *
     * @param arr   牌
     * @param left  可选牌左边界
     * @param right 可选牌右边界
     * @return 后手的2种情况中的最大值
     */
    private static int f2(int[] arr, int left, int right, @NotNull int[][] fdp, int[][] sdp) {
        if (fdp[left][right] != -1) {
            // 缓存命中直接返回
            return fdp[left][right];
        }
        // 否则尝试拿左边或者拿右边的最大分数
        fdp[left][right] = Math.max(
                arr[left] + s2(arr, left + 1, right, sdp, fdp),
                arr[right] + s2(arr, left, right - 1, sdp, fdp)
        );
        return fdp[left][right];
    }

    /**
     * 动态规划dp
     *
     * @param pokers 牌组
     * @return 最优分数
     */
    public static int bestWayToHoldPoker3(int[] pokers) {
        if (pokers == null || pokers.length == 0) {
            return 0;
        }
        int n = pokers.length;
        int[][] f = new int[n][n];
        int[][] s = new int[n][n];

        // 初始化(base case)
        for (int i = 0; i < n; i++) {
            // 先手必拿, 后手默认为0
            f[i][i] = pokers[i];
        }
        int row = 0;
        // col = row + 1, 不用考虑row == col
        int col = 1;
        // 从对角线位置开始填f和s表row行col列的值
        while (col < n) {
            int i = row;
            int j = col;
            while (i < n && j < n) {
                f[i][j] = Math.max(pokers[i] + s[i + 1][j], pokers[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
                i++;
                j++;
            }
            col++;
        }
        // 返回0~n-1 范围上先手或者后手的最大值
        return Math.max(f[0][n - 1], s[0][n - 1]);
    }
}
