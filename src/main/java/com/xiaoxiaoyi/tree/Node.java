package com.xiaoxiaoyi.tree;

/**
 * @author xiaoxiaoyi
 * 树节点
 */
public class Node<T> {
    private final T val;
    private Node<T> left;
    private Node<T> right;

    Node(T val) {
        this.val = val;
        this.left = this.right = null;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}
