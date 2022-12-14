package com.xiaoxiaoyi.tree;

/**
 * @author xiaoxiaoyi
 */
public class BinaryTree {

    public Node root;

    public static class Node {
        public Node left;
        public Node right;

        public Node() {
            left = right = null;
        }
    }
}
