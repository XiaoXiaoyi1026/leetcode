package com.xiaoxiaoyi.tree.orderedlist;

import com.xiaoxiaoyi.tree.BinarySearchTree;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Objects;

/**
 * @author xiaoxiaoyi
 * 红黑树
 */
public class RedBlackTree<T> extends SelfBalancingBinaryTree<T> {

    /**
     * 根节点
     */
    public final Node<T> nilNode;

    public RedBlackTree(Comparator<BinarySearchTree.Node<T>> comparator) {
        super(comparator);
        // 保证红黑树的根节点为黑色(零节点, 黑色节点)
        nilNode = new Node<>(null, ColorEnum.BLACK);
    }

    @Override
    public Node<T> getRoot() {
        return (Node<T>) super.getRoot();
    }

    @Override
    public Node<T> insert(T element) {
        return insert(new Node<>(element, ColorEnum.RED));
    }

    /**
     * 插入节点
     *
     * @param newNode 新节点
     * @return 新创建的节点
     */
    public Node<T> insert(Node<T> newNode) {
        super.insert(newNode);
        // 红黑树初始化节点的时候左右和父亲都要指向零节点
        newNode.setLeft(nilNode);
        newNode.setRight(nilNode);
        getRoot().setParent(nilNode);
        // 插入节点后调整
        adjustAfterInsertingNode(newNode);
        return newNode;
    }

