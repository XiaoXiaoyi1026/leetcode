package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出
 */
public class AddBinary {

    public static String addBinary(String a, String b) {
        char[] x = a.length() >= b.length() ? a.toCharArray() : b.toCharArray();
        char[] y = a.length() >= b.length() ? b.toCharArray() : a.toCharArray();
        int i = x.length - 1, j = y.length - 1;
        boolean flag = false;
        StringBuilder ans = new StringBuilder();
        while (i >= 0) {
            char digitX = x[i];
            if (j < 0) {
                if (flag) {
                    // 如果要进位
                    if (digitX == '1') {
                        ans.insert(0, "0");
                    } else {
                        ans.insert(0, "1");
                    }
                    flag = digitX == '1';
                } else {
                    ans.insert(0, digitX);
                }
            } else {
                char digitY = y[j];
                if (digitX == digitY) {
                    if (flag) {
                        ans.insert(0, "1");
                    } else {
                        ans.insert(0, "0");
                    }
                    flag = digitX == '1';
                } else {
                    if (flag) {
                        ans.insert(0, "0");
                    } else {
                        ans.insert(0, "1");
                    }
                }
            }
            i--;
            j--;
        }
        if (flag) {
            ans.insert(0, "1");
        }
        return ans.toString();
    }

}
