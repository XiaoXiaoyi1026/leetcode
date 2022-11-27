package com.xiaoxiaoyi.tree.orderedlist;

import java.util.Comparator;

/**
 * @author xiaoxiaoyi
 * 红黑树
 */
public class RedBlackTree<T> extends RotateSearchTree<T> {

    /**
     * 根节点
     */
    public final RedBlackTreeNode<T> nilNode;

    public RedBlackTree(Comparator<BinarySearchTreeNode<T>> comparator) {
        super(comparator);
        // 保证红黑树的根节点为黑色(零节点, 黑色节点)
        nilNode = new RedBlackTreeNode<>(null, ColorEnum.BLACK);
    }

    public RedBlackTreeNode<T> getRoot() {
        return (RedBlackTreeNode<T>) root;
    }

    /**
     * 插入节点
     *
     * @param element 原始元素
     * @return 新创建的节点
     */
    @Override
    public RedBlackTreeNode<T> insert(T element) {
        RedBlackTreeNode<T> newNode = (RedBlackTreeNode<T>) super.insert(element);
        // 红黑树初始化节点的时候左右和父亲都要指向零节点
        newNode.left = nilNode;
        newNode.right = nilNode;
        root.parent = nilNode;
        // 插入节点后调整
        adjustAfterInsertingNode(newNode);
        return newNode;
    }

    @Override
    public RedBlackTreeNode<T> remove(T element) {
        // 跟踪节点, 要移除的节点
        RedBlackTreeNode<T> replaceNode = null, removeNode = findNode(element);
        if (removeNode != null && !removeNode.equals(nilNode)) {
            // 要删除的节点不为空且节点不为零节点
            RedBlackTreeNode<T> removedOrMovedNode = removeNode;
            ColorEnum removedOrMovedNodeColor = removedOrMovedNode.color;

            if (removeNode.getLeft().equals(nilNode)) {
                replaceNode = removeNode.getRight();
                nodeTransplant(replaceNode, removeNode.getRight());

            } else if (removeNode.getRight().equals(nilNode)) {
                replaceNode = removeNode.getLeft();
                nodeTransplant(replaceNode, removeNode.getLeft());

            } else {
                removedOrMovedNode = getMinimum(removeNode.getRight());
                removedOrMovedNodeColor = (removedOrMovedNode).color;
                replaceNode = removedOrMovedNode.getRight();

                if (removedOrMovedNode.parent.equals(removeNode)) {
                    replaceNode.parent = removedOrMovedNode;

                } else {
                    nodeTransplant(removedOrMovedNode, removedOrMovedNode.getRight());
                    removedOrMovedNode.right = removeNode.getRight();
                    removedOrMovedNode.getRight().parent = removedOrMovedNode;

                }
                nodeTransplant(removeNode, removedOrMovedNode);
                removedOrMovedNode.left = removeNode.getLeft();
                removedOrMovedNode.getLeft().parent = removedOrMovedNode;
                removedOrMovedNode.color = removeNode.color;
            }

            size--;
            if (removedOrMovedNodeColor == ColorEnum.BLACK) {
                adjustAfterDeletingNode(replaceNode);
            }
        }
        return replaceNode;
    }

    @Override
    public RedBlackTreeNode<T> findNode(T element) {
        return (RedBlackTreeNode<T>) super.findNode(element);
    }

    @Override
    public RedBlackTreeNode<T> getMinimum(BinarySearchTreeNode<T> node) {
        RedBlackTreeNode<T> cur = (RedBlackTreeNode<T>) node;
        // 当前节点不为根节点
        while (!nilNode.equals(cur.getLeft())) {
            cur = cur.getLeft();
        }
        return cur;
    }

    @Override
    public RedBlackTreeNode<T> getMaximum(BinarySearchTreeNode<T> node) {
        RedBlackTreeNode<T> cur = (RedBlackTreeNode<T>) node;
        // 当前节点不为根节点
        while (!nilNode.equals(cur.getRight())) {
            cur = cur.getRight();
        }
        return cur;
    }

    @Override
    public RedBlackTreeNode<T> rotateLeft(BinarySearchTreeNode<T> node) {
        RedBlackTreeNode<T> rotateNode = (RedBlackTreeNode<T>) node;
        RedBlackTreeNode<T> temp = rotateNode.getRight();
        // 设置根节点(rotateNode)的右孩子(temp)的parent指向根节点的parent
        temp.parent = rotateNode.getParent();

        // 根节点接管右孩子的左子树
        rotateNode.right = temp.getLeft();
        if (!rotateNode.getRight().equals(nilNode)) {
            // 根节点的右指针的父亲更新为自己
            rotateNode.getRight().parent = rotateNode;
        }

        temp.left = rotateNode;
        rotateNode.parent = temp;

        if (!temp.getParent().equals(nilNode)) {
            if (rotateNode.equals(temp.getParent().getLeft())) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            // 父亲是零节点, 说明是根节点
            root = temp;
        }

        // 返回旋转后的根节点
        return temp;
    }

