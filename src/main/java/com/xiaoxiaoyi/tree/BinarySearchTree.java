package com.xiaoxiaoyi.tree;

import java.util.Comparator;

/**
 * @author xiaoxiaoyi
 * 搜索二叉树
 */
public class BinarySearchTree<T> extends ElementBinaryTree<T> {

    public int size;
    public final Comparator<Node<T>> comparator;

    public BinarySearchTree(Comparator<Node<T>> comparator) {
        super();
        root = null;
        this.comparator = comparator;
    }

    public Node<T> getRoot() {
        return (Node<T>) super.getRoot();
    }

    public Node<T> insert(T element) {
        return insert(new Node<>(element));
    }

    public Node<T> insert(Node<T> node) {
        if (root == null) {
            root = node;
        } else {
            Node<T> curNode = getRoot(), parent = null;
            // 判断element不为空是为了红黑树
            while (curNode != null && curNode.element != null) {
                parent = curNode;
                if (comparator.compare(node, curNode) > 0) {
                    // node > curNode, 往右边滑
                    curNode = curNode.getRight();
                } else if (comparator.compare(node, curNode) < 0) {
                    // node < curNode, 往左边滑
                    curNode = curNode.getLeft();
                } else {
                    // 发现相同元素, 直接退出
                    return curNode;
                }
            }
            node.parent = parent;
            if (comparator.compare(parent, node) > 0) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
        size++;
        return node;
    }

    public Node<T> findNode(T element) {
        return findNode(new Node<>(element));
    }

    public Node<T> findNode(Node<T> node) {
        if (root == null) {
            return null;
        } else {
            Node<T> curNode = getRoot();
            while (curNode != null) {
                if (comparator.compare(node, curNode) > 0) {
                    // node > curNode, 往右边滑
                    curNode = curNode.getRight();
                } else if (comparator.compare(node, curNode) < 0) {
                    // node < curNode, 往左边滑
                    curNode = curNode.getLeft();
                } else {
                    // node == curNode, 退出循环
                    break;
                }
            }
            // 要么找到了退出循环返回, 要么返回null
            return curNode;
        }
    }

    public Node<T> remove(T element) {
        return remove(new Node<>(element));
    }

    public Node<T> remove(Node<T> node) {
        node = findNode(node);
        Node<T> nodeToReturn = null;
        if (node != null) {
            // 如果要删除的节点存在
            if (node.left == null) {
                // 如果左子树为空, 则用节点的右子树替代该节点即可
                nodeToReturn = nodeTransplant(node, node.getRight());
            } else if (node.right == null) {
                // 如果右子树为空, 则用节点的左子树替代该节点即可
                nodeToReturn = nodeTransplant(node, node.getLeft());
            } else {
                // 左右子树都不为空, 后继结点为其右子树的最小节点
                Node<T> successorNode = getMinimum(node.getRight());
                if (!successorNode.parent.equals(node)) {
                    // 如果后继结点不是node的直接子节点, 将后继结点替换成其右子节点
                    nodeTransplant(successorNode, successorNode.getRight());
                    // 后继结点接管原节点的右子树
                    successorNode.right = (node.right);
                    successorNode.getRight().parent = successorNode;
                }
                // 后继结点替换掉当前节点
                nodeTransplant(node, successorNode);
                // 后继结点接管原节点的左子树
                successorNode.left = node.left;
                successorNode.getLeft().parent = node.getRight();
            }
        }
        return nodeToReturn;
    }

    /**
     * 替换nodeToReplace为newNode
     */
    public Node<T> nodeTransplant(Node<T> nodeToReplace, Node<T> newNode) {
        if (nodeToReplace.parent == null) {
            // 替换根节点
            root = newNode;
        } else if (nodeToReplace.equals(nodeToReplace.parent.left)) {
            // 如果被替换掉的是父节点的左边节点
            nodeToReplace.parent.left = newNode;
        } else {
            nodeToReplace.parent.right = newNode;
        }
        if (newNode != null) {
            // 如果新节点不是null, 则设置新节点的父节点为原来节点的父节点
            newNode.parent = nodeToReplace.parent;
        }
        // 返回新节点
        return newNode;
    }

    /**
     * 获取以node为根的搜索树上最小值的节点
     */
    public Node<T> getMinimum(Node<T> node) {
        while (node.left != null) {
            node = node.getLeft();
        }
        return node;
    }

    /**
     * 获取以node为根的搜索树上最大值的节点
     */
    public Node<T> getMaximum(Node<T> node) {
        while (node.right != null) {
            node = node.getRight();
        }
        return node;
    }

    public int getSize() {
        return size;
    }

    /**
     * @author 20609
     * 二分搜索树的节点定义
     */
    public static class Node<T> extends ElementBinaryTree.Node<T> {
        public Node<T> parent;

        public Node(T element) {
            super(element);
            parent = null;
        }

        public Node<T> getLeft() {
            return (Node<T>) super.getLeft();
        }

        public Node<T> getRight() {
            return (Node<T>) super.getRight();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }
}