    @Override
    public Node<T> remove(T element) {
        // 跟踪节点, 要移除的节点
        Node<T> replaceNode = null;
        Node<T> removeNode = findNode(element);
        if (removeNode != null && !removeNode.equals(nilNode)) {
            // 要删除的节点不为空且节点不为零节点
            Node<T> removedOrMovedNode = removeNode;
            ColorEnum removedOrMovedNodeColor = removedOrMovedNode.color;
            if (removeNode.getLeft().equals(nilNode)) {
                replaceNode = removeNode.getRight();
                nodeTransplant(removeNode, removeNode.getRight());
            } else if (removeNode.getRight().equals(nilNode)) {
                replaceNode = removeNode.getLeft();
                nodeTransplant(removeNode, removeNode.getLeft());
            } else {
                removedOrMovedNode = getMinimum(removeNode.getRight());
                removedOrMovedNodeColor = removedOrMovedNode.color;
                replaceNode = removedOrMovedNode.getRight();
                if (removedOrMovedNode.getParent().equals(removeNode)) {
                    replaceNode.setParent(removedOrMovedNode);
                } else {
                    nodeTransplant(removedOrMovedNode, removedOrMovedNode.getRight());
                    removedOrMovedNode.setRight(removeNode.getRight());
                    removedOrMovedNode.getRight().setParent(removedOrMovedNode);

                }
                nodeTransplant(removeNode, removedOrMovedNode);
                removedOrMovedNode.setLeft(removeNode.getLeft());
                removedOrMovedNode.getLeft().setParent(removedOrMovedNode);
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
    public Node<T> findNode(T element) {
        return (Node<T>) super.findNode(element);
    }

    public Node<T> getMinimum(@NotNull Node<T> node) {
        // 当前节点不为根节点
        while (!node.getLeft().equals(nilNode)) {
            node = node.getLeft();
        }
        return node;
    }

    public Node<T> getMaximum(@NotNull Node<T> node) {
        // 当前节点不为根节点
        while (!node.getRight().equals(nilNode)) {
            node = node.getRight();
        }
        return node;
    }

    @Override
    public Node<T> rotateLeft(@NotNull BinarySearchTree.Node<T> node) {
        Node<T> curNode = (Node<T>) node;
        Node<T> temp = curNode.getRight();
        // 设置根节点(rotateNode)的右孩子(temp)的parent指向根节点的parent
        temp.setParent(curNode.getParent());
        // 根节点接管右孩子的左子树
        curNode.setRight(temp.getLeft());
        if (!curNode.getRight().equals(nilNode)) {
            // 根节点的右指针的父亲更新为自己
            curNode.getRight().setParent(curNode);
        }
        temp.setLeft(curNode);
        curNode.setParent(temp);
        if (!temp.getParent().equals(nilNode)) {
            if (curNode.equals(temp.getParent().getLeft())) {
                temp.getParent().setLeft(temp);
            } else {
                temp.getParent().setRight(temp);
            }
        } else {
            // 父亲是零节点, 说明是根节点
            setRoot(temp);
        }
        // 返回旋转后的根节点
        return getRoot();
    }

    @Override
    public Node<T> rotateRight(@NotNull BinarySearchTree.Node<T> node) {
        Node<T> curNode = (Node<T>) node;
        // 记录根节点的左孩子
        Node<T> temp = curNode.getLeft();
        // 左孩子的父亲更新为根节点(node)的父亲
        temp.setParent(curNode.getParent());
        // 根节点接管temp的右子树为自己的左子树
        node.setLeft(temp.getRight());
        if (!curNode.getLeft().equals(nilNode)) {
            // 更新接管后的左子树的父亲
            curNode.getLeft().setParent(node);
        }
        // 根节点挂到temp的右子树
        temp.setRight(curNode);
        // 更新父节点
        curNode.setParent(temp);
        if (!temp.getParent().equals(nilNode)) {
            if (curNode.equals(temp.getParent().getLeft())) {
                temp.getParent().setLeft(temp);
            } else {
                temp.getParent().setRight(temp);
            }
        } else {
            // 只有root节点的parent为nilNode
            setRoot(temp);
        }
        return getRoot();
    }

    /**
     * 类似于BinarySearchTree中的节点移植方法，但使用nilNode而不使用null
     */
    public void nodeTransplant(@NotNull Node<T> replaceNode, Node<T> node) {
        if (replaceNode.getParent().equals(nilNode)) {
            // 替换的节点是根节点
            setRoot(node);
        } else if (replaceNode.equals(replaceNode.getParent().getLeft())) {
            // 替换的节点是左子树根节点
            replaceNode.getParent().setLeft(node);
        } else {
            // 替换的节点是右子树的根节点
            replaceNode.getParent().setRight(node);
        }
        // 新节点继承老节点的父亲
        node.setParent(replaceNode.getParent());
        // 返回替换后的节点
    }

    public void adjustAfterDeletingNode(@NotNull Node<T> node) {
        while (!node.equals(getRoot()) && isBlack(node)) {
            // 当节点不为根节点且颜色为黑时
            if (node.equals(node.getParent().getLeft())) {
                // 如果是左子树, 获取它的兄弟结点(右子树)
                Node<T> brother = node.getParent().getRight();
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
                Node<T> brother = node.getParent().getLeft();
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

    public boolean isBlack(Node<T> node) {
        return node != null && node.color == ColorEnum.BLACK;
    }

    public boolean isRed(Node<T> node) {
        return node != null && node.color == ColorEnum.RED;
    }

    /**
     * 插入节点后调整, 可能会产生:
     * 1. root节点变成红色
     * 2. 根节点为红色那么子节点必须为黑色
     */
    public void adjustAfterInsertingNode(@NotNull Node<T> currentNode) {
        // currentNode为红色, 如果父节点也为红色, 那么需要调整, 否则退出
        while (!getRoot().equals(currentNode.getParent()) && currentNode.getParent().color == ColorEnum.RED) {
            Node<T> parent = currentNode.getParent();
            Node<T> grandParent = parent.getParent();
            if (parent.equals(grandParent.getLeft())) {
                Node<T> uncle = grandParent.getRight();
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
                Node<T> uncle = grandParent.getLeft();
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
    @Getter
    @Setter
    public static class Node<T> extends BinarySearchTree.Node<T> {

        /**
         * 节点颜色
         */
        private ColorEnum color;

        public Node(T element, ColorEnum color) {
            super(element);
            this.color = color;
        }

        @Override
        public Node<T> getLeft() {
            return (Node<T>) super.getLeft();
        }

        @Override
        public Node<T> getRight() {
            return (Node<T>) super.getRight();
        }

        @Override
        public Node<T> getParent() {
            return (Node<T>) super.getParent();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            if (!super.equals(o)) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return color == node.color;
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), color);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + this.getElement() +
                    "color=" + color +
                    '}';
        }
    }
}
