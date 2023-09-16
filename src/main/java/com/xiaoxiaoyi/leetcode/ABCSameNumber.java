package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomGenerate;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author xiaoxiaoyi
 */
public class ABCSameNumber {

    private ABCSameNumber() {
    }

    /**
     * 给定一个由ABC组成的字符串, 要求每次调整可以将一段连续的字符变成A/B/C, 目标是让ABC的词频一样
     *
     * @param stringABC 由ABC组成的字符串
     * @return 调整的最少次数
     */
    public static int lessTimes(String stringABC) {
        if (stringABC == null || stringABC.isEmpty()) {
            return 0;
        }
        if (stringABC.length() % 3 != 0) {
            return -1;
        }
        return lessTimes(stringABC.toCharArray());
    }


    @Contract(pure = true)
    public static int lessTimes(@NotNull char[] charsABC) {
        // 统计每个词的出现次数
        int[] count = new int[3];
        for (char c : charsABC) {
            count[c - 'A']++;
        }
        if (count[0] == count[1] && count[1] == count[2]) {
            // 如果3种字符词频一样, 那么直接返回
            return 0;
        }
        // 每个词的词频都要达到的目标
        int targetTimes = charsABC.length / 3;
        // 统计3种字符中有几种出现次数小于目标
        int lessThanTargetTimes = 0;
        for (int i = 0; i < 3; i++) {
            if (count[i] < targetTimes) {
                lessThanTargetTimes++;
            }
        }
        if (lessThanTargetTimes == 2) {
            // 如果有2种字符的出现次数都小于目标次数, 那么至少需要调整2次
            return 2;
        }
        // 走到这里说明只有1个词频小于目标
        int lessTime = count[0] < targetTimes ? 0 : (count[1] < targetTimes ? 1 : 2);
        if (count[0] > targetTimes && modify(charsABC, count, 0, lessTime)) {
            // 如果'A'的出现次数 是多的那个, 那么尝试将多的'A'变成目标少的字符, 看能不能达成目标
            return 1;
        }
        if (count[1] > targetTimes && modify(charsABC, count, 1, lessTime)) {
            return 1;
        }
        if (count[2] > targetTimes && modify(charsABC, count, 2, lessTime)) {
            return 1;
        }
        return 2;
    }

    /**
     * 模拟调整的过程
     *
     * @param charsABC ABC字符集
     * @param count    词频
     * @param moreTime 多的字符的词频
     * @param lessTime 少的字符的词频
     * @return 只调整一次能否达成完全平衡
     */
    private static boolean modify(@NotNull char[] charsABC, int[] count, int moreTime, int lessTime) {
        // 窗口左右边界[left, right)
        int left = 0;
        int right = 0;
        // 目标词频
        int targetTimes = charsABC.length / 3;
        // 如果窗口右边界不越界, 或者多的那个数的词频不够了, 都需要继续调整窗口边界
        while (right < charsABC.length || count[moreTime] < targetTimes) {
            if (count[moreTime] > targetTimes) {
                // 如果多的词频大于了目标词频, 那么说明窗口小了, 向右扩
                count[charsABC[right++] - 'A']--;
            } else if (left < right && count[moreTime] < targetTimes) {
                // 如果多的词频小于了目标词频, 那么说明窗口大了, 往左缩
                count[charsABC[left++] - 'A']++;
            } else {
                // 在多的那个词频等于目标词频的情况下, 判断少的那个能否变成目标词频
                if (count[lessTime] + (right - left) < targetTimes) {
                    // 如果窗口内的字符都变成少的那个字符还是小于目标词频, 那么说明窗口小了
                    count[charsABC[right++] - 'A']--;
                } else if (count[lessTime] + (right - left) > targetTimes) {
                    // 如果窗口内的字符都变成少的那个字符大于目标词频, 那么说明窗口大了
                    count[charsABC[left++] - 'A']++;
                } else {
                    // 如果可以达成目标, 返回true
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 给定一个由ABC组成的字符串, 要求每次调整可以将一段连续的字符变成A/B/C, 目标是让ABC的词频一样
     *
     * @param stringABC 由ABC组成的字符串
     * @return 调整的最少次数
     */
    public static int lessTimes2(String stringABC) {
        if (stringABC == null || stringABC.isEmpty()) {
            return 0;
        }
        if (stringABC.length() % 3 != 0) {
            return -1;
        }
        return lessTimes2(stringABC.toCharArray());
    }


    @Contract(pure = true)
    public static int lessTimes2(@NotNull char[] charsABC) {
        char[] set = new char[charsABC.length];
        System.arraycopy(charsABC, 0, set, 0, charsABC.length);
        return process1(set, 0, charsABC);
    }

    public static int process1(@NotNull char[] set, int times, char[] origin) {
        int[] count = new int[3];
        for (char c : set) {
            count[c - 'A']++;
        }
        if (count[0] == count[1] && count[1] == count[2]) {
            return times;
        } else {
            if (times == 2) {
                return 3;
            }
            int ans = Integer.MAX_VALUE;
            for (int left = 0; left < set.length; left++) {
                for (int right = left; right < set.length; right++) {
                    set(set, left, right, 'A');
                    ans = Math.min(ans, process1(set, times + 1, origin));
                    set(set, left, right, 'B');
                    ans = Math.min(ans, process1(set, times + 1, origin));
                    set(set, left, right, 'C');
                    ans = Math.min(ans, process1(set, times + 1, origin));
                    rollBack(set, left, right, origin);
                }
            }
            return ans;
        }
    }

    private static void rollBack(char[] set, int left, int right, char[] origin) {
        if (right - left >= 0) System.arraycopy(origin, left, set, left, right - left);
    }

    private static void set(char[] chars, int from, int to, char toChar) {
        for (int i = from; i < to; i++) {
            chars[i] = toChar;
        }
    }

    public static void main(String[] args) {
        int testTimes = 100;
        String stringABC;
        int ans1;
        int ans2;
        System.out.println("测试开始!!!");
        for (int i = 0; i < testTimes; i++) {
            stringABC = RandomGenerate.string(new char[]{'A', 'B', 'C'}, 24);
            ans1 = lessTimes(stringABC);
            ans2 = lessTimes2(stringABC);
            if (ans1 != ans2) {
                System.out.println(stringABC);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束!!!");
    }
}
