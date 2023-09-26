package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.MathUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * 括号染色方案
 * 给定一个数组brackets, 代表每个位置上的括号状况
 * 比如[0, 1, 0, 0, 2, 0]
 * brackets[i] == 0代表该位置的括号可以是左括号, 也可以是右括号, 且颜色也可以自定义
 * brackets[i] == 1代表这一位置的括号已经是左括号了, 且颜色为1
 * 给定一个整数k, 代表括号的颜色范围从[1, k]
 * 要求括号字符串合法且对应的左括号和右括号颜色必须一样, 返回染色方案数量
 */
public class BracketColoringScheme {

    private BracketColoringScheme() {
    }

    public static int bracketColoringScheme(int[] brackets, int k) {
        if (brackets == null || brackets.length == 0) {
            return 0;
        }
        int n = brackets.length;
        int legalBrackets = process(brackets, 0, 0);
        int coloredBrackets = 0;
        for (int bracket : brackets) {
            if (bracket != 0) {
                // 统计多少个已经有颜色的左括号
                coloredBrackets++;
            }
        }
        // 在括号组合合法且确定的情况下, 一共有n个括号, 但是已经有coloredBrackets个有颜色的左括号, 那么和它们配对的右括号数量和颜色都确定了, 也就是只剩下n - (coloredBrackets * 2)个括号是可以自由染色的, 括号又必须成对, 所以染色的方案一共就有K^(n - (coloredBrackets * 2)) / 2种!
        return (int) ((legalBrackets * MathUtils.pow(k, (n - ((long) coloredBrackets << 1)) >> 1)) % 1000000007);
    }

    /**
     * @param brackets                        括号数组
     * @param curIndex                        当前决定到的下标
     * @param leftMoreThanRightBracketsNumber [0, curIndex)范围内左括号右括号多的数量
     * @return 在[curIndex, n) 范围上去决策, 合法的括号组合数量
     */
    @Contract(pure = true)
    private static int process(@NotNull int[] brackets, int curIndex, int leftMoreThanRightBracketsNumber) {
        if (curIndex == brackets.length) {
            // base case: 决策到了最后, 如果左括号和右括号一样多, 那么是1个合法的组合
            return leftMoreThanRightBracketsNumber == 0 ? 1 : 0;
        }
        if (leftMoreThanRightBracketsNumber < 0) {
            // 如果右括号比左括号还多了, 那么就不合法
            return 0;
        }
        // 当前位置的括号决策为左括号
        int ans = process(brackets, curIndex + 1, leftMoreThanRightBracketsNumber + 1);
        if (brackets[curIndex] == 0) {
            ans += process(brackets, curIndex + 1, leftMoreThanRightBracketsNumber - 1);
        }
        return ans;
    }

    /**
     * 递归转动态规划
     *
     * @param brackets 括号数组
     * @param k        颜色种类
     * @return 所有染色方案
     */
    public static int bracketColoringScheme2(int[] brackets, int k) {
        if (brackets == null || brackets.length == 0) {
            return 0;
        }
        int n = brackets.length;
        int[][] dp = new int[n + 1][n + 1];
        dp[n][0] = 1;
        for (int curIndex = n - 1; curIndex >= 0; curIndex--) {
            for (int leftMoreThanRightBracketsNumber = n; leftMoreThanRightBracketsNumber >= 0; leftMoreThanRightBracketsNumber--) {
                if (leftMoreThanRightBracketsNumber > 0 && brackets[curIndex] == 0) {
                    dp[curIndex][leftMoreThanRightBracketsNumber] += dp[curIndex + 1][leftMoreThanRightBracketsNumber - 1];
                }
                if (leftMoreThanRightBracketsNumber < n) {
                    dp[curIndex][leftMoreThanRightBracketsNumber] += dp[curIndex + 1][leftMoreThanRightBracketsNumber + 1];
                }
            }
        }
        int legalBrackets = dp[0][0];
        int coloredBrackets = 0;
        for (int bracket : brackets) {
            if (bracket != 0) {
                // 统计多少个已经有颜色的左括号
                coloredBrackets++;
            }
        }
        // 在括号组合合法且确定的情况下, 一共有n个括号, 但是已经有coloredBrackets个有颜色的左括号, 那么和它们配对的右括号数量和颜色都确定了, 也就是只剩下n - (coloredBrackets * 2)个括号是可以自由染色的, 括号又必须成对, 所以染色的方案一共就有K^(n - (coloredBrackets * 2)) / 2种!
        return (int) ((legalBrackets * MathUtils.pow(k, (n - ((long) coloredBrackets << 1)) >> 1)) % 1000000007);
    }

    public static void main(String[] args) {
        int[] brackets = {0, 1, 0, 0, 2, 0};
        System.out.println(bracketColoringScheme(brackets, 3));
        System.out.println(bracketColoringScheme2(brackets, 3));
    }

}
