package com.xiaoxiaoyi.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @author xiaoxiaoyi
 * 给定一棵树, 现在知道它上面有2个错误节点
 * 如果把这2个错误节点位置交换一下, 那么二叉树将恢复为搜索二叉树
 * 要求找到2个错误节点并返回
 */
public class AdjustBinarySearchTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    private final List<Node<T>> wrongNodes;

    public AdjustBinarySearchTree(Comparator<Node<T>> comparator) {
        super(comparator);
        wrongNodes = new ArrayList<>(2);
    }

    public List<Node<T>> getWrongNodes() {
        findWrongNodes();
        return wrongNodes;
    }

    public void findWrongNodes() {
        findWrongNodes(getRoot());
    }

    /**
     * 找到以node为头结点的二叉树上的错误节点
     */
    public void findWrongNodes(Node<T> node) {
        /*
        思路: 中序遍历所有节点, 如果中间出现降序
        1: 如果是第1次出现降序, 那么前一个结点是错误节点
        2: 如果是第2次出现降序, 那么后一个节点是错误节点
        tips: 如果降序最后只出现了1次, 那么出现降序的前后2个节点都是错误节点
         */
        Stack<Node<T>> help = new Stack<>();
        while (node != null) {
            help.push(node);
            node = node.getLeft();
        }
        // 记录当前节点前一个出栈的节点
        Node<T> preNode = null;
        while (!help.isEmpty()) {
            // middle指向当前出栈的节点
            Node<T> middle = help.pop();
            if (preNode != null) {
                check(preNode, middle);
            }
            node = middle.getRight();
            while (node != null) {
                help.push(node);
                node = node.getLeft();
            }
            preNode = middle;
        }
    }

    protected void check(Node<T> node1,
                         Node<T> node2) {
        if (comparator.compare(node1, node2) > 0) {
            if (wrongNodes.isEmpty()) {
                // 如果是第1次出现降序
                wrongNodes.add(node1);
                wrongNodes.add(node2);
            } else {
                // 如果是第2次出现降序
                wrongNodes.remove(1);
                wrongNodes.add(node2);
            }
        }
    }

    public void exchangeWrongNodes() {
        exchangeNodes(wrongNodes.get(0), wrongNodes.get(1));
    }

    public void exchangeNodes(T element1, T element2) {
        exchangeNodes(findNode(element1), findNode(element2));
    }

    /**
     * 交换树上的两个节点, 潜台词: 中序遍历过程中node1一定先比node2先发现
     */
    protected void exchangeNodes(Node<T> node1, Node<T> node2) {
        // 1. 判断节点是否为头结点
        if (node1 == getRoot()) {
            // 2. 判断节点是否相邻
            if (node1.right == node2) {
                node1.parent = node2;
                node2.parent = null;
                Node<T> tmp = node1.getLeft();
                node1.left = node2.left;
                node2.left = tmp;
                node1.right = node2.right;
                node2.right = node1;
            } else {
                // 3. 判断节点是父节点的左子树还是右子树
                if (node2 == node2.parent.getLeft()) {
                    node2.parent.left = node1;
                } else {
                    node2.parent.right = node1;
                }
                node1.parent = node2.parent;
                node2.parent = null;
                Node<T> tmp = node1.getLeft();
                node1.left = node2.left;
                node2.left = tmp;
                tmp = node1.getRight();
                node1.right = node2.right;
                node2.right = tmp;
            }
            // 交换完成后树的根节点由wrong1变成wrong2
            root = node2;
        } else if (node2 == root) {
            // 2. 判断节点是否相邻
            if (node2.left == node1) {
                node2.parent = node1;
                node1.parent = null;
                Node<T> tmp = node2.getRight();
                node2.right = node1.right;
                node1.right = tmp;
                node2.left = node1.left;
                node1.left = node2;
            } else {
                // 3. 判断节点是父节点的左子树还是右子树
                if (node1 == node1.parent.getLeft()) {
                    node1.parent.left = node2;
                } else {
                    node1.parent.right = node2;
                }
                node2.parent = node1.parent;
                node1.parent = null;
                Node<T> tmp = node2.getLeft();
                node2.left = node1.left;
                node1.left = tmp;
                tmp = node2.getRight();
                node2.right = node1.right;
                node1.right = tmp;
            }
            root = node1;
        } else {
            // 2. 判断节点是否相邻
            if (node1.right == node2) {
                // 3. 判断节点是父节点的左子树还是右子树
                if (node1.parent.left == node1) {
                    node1.parent.left = node2;
                } else {
                    node1.parent.right = node2;
                }
                node2.parent = node1.parent;
                node1.parent = node2;
                Node<T> tmp = node1.getLeft();
                node1.left = node2.left;
                node2.left = tmp;
                node1.right = node2.right;
                node2.right = node1;
            } else if (node2.left == node1) {
                // 3. 判断节点是父节点的左子树还是右子树
                if (node2.parent.left == node2) {
                    node2.parent.left = node1;
                } else {
                    node2.parent.right = node1;
                }
                node1.parent = node2.parent;
                node2.parent = node1;
                Node<T> tmp = node2.getRight();
                node2.right = node1.right;
                node1.right = tmp;
                node2.left = node1.left;
                node1.left = node2;
            } else {
                // 3. 判断节点是父节点的左子树还是右子树
                if (node1.parent.left == node1) {
                    node1.parent.left = node2;
                } else {
                    node1.parent.right = node2;
                }
                if (node2.parent.left == node2) {
                    node2.parent.left = node1;
                } else {
                    node2.parent.right = node1;
                }
                Node<T> tmp = node1.parent;
                node1.parent = node2.parent;
                node2.parent = tmp;
                tmp = node1.getLeft();
                node1.left = node2.left;
                node2.left = tmp;
                tmp = node1.getRight();
                node1.right = node2.right;
                node2.right = tmp;
            }
        }
    }

}
