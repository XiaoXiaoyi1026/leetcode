package com.xiaoxiaoyi.tree;

import java.util.Objects;

/**
 * @author xiaoxiaoyi
 * 树节点
 */
public class Node {
    protected final Object element;
    protected Node left;
    protected Node right;

    Node(Object element) {
        this.element = element;
        left = right = null;
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
        return Objects.equals(element, node.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element);
    }

    @Override
    public String toString() {
        return "Node{" +
                "element=" + element +
                '}';
    }
}
