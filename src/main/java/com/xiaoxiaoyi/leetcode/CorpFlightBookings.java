package com.xiaoxiaoyi.leetcode;

import org.jetbrains.annotations.NotNull;

/**
 * 航班机票预订数量问题
 * 给定一个非负整数数组 bookings，其中 bookings[i] = [firsti, lasti, seatsi] 表示某个客户在 [firsti, lasti] 编号的航班预订了 seatsi 个座位。
 * 请返回一个 int[]，其中 ans[i] 是第 i 条航班预订的座位数。
 */
public class CorpFlightBookings {

    /**
     * @param bookings 航班预订信息
     * @param n        航班数量
     * @return 航班预订信息
     */
    public int[] corpFlightBookings(@NotNull int[][] bookings, int n) {
        int[] ans = new int[n];
        // 使用差分数组
        int[] diff = new int[n + 1];
        for (int[] booking : bookings) {
            diff[booking[0] - 1] += booking[2];
            diff[booking[1]] -= booking[2];
        }
        for (int i = 1; i < n; i++) {
            diff[i] += diff[i - 1];
        }
        System.arraycopy(diff, 0, ans, 0, n);
        return ans;
    }

    public static void main(String[] args) {
        CorpFlightBookings corpFlightBookings = new CorpFlightBookings();
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int[] ans = corpFlightBookings.corpFlightBookings(bookings, 5);
        for (int a : ans) System.out.println(a);
    } // 10 55 45 25 25
}
