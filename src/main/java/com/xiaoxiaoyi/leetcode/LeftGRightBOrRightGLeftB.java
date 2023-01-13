package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 给定一个由'G'和'B'组成的字符串
 * 一次操作只能交换相邻2个字符
 * 你可以选择让G在左B在右或者B在左G在右, 返回最少交换多少次
 * 贪心: G之间的相对位置不需要发生变化
 * 快慢指针
 */
public class LeftGRightBOrRightGLeftB {

    public static int minimumNumberOfExchanges(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        // slow记录G将要放置到的位置
        int slow = 0;
        // fast向右遍历寻找G
        int fast = 0;
        int res1 = 0;
        int res2 = 0;
        // 尝试左G右B
        while (fast < str.length()) {
            while (fast < str.length() && str.charAt(fast) == 'B') {
                // 左边如果发现B则跳过
                fast++;
            }
            if (fast < str.length()) {
                // 如果找到了G, 则计算将fast位置的G交换到slow位置需要多少次相邻交换
                res1 += fast - slow;
                // 然后更新下一个G将要放置的位置
                slow++;
                // fast后移一位
                fast++;
            }
        }
        fast--;
        slow = fast;
        // 尝试左B右G
        while (fast >= 0) {
            while (fast >= 0 && str.charAt(fast) == 'B') {
                // 右边如果发现B则跳过
                fast--;
            }
            if (fast >= 0) {
                // 如果找到了B, 则计算将fast位置的B交换到slow位置需要多少次相邻交换
                res2 += slow - fast;
                // 然后更新下一个G将要放置的位置
                slow--;
                // fast后移一位
                fast--;
            }
        }
        return Math.min(res1, res2);
    }

    public static int getMinimum2(String str) {
        if (str == null || str.length() < 2) {
            return 0;
        }
        int ans1 = 0, ans2 = 0;
        int gi = 0, bi = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'G') {
                // 可以选择让G去左边
                ans1 += i - (gi++);
            } else {
                // 也可以选择让B去左边
                ans2 += i - (bi++);
            }
        }
        return Math.min(ans1, ans2);
    }

}
