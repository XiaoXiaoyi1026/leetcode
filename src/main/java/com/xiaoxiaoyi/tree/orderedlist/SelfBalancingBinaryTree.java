package com.xiaoxiaoyi.tree.orderedlist;

import com.xiaoxiaoyi.tree.BinarySearchTree;

import java.util.Comparator;

/**
 * @author xiaoxiaoyi
 * 自平衡二叉树, 可以进行左旋和右旋操作
 */
public class SelfBalancingBinaryTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    public SelfBalancingBinaryTree(Comparator<Node<T>> comparator) {
        super(comparator);
    }

    public Node<T> rotateLeft(Node<T> node) {
        if (node.right != null) {
            // temp 指向原来节点的右节点
            Node<T> temp = node.getRight();
            // 右节点的父亲节点变成node的父节点
            temp.parent = node.parent;
            // 原节点的右节点变为右节点的左节点
            node.right = temp.left;
            // 更新右结点的左节点父节点
            if (node.right != null) {
                node.getRight().parent = node;
            }
            // 右结点的左子树指向原来的根节点
            temp.left = node;
            // 更新根节点的父亲
            node.parent = temp;

            if (temp.parent != null) {
                if (node.equals(temp.parent.left)) {
                    // 如果旋转前的节点是父节点的左节点
                    temp.parent.left = temp;
                } else {
                    temp.parent.right = temp;
                }
            } else {
                // 否则说明是根节点
                root = temp;
            }

            // 返回旋转后的根节点
            return temp;
        }
        return null;
    }

    public Node<T> rotateRight(Node<T> node) {
        if (node.left != null) {
            // nodeRight 指向原来节点的右节点
            Node<T> temp = node.getLeft();
            // 右节点的父亲节点变成node的父节点
            temp.parent = node.parent;
            // 原节点的右节点变为右节点的左节点
            node.left = temp.right;
            // 更新右结点的左节点父节点
            if (node.left != null) {
                node.getLeft().parent = node;
            }
            // 右结点的左子树指向原来的根节点
            temp.right = node;
            // 更新根节点的父亲
            node.parent = temp;

            if (temp.parent != null) {
                if (node.equals(temp.parent.left)) {
                    // 如果旋转前的节点是父节点的左节点
                    temp.parent.left = temp;
                } else {
                    temp.parent.right = temp;
                }
            } else {
                // 否则说明是根节点
                root = temp;
            }

            // 返回旋转后的根节点
            return temp;
        }
        return null;
    }
}
