package com.xiaoxiaoyi.tree;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author xiaoxiaoyi
 * 树形dp, 求树上任意两节点间的最大距离
 */
public class TreeMaxDistance {

    /**
     * 节点
     */
    public static class Node {
        /**
         * 左孩子
         */
        private Node left;
        /**
         * 右孩子
         */
        private Node right;

        /**
         * 初始化
         */
        Node() {
            left = null;
            right = null;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    /**
     * 每颗子树需要返回的信息
     */
    private static class Info {
        /**
         * 这棵树上的任意两节点间的最大距离
         */
        private final int maxDistance;
        /**
         * 这棵树的高度
         */
        private final int height;

        Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }

        public int getMaxDistance() {
            return maxDistance;
        }

        public int getHeight() {
            return height;
        }
    }

    /**
     * @param root 根节点
     * @return 以root为根节点的二叉树上的任意两个节点间的最大距离
     */
    public static int maxDistance(Node root) {
        return process(root).getMaxDistance();
    }

    /**
     * @param root 根节点
     * @return 以root为根的树的信息
     */
    @NotNull
    @Contract("null -> new")
    private static Info process(Node root) {
        if (root == null) {
            // base case
            return new Info(0, 0);
        }
        // 获取左子树和右子树的信息
        Info leftInfo = process(root.getLeft());
        Info rightInfo = process(root.getRight());
        // 获取左右两个子树上的任意两节点间的最大距离
        int leftMaxDistance = leftInfo.getMaxDistance();
        int rightMaxDistance = rightInfo.getMaxDistance();
        // 求出root参与的情况下的任意两节点间的最大距离
        int maxDistance = leftInfo.getHeight() + rightInfo.getHeight() + 1;
        // 求出真正的最大距离
        maxDistance = Math.max(maxDistance, Math.max(leftMaxDistance, rightMaxDistance));
        // 求出自己的高度 = 左子树和右子树的最大高度 + 1
        int height = Math.max(leftInfo.getHeight(), rightInfo.getHeight()) + 1;
        // 返回自己的信息
        return new Info(maxDistance, height);
    }

}
