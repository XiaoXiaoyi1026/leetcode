package com.xiaoxiaoyi.orderedlist;

/**
 * @author 20609
 */
public class AvlSearchTreeNode extends BinarySearchTreeNode {

    public int height;

    public AvlSearchTreeNode(Object element) {
        super(element);
        // 初始化节点的高度为0
        height = 0;
    }

    public AvlSearchTreeNode getLeft() {
        return (AvlSearchTreeNode) super.left;
    }

    public AvlSearchTreeNode getRight() {
        return (AvlSearchTreeNode) super.right;
    }
}
