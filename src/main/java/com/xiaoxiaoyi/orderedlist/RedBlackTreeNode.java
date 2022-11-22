package com.xiaoxiaoyi.orderedlist;

/**
 * 红黑树节点, 继承自旋转搜索树
 * @author 20609
 */
public class RedBlackTreeNode extends BinarySearchTreeNode {

    /**
     * 节点颜色
     */
    public ColorEnum color;

    public RedBlackTreeNode(Object element, ColorEnum color) {
        super(element);
        this.color = color;
    }

    public RedBlackTreeNode getLeft() {
        return (RedBlackTreeNode) super.left;
    }

    public RedBlackTreeNode getRight() {
        return (RedBlackTreeNode) super.right;
    }

    public RedBlackTreeNode getParent() {
        return (RedBlackTreeNode) super.parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "element=" + element +
                "color=" + color +
                '}';
    }
}
