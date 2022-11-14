package com.xiaoxiaoyi.tree;

/**
 * @author xiaoxiaoyi
 * 旋转树, 提供树的左旋和右旋操作
 */
public class RotateTree {

    public static void rotateLeft(SearchBinaryTree tree) {
        // root 指向原来的根节点
        Node root = tree.getRoot();
        // rootRight 指向原来根节点的左节点
        Node rootRight = root.getRight();
        // 树的根节点的左孩子变为根节点
        tree.setRoot(rootRight);
        // 左孩子的左孩子变成原来根节点的右孩子
        root.setRight(rootRight.getLeft());
        // 左孩子的左孩子指向原来的根节点
        rootRight.setLeft(root);
    }

    public static void rotateRight(SearchBinaryTree tree) {
        Node root = tree.getRoot();
        Node rootLeft = root.getLeft();
        // 根节点的右孩子变为根节点
        tree.setRoot(rootLeft);
        // 右孩子的右孩子变为原来根节点的左孩子
        root.setLeft(rootLeft.getRight());
        // 右孩子的右孩子指向原来的根节点
        rootLeft.setRight(root);
    }

}
