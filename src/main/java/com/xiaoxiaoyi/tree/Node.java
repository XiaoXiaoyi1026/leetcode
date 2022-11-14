package com.xiaoxiaoyi.tree;

/**
 * @author xiaoxiaoyi
 * 树节点
 */
public class Node {
    private final Object val;
    private Node left;
    private Node right;

    Node(Object val) {
        this.val = val;
        this.left = this.right = null;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Object getVal() {
        return val;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}
