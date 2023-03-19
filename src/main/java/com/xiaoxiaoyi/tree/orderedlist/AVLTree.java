package com.xiaoxiaoyi.tree.orderedlist;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

/**
 * @author xiaoxiaoyi
 * Adelson-Velsky and Landis Tree
 * 自平衡二叉搜索树
 */
public class AVLTree<T extends Comparable<T>> extends SelfBalancingBinaryTree<T> {

    public AVLTree(Comparator<Node<T>> comparator) {
        super(comparator);
    }

    @Override
    public AvlNode<T> getRoot() {
        return (AvlNode<T>) super.getRoot();
    }

    @Override
    public AvlNode<T> insert(T element) {
        AvlNode<T> newNode = insert(new AvlNode<>(element));
        // 调整
        rebalance(newNode);
        return newNode;
    }

    public AvlNode<T> insert(AvlNode<T> newNode) {
        super.insert(newNode);
        // 调整
        rebalance(newNode);
        return newNode;
    }

    @Override
    public AvlNode<T> findNode(T element) {
        return (AvlNode<T>) super.findNode(element);
    }

    @Override
    public AvlNode<T> nodeTransplant(@NotNull Node<T> nodeToReplace, Node<T> newNode) {
        return (AvlNode<T>) super.nodeTransplant(nodeToReplace, newNode);
    }

    @Override
    public AvlNode<T> getMinimum(@NotNull Node<T> node) {
        return (AvlNode<T>) super.getMinimum(node);
    }

    @Override
    public AvlNode<T> getMaximum(@NotNull Node<T> node) {
        return (AvlNode<T>) super.getMaximum(node);
    }

    @Override
    public AvlNode<T> rotateLeft(@NotNull Node<T> node) {
        return (AvlNode<T>) super.rotateLeft(node);
    }

    @Override
    public AvlNode<T> rotateRight(@NotNull Node<T> node) {
        return (AvlNode<T>) super.rotateRight(node);
    }

    @Override
    public AvlNode<T> remove(T element) {
        AvlNode<T> reNode = findNode(element);
        return reNode != null ? remove(reNode) : null;
    }

    public AvlNode<T> remove(AvlNode<T> reNode) {
        // 存在时删除, 删除后哪个节点代替了它就返回哪个节点(后继结点)
        AvlNode<T> successorNode = (AvlNode<T>) super.remove(reNode);
        if (successorNode != null) {
            // 获得后继结点的右子树上的最小节点, 如果后继结点没有右子树, 则最小节点就是它自己
            AvlNode<T> minimum = successorNode.getRight() != null ?
                    getMinimum(successorNode.getRight()) : successorNode;
            // 从最小节点开始向上调整所有节点的高度
            recomputeNode(minimum);
            // 调整自己
            rebalance(minimum);
        } else {
            // 如果后继结点为空时, 则从node的父节点开始向上调整
            recomputeNode((AvlNode<T>) reNode.parent);
            // 重新调整父节点
            rebalance((AvlNode<T>) reNode.parent);
        }
        // 返回后继结点
        return successorNode;
    }

