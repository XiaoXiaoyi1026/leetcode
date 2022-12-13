package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 路灯问题(顶点覆盖), 给定一个字符串, 其中只包含'.'和'X'
 * 其中'.'代表可以安置路灯, 'X'代表无法安置路灯
 * 一个路灯可以照亮它自己所在的位置以及挨着的左右两边
 * 问: 最少安置多少个路灯可以将所有的位置照亮
 */
public class VertexCover {

    public static int streetLight(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int n = chars.length, ans = 0;
        for (int i = 0; i < n; i++) {
            char cur = chars[i];
            if (cur == '.') {
                if (i != n - 1) {
                    if (chars[i + 1] == '.') {
                        // 贪心
                        // 如果当前和下一个位置都是'.', 则在下一个位置上安放路灯, i直接向后跳2个位置
                        i += 2;
                    } else {
                        // 如果下一个位置是X, 则直接跳过下一个位置
                        i++;
                    }
                    // 如果下一个位置不是'.', 则只能将路灯安放在当前位置
                }
                // 如果最后一个位置是'.', 也只能将路灯安放在当前位置
                ans++;
            }
            // 是'X'直接不管
        }
        return ans;
    }
}
