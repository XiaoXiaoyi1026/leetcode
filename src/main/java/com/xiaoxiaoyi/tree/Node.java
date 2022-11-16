package com.xiaoxiaoyi.tree;

import java.util.Objects;

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

    protected Node getLeft() {
        return left;
    }

    protected Node getRight() {
        return right;
    }

    protected Object getVal() {
        return val;
    }

    protected void setLeft(Node left) {
        this.left = left;
    }

    protected void setRight(Node right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return Objects.equals(val, node.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}
