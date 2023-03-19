package com.xiaoxiaoyi.other;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author xiaoxiaoyi
 * 喝咖啡问题
 * 参数1: efficiency, 数组中的每一个数字代表一台咖啡机冲一杯咖啡所需要的时间
 * 参数2: n, 代表有多少人要喝咖啡, 每人只喝1杯, 喝咖啡的时间为0
 * 参数3: washTime, 只有1台洗咖啡杯的机器, 且只能串行洗
 * 参数4: selfCleanTime, 咖啡杯自己挥发也可以变干净的时间
 * 问: n个人开始泡咖啡开始到所有咖啡杯变回干净状态所需的最少时间
 */
public class DrinkCoffee {

    /**
     * 方法1 暴力尝试
     */
    public static int minimumTime1(int[] efficiency, int n, int washTime, int selfCleanTime) {
        return leastWashTime1(getCoffeeTime(efficiency, n), washTime, selfCleanTime);
    }

    /**
     * 面对每个人从什么时刻开始等待洗咖啡杯, 如何选择能够使得洗完的时间最前
     * 每个人有2种选择: 1. 用洗咖啡杯机洗 2. 让其自动挥发
     *
     * @param waitWashTime  每个人开始等待洗杯子的时间
     * @param washTime      洗咖啡杯机的效率
     * @param selfCleanTime 咖啡杯自己挥发变干净需要的时间
     */
    private static int leastWashTime1(int[] waitWashTime, int washTime, int selfCleanTime) {
        return washProcess1(waitWashTime, washTime, selfCleanTime, 0, 0);
    }

    /**
     * @param cur         当前轮到第几个人洗
     * @param canWashTime 洗咖啡杯机的空闲时刻
     * @return 从cur到最后一个人洗完的最早时间
     */
    private static int washProcess1(@NotNull int[] waitWashTime, int washTime, int selfCleanTime, int cur, int canWashTime) {
        if (cur == waitWashTime.length - 1) {
            // 最后1个人洗, 返回选择用机器洗或者挥发
            // Math.max(canWashTime, waitWashTime[cur])表示可以开始洗的时间
            return Math.min(Math.max(canWashTime, waitWashTime[cur]) + washTime
                    , waitWashTime[cur] + selfCleanTime);
        }
        // wash为cur当前的咖啡杯洗完的时间
        int wash = Math.max(canWashTime, waitWashTime[cur]) + washTime;
        // 当前杯子选择用机器洗, 然后去洗后续的杯子, choose1的值代表洗完后续所有咖啡杯的最早时间
        int choose1 = washProcess1(waitWashTime, washTime, selfCleanTime, cur + 1, wash);
        // 完成所有咖啡杯的清洗工作所需的时间, 为洗完当前这个杯子的时间和洗完后续所有杯子时间中的最大值
        int needTime1 = Math.max(wash, choose1);
        // 选择自己挥发
        int selfClean = waitWashTime[cur] + selfCleanTime;
        // 选择2, 当前杯子挥发, 然后去洗后续杯子
        int choose2 = washProcess1(waitWashTime, washTime, selfCleanTime, cur + 1, canWashTime);
        // 完成所有咖啡杯的清洗工作所需的时间, 为洗完当前这个杯子的时间和洗完后续所有杯子时间中的最大值
        int needTime2 = Math.max(selfClean, choose2);
        // 返回2种选择中结束时间早的那个
        return Math.min(needTime1, needTime2);
    }

    /**
     * 方法2 2个可变参数决定的动态规划
     */
    public static int minimumTime2(int[] efficient, int n, int washTime, int selfCleanTime) {
        return dpWashProcess(getCoffeeTime(efficient, n), washTime, selfCleanTime);
    }

    private static int dpWashProcess(@NotNull int[] waitWashTime, int washTime, int selfCleanTime) {
        // 可变参数1: cur 变化范围: 0~waitWashTime.length-1
        // 可变参数2: canWashTime 变化范围0~waitWashTime[waitWashTime.length-1] + waitWashTime.length * washTime
        int n = waitWashTime.length;
        if (washTime >= selfCleanTime) {
            // 如果清洗需要的时间 > 自己挥发的时间, 则直接选择挥发
            return waitWashTime[n - 1] + selfCleanTime;
        }
        int[][] dp = new int[n][waitWashTime[n - 1] + n * washTime];
        // base case
        for (int canWashTime = 0; canWashTime < dp[0].length; canWashTime++) {
            dp[n - 1][canWashTime] = Math.min(
                    Math.max(
                            canWashTime,
                            waitWashTime[n - 1]) + washTime,
                    waitWashTime[n - 1] + selfCleanTime);
        }
        for (int cur = n - 2; cur >= 0; cur--) {
            // 选择用机器洗的话
            // 那么机器的空闲时间最大就会变成开始等待洗杯子的时间 + (包括自己在内)cur+1个杯子的洗涤时间
            int maximumCanWashTime = waitWashTime[cur] + (cur + 1) * washTime;
            // 尝试所有的机器空闲时间
            for (int canWashTime = 0; canWashTime < maximumCanWashTime; canWashTime++) {
                // 选择用机器洗后机器的空闲时间
                int wash = Math.max(canWashTime, waitWashTime[cur]) + washTime;
                dp[cur][canWashTime] = Math.min(
                        // 如果当前用机器洗了, 那么后续的机器空闲时间就是wash
                        Math.max(wash, dp[cur + 1][wash]),
                        // 如果没用机器洗, 那么后续的机器空闲时间还是canWashTime
                        Math.max(
                                waitWashTime[cur] + selfCleanTime,
                                dp[cur + 1][canWashTime]
                        )
                );
            }
        }
        return dp[0][0];
    }

    /**
     * 每个人取得咖啡并一饮而尽, 然后从这个时间开始等待洗咖啡杯
     *
     * @param efficiency 冲咖啡机的效率
     * @param n          要喝咖啡的人数
     */
    @NotNull
    private static int[] getCoffeeTime(@NotNull int[] efficiency, int n) {
        Queue<int[]> smallRootHeap = new PriorityQueue<>(efficiency.length,
                Comparator.comparingInt(o -> (o[0] + o[1]))
        );
        int[] getCoffeeTime = new int[n];
        for (int time : efficiency) {
            // 代表一开始每一台机器都在0时刻有效, 且冲一杯咖啡需要的时间为time
            smallRootHeap.add(new int[]{0, time});
        }
        for (int i = 0; i < n; i++) {
            // 每个人都选择小根堆堆顶的机器来冲咖啡
            int[] currentSelect = smallRootHeap.poll();
            // 当前这个人拿到咖啡的时间
            assert currentSelect != null;
            getCoffeeTime[i] = currentSelect[0] + currentSelect[1];
            // 然后更新咖啡机的有效时间
            currentSelect[0] += currentSelect[1];
            smallRootHeap.add(currentSelect);
        }
        return getCoffeeTime;
    }
}