    @Override
    public RedBlackTreeNode<T> rotateRight(BinarySearchTreeNode<T> node) {
        RedBlackTreeNode<T> rotateNode = (RedBlackTreeNode<T>) node;
        // 记录根节点的左孩子
        RedBlackTreeNode<T> temp = rotateNode.getLeft();
        // 左孩子的父亲更新为根节点(node)的父亲
        temp.parent = rotateNode.getParent();

        // 根节点接管temp的右子树为自己的左子树
        rotateNode.left = temp.getRight();
        if (!rotateNode.getLeft().equals(nilNode)) {
            // 更新接管后的左子树的父亲
            rotateNode.getLeft().parent = rotateNode;
        }

        // 根节点挂到temp的右子树
        temp.right = rotateNode;
        // 更新父节点
        rotateNode.parent = temp;

        if (!temp.getParent().equals(nilNode)) {
            if (node.equals(temp.getParent().getLeft())) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            // 只有root节点的parent为nilNode
            root = temp;
        }

        return temp;
    }

    /**
     * Similar to original transplant() method in BST but uses nilNode instead of null.
     * 类似于BinarySearchTree中的节点移植方法，但使用nilNode而不使用null
     */
    @Override
    public RedBlackTreeNode<T> nodeTransplant(BinarySearchTreeNode<T> replaceNode, BinarySearchTreeNode<T> node) {
        RedBlackTreeNode<T> nodeToReplace = (RedBlackTreeNode<T>) replaceNode, newNode = (RedBlackTreeNode<T>) node;
        if (nodeToReplace.getParent().equals(nilNode)) {
            // 替换的节点是根节点
            root = newNode;
        } else if (nodeToReplace.equals(nodeToReplace.getParent().getLeft())) {
            // 替换的节点是左子树根节点
            nodeToReplace.parent.left = newNode;
        } else {
            // 替换的节点是右子树的根节点
            nodeToReplace.parent.right = newNode;
        }
        // 新节点继承老节点的父亲
        newNode.parent = nodeToReplace.getParent();
        // 返回替换后的节点
        return newNode;
    }

    public void adjustAfterDeletingNode(RedBlackTreeNode<T> node) {
        while (!node.equals(getRoot()) && isBlack(node)) {
            // 当节点不为根节点且颜色为黑时

            if (node.equals(node.getParent().getLeft())) {
                // 如果是左子树, 获取它的兄弟结点(右子树)
                RedBlackTreeNode<T> brother = node.getParent().getRight();
                if (isRed(brother)) {
                    // 兄弟节点是红色, 变为黑色
                    brother.color = ColorEnum.BLACK;
                    // 父亲节点变为红色
                    node.getParent().color = ColorEnum.RED;
                    // 右旋
                    rotateRight(node.getParent());
                    brother = node.getParent().getRight();
                }
                if (isBlack(brother.getLeft())
                        && isBlack(brother.getRight())) {
                    // 当节点的左右孩子都为黑色时
                    brother.color = ColorEnum.RED;
                    node = node.getParent();
                } else if (!brother.equals(nilNode)) {
                    if (isBlack(brother.getRight())) {
                        // 如果不为零节点且右孩子颜色为黑色, 则将左子树颜色变为黑色
                        (brother.getLeft()).color = ColorEnum.BLACK;
                        // 自己变为红色(父节点)
                        brother.color = ColorEnum.RED;
                        // 右旋
                        rotateRight(brother);
                        // 兄弟节点指向右边
                        brother = node.getParent().getRight();
                    }
                    brother.color = node.getParent().color;
                    node.getParent().color = ColorEnum.BLACK;
                    brother.getRight().color = ColorEnum.BLACK;
                    rotateLeft(node.getParent());
                    node = getRoot();
                } else {
                    node.color = ColorEnum.BLACK;
                    node = node.getParent();
                }
            } else {
                RedBlackTreeNode<T> brother = node.getParent().getLeft();
                if (isRed(brother)) {
                    brother.color = ColorEnum.BLACK;
                    node.getParent().color = ColorEnum.RED;
                    rotateRight(node.getParent());
                    brother = node.getParent().getLeft();
                }
                if (isBlack(brother.getLeft())
                        && isBlack(brother.getRight())) {
                    brother.color = ColorEnum.RED;
                    node = node.getParent();
                } else if (!brother.equals(nilNode)) {
                    if (isBlack(brother.getLeft())) {
                        brother.getRight().color = ColorEnum.BLACK;
                        brother.color = ColorEnum.RED;
                        rotateLeft(brother);
                        brother = node.getParent().getLeft();
                    }
                    brother.color = node.getParent().color;
                    node.getParent().color = ColorEnum.BLACK;
                    brother.getLeft().color = ColorEnum.BLACK;
                    rotateRight(node.getParent());
                    node = getRoot();
                } else {
                    node.color = ColorEnum.BLACK;
                    node = node.getParent();
                }
            }
        }
    }

