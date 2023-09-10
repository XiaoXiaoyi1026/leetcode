package com.xiaoxiaoyi.tree.orderedlist;

import com.xiaoxiaoyi.tree.BinarySearchTree;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

/**
 * @author xiaoxiaoyi
 * <p>
 * Adelson-Velsky and Landis Tree
 * 自平衡二叉搜索树
 */
public class AVLTree<T extends Comparable<T>> extends SelfBalancingBinaryTree<T> {

    public AVLTree(Comparator<BinarySearchTree.Node<T>> comparator) {
        super(comparator);
    }

    @Override
    public Node<T> getRoot() {
        return (Node<T>) super.getRoot();
    }

    @Override
    public Node<T> insert(T element) {
        Node<T> newNode = insert(new Node<>(element));
        // 调整
        rebalanced(newNode);
        return newNode;
    }

    public Node<T> insert(Node<T> newNode) {
        super.insert(newNode);
        // 调整
        rebalanced(newNode);
        return newNode;
    }

    @Override
    public Node<T> findNode(T element) {
        return (Node<T>) super.findNode(element);
    }

    @Override
    public Node<T> nodeTransplant(@NotNull BinarySearchTree.Node<T> nodeToReplace, BinarySearchTree.Node<T> newNode) {
        return (Node<T>) super.nodeTransplant(nodeToReplace, newNode);
    }

    @Override
    public Node<T> getMinimum(@NotNull BinarySearchTree.Node<T> node) {
        return (Node<T>) super.getMinimum(node);
    }

    @Override
    public Node<T> getMaximum(@NotNull BinarySearchTree.Node<T> node) {
        return (Node<T>) super.getMaximum(node);
    }

    @Override
    public Node<T> rotateLeft(@NotNull BinarySearchTree.Node<T> node) {
        return (Node<T>) super.rotateLeft(node);
    }

    @Override
    public Node<T> rotateRight(@NotNull BinarySearchTree.Node<T> node) {
        return (Node<T>) super.rotateRight(node);
    }

    @Override
    public Node<T> remove(T element) {
        Node<T> reNode = findNode(element);
        return reNode != null ? remove(reNode) : null;
    }

    public Node<T> remove(Node<T> reNode) {
        // 存在时删除, 删除后哪个节点代替了它就返回哪个节点(后继结点)
        Node<T> successorNode = (Node<T>) super.remove(reNode);
        if (successorNode != null) {
            // 获得后继结点的右子树上的最小节点, 如果后继结点没有右子树, 则最小节点就是它自己
            Node<T> minimum = successorNode.getRight() != null ?
                    getMinimum(successorNode.getRight()) : successorNode;
            // 从最小节点开始向上调整所有节点的高度
            recomputeNode(minimum);
            // 调整自己
            rebalanced(minimum);
        } else {
            // 如果后继结点为空时, 则从node的父节点开始向上调整
            recomputeNode(reNode.getParent());
            // 重新调整父节点
            rebalanced(reNode.getParent());
        }
        // 返回后继结点
        return successorNode;
    }

    /**
     * 从插入的节点向上, 如果需要, 更新高度和平衡信息。
     * 如果某个节点的平衡达到2或者-2, 这意味着子树必须重新平衡。
     *
     * @param node Inserted Node.
     */
    private void rebalanced(Node<T> node) {
        int leftHeight;
        int rightHeight;
        int nodeBalance;
        while (node != null) {
            // 空节点高度为-1
            leftHeight = node.getLeft() == null ? -1 : node.getLeft().getHeight();
            rightHeight = node.getRight() == null ? -1 : node.getRight().getHeight();
            // 记录左右子树的高度差
            nodeBalance = leftHeight - rightHeight;
            if (nodeBalance > 1) {
                // 如果左边比右边高2个高度了, 判断是ll还是lr情况
                assert (node.getLeft() != null);
                if (node.getLeft().getLeft() != null) {
                    // 执行平衡右旋(自带更新高度操作)
                    avlRotateRight(node);
                } else {
                    // lr情况, 需要先以l为根左旋1次, 然后以当前节点为根右旋1次
                    avlRotateLeftAndRight(node);
                }
            } else if (nodeBalance < -1) {
                // 如果右边比左边高2个高度了, 判断是rr还是rl情况
                assert (node.getRight() != null);
                if (node.getRight().getRight() != null) {
                    // rr 情况, 以当前节点为根左旋1次
                    avlRotateLeft(node);
                } else {
                    // rl 情况, 先以r为根右旋1次, 然后以当前节点为根左旋1次
                    avlRotateRightAndLeft(node);
                }
            }
            // 更新自己的高度
            updateHeight(node);
            // 继续向上更新
            node = node.getParent();
        }
    }

    /**
     * 从node开始向上调整所有节点的高度
     */
    private void recomputeNode(Node<T> node) {
        while (node != null) {
            node.setHeight(getMaxHeight(node) + 1);
            node = node.getParent();
        }
    }

    private void avlRotateLeftAndRight(@NotNull Node<T> node) {
        // 左旋后右旋
        node.setLeft(avlRotateLeft(node.getLeft()));
        avlRotateRight(node);
    }

    private void avlRotateRightAndLeft(@NotNull Node<T> node) {
        // 右旋后左旋
        node.setRight(avlRotateRight(node.getRight()));
        avlRotateLeft(node);
    }

    @NotNull
    private Node<T> avlRotateLeft(Node<T> node) {
        // 左旋
        Node<T> temp = (Node<T>) super.rotateLeft(node);
        // 更新左子树的高度
        updateHeight(temp.getLeft());
        // 更新自己的高度
        updateHeight(temp);
        return temp;
    }

    @NotNull
    private Node<T> avlRotateRight(Node<T> node) {
        // 执行右旋操作
        Node<T> temp = (Node<T>) super.rotateRight(node);
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
    protected void updateHeight(@NotNull Node<T> node) {
        int leftHeight = node.getLeft() == null ? -1 : node.getLeft().getHeight();
        int rightHeight = node.getRight() == null ? -1 : node.getRight().getHeight();
        node.height = Math.max(leftHeight, rightHeight) + 1;
    }

    protected int getMaxHeight(@NotNull Node<T> node) {
        if (node.getLeft() != null && node.getRight() != null) {
            // 左右子树都不为空, 返回高度较高的那个
            return Math.max(node.getLeft().getHeight(), node.getRight().getHeight());
        } else if (node.getLeft() == null) {
            // 左子树为空, 判断右子树为空则返回-1, 否则返回右子树的高度
            return -1;
        } else {
            // 左子树不为空, 右子树为空, 返回左子树的高度
            return node.getLeft().getHeight();
        }
    }

    /**
     * @author xiaoxiaoyi
     */
    @Getter
    @Setter
    public static class Node<T> extends BinarySearchTree.Node<T> {

        int height;

        public Node(T element) {
            super(element);
            // 初始化节点的高度为0
            height = 0;
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
            Node<?> avlNode = (Node<?>) o;
            return this.getHeight() == avlNode.getHeight();
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + this.getHeight();
            return result;
        }
    }
}
