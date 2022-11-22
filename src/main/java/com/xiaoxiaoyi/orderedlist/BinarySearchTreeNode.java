package com.xiaoxiaoyi.orderedlist;

/**
 * @author 20609
 * 二分搜索树的节点定义
 */
public class BinarySearchTreeNode {
    public BinarySearchTreeNode left;
    public BinarySearchTreeNode right;
    public Object element;
    public BinarySearchTreeNode parent;

    public BinarySearchTreeNode(Object element) {
        this.element = element;
        left = right = parent = null;
    }

    @Override
    public String toString() {
        return "BinarySearchTreeNode{" +
                "element=" + element +
                '}';
    }
}
