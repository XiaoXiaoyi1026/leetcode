package com.xiaoxiaoyi.tree;

/**
 * @author xiaoxiaoyi
 * 旋转树, 提供树的左旋和右旋操作
 */
public class RotateTree {

    public static SearchBinaryTree.SearchBinaryTreeNode rotateLeft(SearchBinaryTree.SearchBinaryTreeNode node) {
        // nodeRight 指向原来节点的右节点
        SearchBinaryTree.SearchBinaryTreeNode nodeRight = node.getRight();
        // 右孩子的左孩子变成原来节点的右孩子
        node.setRight(nodeRight.getLeft());
        // 左孩子的左孩子指向原来的根节点
        nodeRight.setLeft(node);
        // 返回旋转后的根节点
        return nodeRight;
    }

    public static SearchBinaryTree.SearchBinaryTreeNode rotateRight(SearchBinaryTree.SearchBinaryTreeNode node) {
        // nodeLeft指向原来节点的左孩子
        SearchBinaryTree.SearchBinaryTreeNode nodeLeft = node.getLeft();
        // 左孩子的右孩子变为原来节点的左孩子
        node.setLeft(nodeLeft.getRight());
        // 左孩子的右孩子指向原来的节点
        nodeLeft.setRight(node);
        // 返回旋转后的根节点
        return nodeLeft;
    }

}
