package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

public class LongestPathTest extends TestCase {

    private Tree.Node<Integer> createTree() {
        Tree.Node<Integer> node1 = new Tree.Node<>(2);
        Tree.Node<Integer> node2 = new Tree.Node<>(5);
        Tree.Node<Integer> node3 = new Tree.Node<>(3);
        Tree.Node<Integer> node4 = new Tree.Node<>(1);
        Tree.Node<Integer> node5 = new Tree.Node<>(7);
        Tree.Node<Integer> node6 = new Tree.Node<>(8);
        Tree.Node<Integer> node7 = new Tree.Node<>(10);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        return node1;
    }

    public void testLongestPath() {
        Tree.Node<Integer> root = createTree();
        Tree.printTree(root);
        System.out.println("==================");
        System.out.println(LongestPath.longestPath(root));
        System.out.println(LongestPath.longestPath2(root));
    }

}