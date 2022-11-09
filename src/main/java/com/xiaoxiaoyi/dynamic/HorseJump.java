package com.xiaoxiaoyi.dynamic;

/**
 * @author xiaoxiaoyi
 * 中国象棋跳马问题
 */
public class HorseJump {

    /**
     * 暴力尝试
     *
     * @param x 距离目标行的行距
     * @param y 距离目标列的列距
     * @param step 步数
     * @return 马从0, 0 起跳, 经过step步, 跳到x, y 的跳法有几种
     */
    public static int jumpWays1(int x, int y, int step) {
        return process(x, y, step);
    }

    /**
     * 暴力递归
     *
     * @param x 横坐标
     * @param y 纵坐标
     * @param step 剩余步数
     * @return 跳法
     */
    private static int process(int x, int y, int step) {
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            // 横坐标范围0~8, 纵坐标范围0~9
            return 0;
        }
        if (step == 0) {
            // 没有步数的时候, 如果x和y都为0, 则说明到达了目标点
            return (x == 0 && y == 0) ? 1 : 0;
        }
        // 没越界, 且还能跳, 周围一圈可以有8种跳法
        return process(x - 1, y + 2, step - 1) +
                process(x - 1, y - 2, step - 1) +
                process(x + 1, y + 2, step - 1) +
                process(x + 1, y - 2, step - 1) +
                process(x - 2, y + 1, step - 1) +
                process(x - 2, y - 1, step - 1) +
                process(x + 2, y + 1, step - 1) +
                process(x + 2, y - 1, step - 1);
    }

    /**
     * dp 动态规划
     */
    public static int jumpWays2(int x, int y, int step) {
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            // 横坐标范围0~8, 纵坐标范围0~9
            return 0;
        }
        int[][][] dp = new int[9][10][step + 1];
        // base case 第 0 层除了 0, 0 位置外都为 0
        dp[0][0][0] = 1;
        for (int h = 1; h <= step ; h++) {
            // 从第1层开始求, 每1层的值都依赖于它下面那一层的值
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 10; j++) {
                    dp[i][j][h] = getValue(dp, i + 1, j + 2, h - 1) +
                            getValue(dp, i + 1, j - 2, h - 1) +
                            getValue(dp, i - 1, j + 2, h - 1) +
                            getValue(dp, i - 1, j - 2, h - 1) +
                            getValue(dp, i + 2, j + 1, h - 1) +
                            getValue(dp, i + 2, j - 1, h - 1) +
                            getValue(dp, i - 2, j + 1, h - 1) +
                            getValue(dp, i - 2, j - 1, h - 1);
                }
            }
        }
        return dp[x][y][step];
    }

    public static int getValue(int[][][] dp, int x, int y, int h) {
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            // 横坐标范围0~8, 纵坐标范围0~9
            return 0;
        }
        return dp[x][y][h];
    }
}
