package com.xiaoxiaoyi.tree;

import org.jetbrains.annotations.NotNull;

/**
 * @author xiaoxiaoyi
 * 给你一棵树, 求从根节点到叶节点中的路径中最大的权值和
 */
public class LongestPath {

    public static int maxSum = Integer.MIN_VALUE;

    public static int longestPath(BinarySearchTree.Node<Integer> root) {
        process(root, 0);
        return maxSum;
    }

    /**
     * @param cur    遍历到的当前节点
     * @param preSum 到达当前节点之前的所有节点权值和
     */
    public static void process(@NotNull BinarySearchTree.Node<Integer> cur, int preSum) {
        if (cur.left == null && cur.right == null) {
            // 到达叶节点, 更新最大路径和, 返回
            maxSum = Math.max(maxSum, preSum + cur.element);
            return;
        }
        if (cur.left != null) {
            process(cur.getLeft(), preSum + cur.element);
        }
        if (cur.right != null) {
            process(cur.getRight(), preSum + cur.element);
        }
    }

    public static int longestPath2(BinarySearchTree.Node<Integer> root) {
        return process2(root);
    }

    /**
     * 套路解, 向左右子树要信息然后汇总返回
     */
    public static int process2(@NotNull BinarySearchTree.Node<Integer> cur) {
        if (cur.left == null && cur.right == null) {
            return cur.element;
        }
        int ans = Integer.MIN_VALUE;
        if (cur.left != null) {
            ans = process2(cur.getLeft());
        }
        if (cur.right != null) {
            ans = Math.max(ans, process2(cur.getRight()));
        }
        return cur.element + ans;
    }

}
