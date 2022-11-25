package com.xiaoxiaoyi.dynamic;

/**
 * @author xiaoxiaoyi
 * 给定n个二叉树节点, 求一共可能的二叉树结构有几种
 */
public class PossibleBinaryTreeNumber {

    public static int possibleBinaryTreeNumber(int n) {
        if (n < 2) {
            // base case
            return 1;
        }
        int ans = 0;
        // 枚举所有的左子树的节点个数(0~n-1)
        for (int leftNodes = 0; leftNodes < n; leftNodes++) {
            // 右子树的节点个数
            int rightNodes = n - leftNodes - 1;
            // 结果加伤左子树的所有可能 * 右子树的所有可能
            ans += possibleBinaryTreeNumber(leftNodes) * possibleBinaryTreeNumber(rightNodes);
        }
        return ans;
    }

    public static int possibleBinaryTreeNumberDp(int n) {
        if (n < 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        // base case 0个节点只有1种可能
        dp[0] = 1;
        for (int i = 0; i <= n; i++) {
            // 节点总数为i时
            for (int j = 0; j <= i - 1; j++) {
                // 左子树节点个数为j, 右子树节点个数为i - j - 1
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

}
