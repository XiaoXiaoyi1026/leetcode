package com.xiaoxiaoyi.tree;

/**
 * @author xiaoxiaoyi
 * 给你一棵树, 求从根节点到叶节点中的路径中最大的权值和
 */
public class LongestPath {

    public static int maxSum = Integer.MIN_VALUE;

    public static int longestPath(Tree.Node<Integer> root) {
        process(root, 0);
        return maxSum;
    }

    /**
     * @param cur    遍历到的当前节点
     * @param preSum 到达当前节点之前的所有节点权值和
     */
    public static void process(Tree.Node<Integer> cur, int preSum) {
        if (cur.left == null && cur.right == null) {
            // 到达叶节点, 更新最大路径和, 返回
            maxSum = Math.max(maxSum, preSum + cur.element);
            return;
        }
        if (cur.left != null) {
            process(cur.left, preSum + cur.element);
        }
        if (cur.right != null) {
            process(cur.right, preSum + cur.element);
        }
    }

}
