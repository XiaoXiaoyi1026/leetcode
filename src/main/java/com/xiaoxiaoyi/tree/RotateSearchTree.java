package com.xiaoxiaoyi.tree;

import java.util.Comparator;

/**
 * @author xiaoxiaoyi
 * 旋转树, 提供树的左旋和右旋操作
 */
public class RotateSearchTree extends BinarySearchTree {

    RotateSearchTree(Comparator<Node> comparator) {
        super(comparator);
    }

    public SearchBinaryTreeNode rotateLeft(SearchBinaryTreeNode node) {
        if (node.getRight() != null) {
            // nodeRight 指向原来节点的右节点
            SearchBinaryTreeNode nodeRight = node.getRight();
            // 右节点的父亲节点变成node的父节点
            nodeRight.setParent(node.getParent());
            // 原节点的右节点变为右节点的左节点
            node.setRight(nodeRight.getLeft());
            // 更新右结点的左节点父节点
            if (node.getRight() != null) {
                node.getRight().setParent(node);
            }
            // 右结点的左子树指向原来的根节点
            nodeRight.setLeft(node);
            // 更新根节点的父亲
            node.setParent(nodeRight);

            if (nodeRight.getParent() != null) {
                if (node.equals(nodeRight.getParent().getLeft())) {
                    // 如果旋转前的节点是父节点的左节点
                    nodeRight.getParent().setLeft(nodeRight);
                } else {
                    nodeRight.getParent().setRight(nodeRight);
                }
            } else {
                // 否则说明是根节点
                setRoot(nodeRight);
            }

            // 返回旋转后的根节点
            return nodeRight;
        }
        return null;
    }

    public SearchBinaryTreeNode rotateRight(SearchBinaryTreeNode node) {
        if (node.getLeft() != null) {
            // nodeRight 指向原来节点的右节点
            SearchBinaryTreeNode nodeLeft = node.getLeft();
            // 右节点的父亲节点变成node的父节点
            nodeLeft.setParent(node.getParent());
            // 原节点的右节点变为右节点的左节点
            node.setLeft(nodeLeft.getRight());
            // 更新右结点的左节点父节点
            if (node.getLeft() != null) {
                node.getLeft().setParent(node);
            }
            // 右结点的左子树指向原来的根节点
            nodeLeft.setRight(node);
            // 更新根节点的父亲
            node.setParent(nodeLeft);

            if (nodeLeft.getParent() != null) {
                if (node.equals(nodeLeft.getParent().getLeft())) {
                    // 如果旋转前的节点是父节点的左节点
                    nodeLeft.getParent().setLeft(nodeLeft);
                } else {
                    nodeLeft.getParent().setRight(nodeLeft);
                }
            } else {
                // 否则说明是根节点
                setRoot(nodeLeft);
            }

            // 返回旋转后的根节点
            return nodeLeft;
        }
        return null;
    }
}