    /**
     * Go up from inserted node, and update height and balance information if needed.
     * If some node balance reaches 2 or -2 that means that subtree must be rebalanced.
     * 从插入的节点向上, 如果需要, 更新高度和平衡信息。
     * 如果某个节点的平衡达到2或者-2,, 这意味着子树必须重新平衡。
     *
     * @param node Inserted Node.
     */
    private void rebalance(AvlNode<T> node) {
        while (node != null) {
            // afterRotateRoot指向旋转后的根节点, 未旋转之前指向node
            Node<T> parent = node.parent;
            // 空节点高度为-1
            int leftHeight = node.getLeft() == null ? -1 : node.getLeft().height;
            int rightHeight = node.getRight() == null ? -1 : node.getRight().height;
            // 记录左右子树的高度差
            int nodeBalance = leftHeight - rightHeight;
            if (nodeBalance == 2) {
                // 如果左边比右边高2个高度了, 判断是ll还是lr情况
                if (node.getLeft().getLeft() != null) {
                    // 执行平衡左旋(自带更新高度操作)
                    avlRotateLeft(node);
                } else {
                    // lr情况, 需要先以l为根左旋1次, 然后以当前节点为根右旋1次
                    avlRotateLeftAndRight(node);
                }
                break;
            } else if (nodeBalance == -2) {
                // 如果右边比左边高2个高度了, 判断是rr还是rl情况
                if (node.getRight().getRight() != null) {
                    // rr 情况, 以当前节点为根左旋1次
                    avlRotateLeft(node);
                } else {
                    // rl 情况, 先以r为根右旋1次, 然后以当前节点为根左旋1次
                    avlRotateRightAndLeft(node);
                }
                break;
            } else {
                // 更新自己的高度
                updateHeight(node);
            }
            // 继续向上更新
            node = (AvlNode<T>) parent;
        }
    }

    /**
     * 从node开始向上调整所有节点的高度
     */
    private void recomputeNode(AvlNode<T> node) {
        while (node != null) {
            node.height = getMaxHeight(node) + 1;
            node = (AvlNode<T>) node.parent;
        }
    }

    private void avlRotateLeftAndRight(@NotNull AvlNode<T> node) {
        // 左旋后右旋
        node.left = avlRotateLeft(node.getLeft());
        avlRotateRight(node);
    }

    private void avlRotateRightAndLeft(@NotNull AvlNode<T> node) {
        // 右旋后左旋
        node.right = avlRotateRight(node.getRight());
        avlRotateLeft(node);
    }

    @NotNull
    private AvlNode<T> avlRotateLeft(AvlNode<T> node) {
        // 左旋
        AvlNode<T> temp = (AvlNode<T>) super.rotateLeft(node);

        // 更新左子树的高度
        updateHeight(temp.getLeft());
        // 更新自己的高度
        updateHeight(temp);
        return temp;
    }

    @NotNull
    private AvlNode<T> avlRotateRight(AvlNode<T> node) {
        // 执行右旋操作
        AvlNode<T> temp = (AvlNode<T>) super.rotateRight(node);

        // 更新右子树的高度
        updateHeight(temp.getRight());
        // 更新整棵树的高度
        updateHeight(temp);
        return temp;
    }

    /**
     * Updates height and balance of the node.
     * 更新节点的高度和平衡信息
     *
     * @param node Node for which height and balance must be updated.
     */
    protected void updateHeight(@NotNull AvlNode<T> node) {
        int leftHeight = node.getLeft() == null ? -1 : node.getLeft().height;
        int rightHeight = node.getRight() == null ? -1 : node.getRight().height;
        node.height = Math.max(leftHeight, rightHeight) + 1;
    }

    protected int getMaxHeight(@NotNull AvlNode<T> node) {
        if (node.getLeft() != null && node.getRight() != null) {
            // 左右子树都不为空, 返回高度较高的那个
            return Math.max(node.getLeft().height, node.getRight().height);
        } else if (node.getLeft() == null) {
            // 左子树为空, 判断右子树为空则返回-1, 否则返回右子树的高度
            return node.getLeft() == null ? -1 : node.getRight().height;
        } else if (node.getRight() == null) {
            // 左子树不为空, 右子树为空, 返回左子树的高度
            return node.getLeft().height;
        }
        return -1;
    }

    /**
     * @author 20609
     */
    public static class AvlNode<T> extends Node<T> {

        public int height;

        public AvlNode(T element) {
            super(element);
            // 初始化节点的高度为0
            height = 0;
        }

        public AvlNode<T> getLeft() {
            return (AvlNode<T>) super.getLeft();
        }

        public AvlNode<T> getRight() {
            return (AvlNode<T>) super.getRight();
        }
    }
}
