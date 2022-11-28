package com.xiaoxiaoyi.tree;

import java.util.Objects;

/**
 * @author xiaoxiaoyi
 */
public class Tree<T> {

    public Node<T> root = null;

    /**
     * @author xiaoxiaoyi
     * 树节点
     */
    public static class Node<T> {
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


    public static void printTree(Node<?> node) {
        printSubtree(node);
    }

    public static void printSubtree(Node<?> node) {
        if (node.right != null) {
            printTree(node.right, true, "");
        }
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, "");
        }
    }

    public static void printTree(Node<?> node, boolean isRight, String indent) {
        if (node.right != null) {
            printTree(node.right, true, indent + (isRight ? "        " : " |      "));
        }
        System.out.print(indent);
        if (isRight) {
            System.out.print(" /");
        } else {
            System.out.print(" \\");
        }
        System.out.print("----- ");
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, indent + (isRight ? " |      " : "        "));
        }
    }

    public static void printNodeValue(Node<?> node) {
        if (node.element == null) {
            System.out.print("<null>");
        } else {
            System.out.print(node.element);
        }
        System.out.println();
    }
}
