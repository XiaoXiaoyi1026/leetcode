package com.xiaoxiaoyi.tree;

import java.util.Objects;

/**
 * @author xiaoxiaoyi
 * 树(节点含有元素)
 */
public class ElementBinaryTree<T> extends BinaryTree {

    public static class Node<T> extends BinaryTree.Node {
        public final T element;

        public Node(T element) {
            super();
            this.element = element;
        }

        @SuppressWarnings("unchecked")
        public Node<T> getLeft() {
            return (Node<T>) this.left;
        }

        @SuppressWarnings("unchecked")
        public Node<T> getRight() {
            return (Node<T>) this.right;
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

    @SuppressWarnings("unchecked")
    public Node<T> getRoot() {
        return (Node<T>) root;
    }

    public static void print(ElementBinaryTree<?> tree) {
        print(tree.getRoot());
    }

    public static void print(Node<?> node, boolean isRight, String indent) {
        if (node.right != null) {
            print(node.getRight(), true, indent + (isRight ? "        " : " |      "));
        }
        System.out.print(indent);
        if (isRight) {
            System.out.print(" /");
        } else {
            System.out.print(" \\");
        }
        System.out.print("----- ");
        printNodeElement(node);
        if (node.left != null) {
            print(node.getLeft(), false, indent + (isRight ? " |      " : "        "));
        }
    }

    public static void print(Node<?> node) {
        if (node.right != null) {
            print(node.getRight(), true, "");
        }
        printNodeElement(node);
        if (node.left != null) {
            print(node.getLeft(), false, "");
        }
    }

    public static void printNodeElement(Node<?> node) {
        if (node.element == null) {
            System.out.print("<null>");
        } else {
            System.out.print(node.element);
        }
        System.out.println();
    }

}
