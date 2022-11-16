package com.xiaoxiaoyi.tree;

import java.util.Comparator;

/**
 * @author xiaoxiaoyi
 */
public class AvlSearchTree extends RotateSearchTree {

    public static class AvlTreeNode extends SearchBinaryTreeNode {

        private int height;

        AvlTreeNode(Object val) {
            super(val);
            // 初始化节点的高度为1
            height = 1;
        }

        @Override
        public AvlTreeNode getLeft() {
            return (AvlTreeNode) super.getLeft();
        }

        @Override
        public AvlTreeNode getRight() {
            return (AvlTreeNode) super.getRight();
        }

        @Override
        public String toString() {
            return "AvlTreeNode{" +
                    "val=" + super.getVal() +
                    '}';
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    AvlSearchTree(Comparator<Node> comparator) {
        super(comparator);
    }

    @Override
    public Node insert(SearchBinaryTreeNode node) {
        Node newNode = super.insert(node);
        // 调整
        rebalance((AvlTreeNode) newNode);
        return newNode;
    }

    @Override
    public Node removeNode(SearchBinaryTreeNode node) {
        Node reNode = super.findNode(node);
        if (reNode != null) {
            // 存在时删除, 删除后哪个节点代替了它就返回哪个节点
            AvlTreeNode successorNode = (AvlTreeNode) super.removeNode(node);
            if (successorNode != null) {
                // 获得后继结点的右子树上的最小节点, 如果后继结点没有右子树, 则最小节点就是它自己
                AvlTreeNode minimum = successorNode.getRight() != null ?
                        (AvlTreeNode) getMinimum(successorNode.getRight()) : successorNode;
                // 从最小节点开始向上调整所有节点的高度
                recomputeNode(minimum);
                // 调整自己
                rebalance(minimum);
            } else {
                // 如果后继结点为空时, 则从node的父节点开始向上调整
                recomputeNode((AvlTreeNode) node.getParent());
                // 重新调整父节点
                rebalance((AvlTreeNode) node.getParent());
            }
            // 返回后继结点
            return successorNode;
        }
        return null;
    }

    /**
     * Go up from inserted node, and update height and balance information if needed.
     * If some node balance reaches 2 or -2 that means that subtree must be rebalanced.
     * 从插入的节点向上, 如果需要, 更新高度和平衡信息。
     * 如果某个节点的平衡达到2或者-2,, 这意味着子树必须重新平衡。
     *
     * @param node Inserted Node.
     */
    private void rebalance(AvlTreeNode node) {
        while (node != null) {
            // afterRotateRoot指向旋转后的根节点, 未旋转之前指向node
            Node parent = node.getParent();
            // 空节点高度为-1
            int leftHeight = node.getLeft() == null ? -1 : node.getLeft().getHeight();
            int rightHeight = node.getRight() == null ? -1 : node.getRight().getHeight();
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
            node = (AvlTreeNode) parent;
        }
    }

    /**
     * 从node开始向上调整所有节点的高度
     */
    private void recomputeNode(AvlTreeNode node) {
        while (node != null) {
            node.setHeight(getMaxHeight(node) + 1);
            node = (AvlTreeNode) node.getParent();
        }
    }

    private void avlRotateLeftAndRight(AvlTreeNode node) {
        // 左旋后右旋
        node.setLeft(avlRotateLeft(node.getLeft()));
        avlRotateRight(node);
    }

    private void avlRotateRightAndLeft(AvlTreeNode node) {
        // 右旋后左旋
        node.setRight(avlRotateRight(node.getRight()));
        avlRotateLeft(node);
    }

    private Node avlRotateLeft(AvlTreeNode node) {
        // 左旋
        Node temp = super.rotateLeft(node);

        // 更新左子树的高度
        updateHeight((AvlTreeNode) temp.getLeft());
        // 更新自己的高度
        updateHeight((AvlTreeNode) temp);
        return temp;
    }

    private Node avlRotateRight(AvlTreeNode node) {
        // 执行右旋操作
        Node temp = super.rotateRight(node);

        // 更新右子树的高度
        updateHeight((AvlTreeNode) temp.getRight());
        // 更新整棵树的高度
        updateHeight((AvlTreeNode) temp);
        return temp;
    }

    /**
     * Updates height and balance of the node.
     * 更新节点的高度和平衡信息
     *
     * @param node Node for which height and balance must be updated.
     */
    private void updateHeight(AvlTreeNode node) {
        int leftHeight = node.getLeft() == null ? -1 : node.getLeft().getHeight();
        int rightHeight = node.getRight() == null ? -1 : node.getRight().getHeight();
        node.setHeight(Math.max(leftHeight, rightHeight) + 1);
    }

    private int getMaxHeight(AvlTreeNode node) {
        if (node.getLeft() != null && node.getRight() != null) {
            // 左右子树都不为空, 返回高度较高的那个
            return Math.max(node.getLeft().getHeight(), node.getRight().getHeight());
        } else if (node.getLeft() == null) {
            // 左子树为空, 判断右子树为空则返回-1, 否则返回右子树的高度
            return node.getLeft() == null ? -1 : node.getRight().getHeight();
        } else if (node.getRight() == null) {
            // 左子树不为空, 右子树为空, 返回左子树的高度
            return node.getLeft().getHeight();
        }
        return -1;
    }
}
