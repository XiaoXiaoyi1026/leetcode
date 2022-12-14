package com.xiaoxiaoyi.tree;

/**
 * @author xiaoxiaoyi
 * 求完全二叉树的节点个数
 */
public class NumberOfCompleteBinaryTreeNodes extends BinaryTree {

    public static int numberOfCompleteBinaryTreeNodes(BinaryTree completeBinaryTree) {
        if (completeBinaryTree == null) {
            return 0;
        }
        return process(completeBinaryTree.root, height(completeBinaryTree.root));
    }

    /**
     * @param node       子树根节点
     * @param treeHeight 当前子树的高度
     * @return 子树的节点个数
     */
    public static int process(Node node, int treeHeight) {
        if (node == null) {
            // 空树没有节点
            return 0;
        }
        int ans = 0;
        // 求整棵树的右子树的高度(logN)
        int rightHeight = height(node.right);
        if (rightHeight == treeHeight - 1) {
            // 如果右子树高度 = 整棵树的高度 - 1, 说明左子树是满二叉树, 高度 = 整棵树的高度 - 1
            // 左子树节点个数 = 2^(treeHeight - 1) - 1, 加上根节点就是2^(treeHeight - 1)
            ans += Math.pow(2, rightHeight);
            // 还可以知道右子树一定是完全二叉树, 递归求解
            ans += process(node.right, rightHeight);
        } else {
            // 左子树不满, 但完全, 左子树的高度 = 整棵树的高度 - 1
            ans += process(node.left, treeHeight - 1);
            // 右子树一定是满二叉树, 高度为整颗树的高度 - 2
            // 所以右子树的节点个数 + 根节点 = 2^(treeHeight - 2)
            ans += Math.pow(2, treeHeight - 2);
        }
        return ans;
    }

    /**
     * 求以node为头的完全二叉树的深度
     */
    public static int height(Node node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }

}
