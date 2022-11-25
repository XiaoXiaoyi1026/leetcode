package com.xiaoxiaoyi.optimization.preprocessing;

import java.util.Arrays;

/**
 * @author xiaoxiaoyi
 * 预处理
 * 给你一个由G和R组成的字符串, 要求G的左边没有R存在
 */
public class LeftGreenRightRed {

    public static int minimalStaining(String GreenRedString) {
        if (GreenRedString == null || GreenRedString.length() < 2) {
            return 0;
        }
        char[] chars = GreenRedString.toCharArray();
        int n = chars.length, res = n;
        int[] leftRed = new int[n];
        int[] rightGreen = new int[n];
        // 预处理, 统计每个位置的左边有多少个R(O(n))
        for (int i = 0; i < n - 1; i++) {
            leftRed[i + 1] = leftRed[i];
            if (chars[i] == 'R') {
                leftRed[i + 1] += 1;
            }
        }
        System.out.println(Arrays.toString(leftRed));
        // 预处理, 统计每个位置的右边有多少个G(O(n))
        for (int i = n - 1; i >= 1; i--) {
            rightGreen[i - 1] = rightGreen[i];
            if (chars[i] == 'G') {
                rightGreen[i - 1] += 1;
            }
        }
        System.out.println(Arrays.toString(rightGreen));
        // (O(n))
        for (int i = 0; i < n; i++) {
            // 如果从当前位置开始左边变成G, 右边变成R需要的代价比之前的res小, 则更新res
            res = Math.min(res, leftRed[i] + rightGreen[i]);
        }
        return res;
    }

}
