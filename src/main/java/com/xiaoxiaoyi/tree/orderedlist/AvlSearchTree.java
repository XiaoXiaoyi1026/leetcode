package com.xiaoxiaoyi.tree.orderedlist;

import java.util.Comparator;

/**
 * @author xiaoxiaoyi
 */
public class AvlSearchTree<T> extends RotateSearchTree<T> {

    public AvlSearchTree(Comparator<BinarySearchTreeNode<T>> comparator) {
        super(comparator);
    }

    public AvlSearchTreeNode<T> insert(T element) {
        AvlSearchTreeNode<T> newNode = (AvlSearchTreeNode<T>) super.insert(element);
        // 调整
        rebalance(newNode);
        return newNode;
    }

    @Override
    public AvlSearchTreeNode<T> findNode(T element) {
        return (AvlSearchTreeNode<T>) super.findNode(element);
    }

    @Override
    public AvlSearchTreeNode<T> nodeTransplant(BinarySearchTreeNode<T> nodeToReplace, BinarySearchTreeNode<T> newNode) {
        return (AvlSearchTreeNode<T>) super.nodeTransplant(nodeToReplace, newNode);
    }

    @Override
    public AvlSearchTreeNode<T> getMinimum(BinarySearchTreeNode<T> node) {
        return (AvlSearchTreeNode<T>) super.getMinimum(node);
    }

    @Override
    public AvlSearchTreeNode<T> getMaximum(BinarySearchTreeNode<T> node) {
        return (AvlSearchTreeNode<T>) super.getMaximum(node);
    }

    @Override
    public AvlSearchTreeNode<T> rotateLeft(BinarySearchTreeNode<T> node) {
        return (AvlSearchTreeNode<T>) super.rotateLeft(node);
    }

    @Override
    public AvlSearchTreeNode<T> rotateRight(BinarySearchTreeNode<T> node) {
        return (AvlSearchTreeNode<T>) super.rotateRight(node);
    }

    @Override
    public AvlSearchTreeNode<T> remove(T element) {
        AvlSearchTreeNode<T> reNode = findNode(element);
        return reNode != null ? remove(reNode) : null;
    }

    public AvlSearchTreeNode<T> remove(AvlSearchTreeNode<T> reNode) {
        // 存在时删除, 删除后哪个节点代替了它就返回哪个节点(后继结点)
        AvlSearchTreeNode<T> successorNode = (AvlSearchTreeNode<T>) super.remove(reNode);
        if (successorNode != null) {
            // 获得后继结点的右子树上的最小节点, 如果后继结点没有右子树, 则最小节点就是它自己
            AvlSearchTreeNode<T> minimum = successorNode.getRight() != null ?
                    getMinimum(successorNode.getRight()) : successorNode;
            // 从最小节点开始向上调整所有节点的高度
            recomputeNode(minimum);
            // 调整自己
            rebalance(minimum);
        } else {
            // 如果后继结点为空时, 则从node的父节点开始向上调整
            recomputeNode((AvlSearchTreeNode<T>) reNode.parent);
            // 重新调整父节点
            rebalance((AvlSearchTreeNode<T>) reNode.parent);
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
    private void rebalance(AvlSearchTreeNode<T> node) {
        while (node != null) {
            // afterRotateRoot指向旋转后的根节点, 未旋转之前指向node
            BinarySearchTreeNode<T> parent = node.parent;
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
            node = (AvlSearchTreeNode<T>) parent;
        }
    }

    /**
     * 从node开始向上调整所有节点的高度
     */
    private void recomputeNode(AvlSearchTreeNode<T> node) {
        while (node != null) {
            node.height = getMaxHeight(node) + 1;
            node = (AvlSearchTreeNode<T>) node.parent;
        }
    }

    private void avlRotateLeftAndRight(AvlSearchTreeNode<T> node) {
        // 左旋后右旋
        node.left = avlRotateLeft(node.getLeft());
        avlRotateRight(node);
    }

    private void avlRotateRightAndLeft(AvlSearchTreeNode<T> node) {
        // 右旋后左旋
        node.right = avlRotateRight(node.getRight());
        avlRotateLeft(node);
    }

    private AvlSearchTreeNode<T> avlRotateLeft(AvlSearchTreeNode<T> node) {
        // 左旋
        AvlSearchTreeNode<T> temp = (AvlSearchTreeNode<T>) super.rotateLeft(node);

        // 更新左子树的高度
        updateHeight((AvlSearchTreeNode<T>) temp.left);
        // 更新自己的高度
        updateHeight(temp);
        return temp;
    }

    private AvlSearchTreeNode<T> avlRotateRight(AvlSearchTreeNode<T> node) {
        // 执行右旋操作
        AvlSearchTreeNode<T> temp = (AvlSearchTreeNode<T>) super.rotateRight(node);

        // 更新右子树的高度
        updateHeight((AvlSearchTreeNode<T>) temp.right);
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
    private void updateHeight(AvlSearchTreeNode<T> node) {
        int leftHeight = node.getLeft() == null ? -1 : node.getLeft().height;
        int rightHeight = node.getRight() == null ? -1 : node.getRight().height;
        node.height = Math.max(leftHeight, rightHeight) + 1;
    }

    private int getMaxHeight(AvlSearchTreeNode<T> node) {
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
    public static class AvlSearchTreeNode<T> extends BinarySearchTreeNode<T> {

        public int height;

        public AvlSearchTreeNode(T element) {
            super(element);
            // 初始化节点的高度为0
            height = 0;
        }

        public AvlSearchTreeNode<T> getLeft() {
            return (AvlSearchTreeNode<T>) super.left;
        }

        public AvlSearchTreeNode<T> getRight() {
            return (AvlSearchTreeNode<T>) super.right;
        }
    }
}