    public boolean isBlack(RedBlackTreeNode<T> node) {
        return node != null && node.color == ColorEnum.BLACK;
    }

    public boolean isRed(RedBlackTreeNode<T> node) {
        return node != null && node.color == ColorEnum.RED;
    }

    /**
     * 插入节点后调整, 可能会产生:
     * 1. root节点变成红色
     * 2. 根节点为红色那么子节点必须为黑色
     */
    public void adjustAfterInsertingNode(RedBlackTreeNode<T> currentNode) {
        // currentNode为红色, 如果父节点也为红色, 那么需要调整, 否则退出
        while (!getRoot().equals(currentNode.getParent()) && currentNode.getParent().color == ColorEnum.RED) {
            RedBlackTreeNode<T> parent = currentNode.getParent();
            RedBlackTreeNode<T> grandParent = parent.getParent();
            if (parent.equals(grandParent.getLeft())) {
                RedBlackTreeNode<T> uncle = grandParent.getRight();
                // 情况1: 叔叔节点(父节点的兄弟节点) 和父节点都是红色
                // 将他们的颜色都变成黑色
                if (uncle.color == ColorEnum.RED) {
                    parent.color = ColorEnum.BLACK;
                    uncle.color = ColorEnum.BLACK;
                    grandParent.color = ColorEnum.RED;
                    // 爷爷节点颜色变成红色了, 在下次循环需要判断它有没有破坏红黑规则
                    currentNode = grandParent;
                }
                // 情况2/3 叔叔为黑色, 然后进行旋转
                else {
                    if (currentNode.equals(parent.getRight())) {
                        // 情况2: 先左旋
                        currentNode = parent;
                        rotateLeft(currentNode);
                    }
                    // 不用parent, 情况3
                    parent.color = ColorEnum.BLACK;
                    grandParent.color = ColorEnum.RED;
                    rotateRight(grandParent);
                }
            } else if (parent.equals(grandParent.getRight())) {
                RedBlackTreeNode<T> uncle = grandParent.getLeft();
                // 情况1: 叔叔和父亲都为红色, 变为黑色
                if (uncle.color == ColorEnum.RED) {
                    parent.color = ColorEnum.BLACK;
                    uncle.color = ColorEnum.BLACK;
                    grandParent.color = ColorEnum.RED;
                    // 爷爷更新了颜色, 所以下一轮循环判断有没有破坏红黑规则
                    currentNode = grandParent;
                }
                // 情况2/3: 叔叔为黑色, 旋转
                else {
                    if (currentNode.equals(parent.getLeft())) {
                        // 情况2: 先右旋
                        currentNode = parent;
                        rotateRight(currentNode);
                    }
                    // 不用父亲, 情况3
                    parent.color = ColorEnum.BLACK;
                    grandParent.color = ColorEnum.RED;
                    rotateLeft(grandParent);
                }
                // 保证根节点为黑色
                getRoot().color = ColorEnum.BLACK;
            }
        }
    }

    /**
     * 节点颜色
     *
     * @author xiaoxiaoyi
     */
    public enum ColorEnum {
        // 红
        RED,
        // 黑
        BLACK
    }

    /**
     * @author xiaoxiaoyi
     * 红黑树节点, 继承自旋转搜索树
     */
    public static class RedBlackTreeNode<T> extends BinarySearchTreeNode<T> {

        /**
         * 节点颜色
         */
        public ColorEnum color;

        public RedBlackTreeNode(T element, ColorEnum color) {
            super(element);
            this.color = color;
        }

        public RedBlackTreeNode<T> getLeft() {
            return (RedBlackTreeNode<T>) super.left;
        }

        public RedBlackTreeNode<T> getRight() {
            return (RedBlackTreeNode<T>) super.right;
        }

        public RedBlackTreeNode<T> getParent() {
            return (RedBlackTreeNode<T>) super.parent;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    "color=" + color +
                    '}';
        }
    }
}
