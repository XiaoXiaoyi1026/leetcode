package com.xiaoxiaoyi.tree.orderedlist;

import com.xiaoxiaoyi.tree.BinarySearchTree;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

/**
 * @author xiaoxiaoyi
 * 自平衡二叉树, 可以进行左旋和右旋操作
 */
public class SelfBalancingBinaryTree<T> extends BinarySearchTree<T> {

    public SelfBalancingBinaryTree(Comparator<Node<T>> comparator) {
        super(comparator);
    }

    public Node<T> rotateLeft(@NotNull Node<T> node) {
        if (node.getRight() != null) {
            // temp 指向原来节点的右节点
            Node<T> temp = node.getRight();
            // 右节点的父亲节点变成node的父节点
            temp.setParent(node.getParent());
            // 原节点的右节点变为右节点的左节点
            node.setRight(temp.getLeft());
            // 更新右结点的左节点父节点
            if (node.getRight() != null) {
                node.getRight().setParent(node);
            }
            // 右结点的左子树指向原来的根节点
            temp.setLeft(node);
            // 更新根节点的父亲
            node.setParent(temp);

            if (temp.getParent() != null) {
                if (node.equals(temp.getParent().getLeft())) {
                    // 如果旋转前的节点是父节点的左节点
                    temp.getParent().setLeft(temp);
                } else {
                    temp.getParent().setRight(temp);
                }
            } else {
                // 否则说明是根节点
                setRoot(temp);
            }

            // 返回旋转后的根节点
            return temp;
        }
        return null;
    }

    public Node<T> rotateRight(@NotNull Node<T> node) {
        if (node.getLeft() != null) {
            // nodeRight 指向原来节点的右节点
            Node<T> temp = node.getLeft();
            // 右节点的父亲节点变成node的父节点
            temp.setParent(node.getParent());
            // 原节点的右节点变为右节点的左节点
            node.setLeft(temp.getRight());
            // 更新右结点的左节点父节点
            if (node.getLeft() != null) {
                node.getLeft().setParent(node);
            }
            // 右结点的左子树指向原来的根节点
            temp.setRight(node);
            // 更新根节点的父亲
            node.setParent(temp);

            if (temp.getParent() != null) {
                if (node.equals(temp.getParent().getLeft())) {
                    // 如果旋转前的节点是父节点的左节点
                    temp.getParent().setLeft(temp);
                } else {
                    temp.getParent().setRight(temp);
                }
            } else {
                // 否则说明是根节点
                setRoot(temp);
            }

            // 返回旋转后的根节点
            return temp;
        }
        return null;
    }
}
