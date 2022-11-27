package com.xiaoxiaoyi.tree;

import java.util.Objects;

/**
 * @author xiaoxiaoyi
 * 树节点
 */
public class Node<T> {
    public final T element;
    public Node<T> left;
    public Node<T> right;

    public Node(T element) {
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
        Node<?> node = (Node<?>) o;
        return element.equals(node.element);
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
