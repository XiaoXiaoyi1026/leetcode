package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

public class MaximumSearchSubtreeTest extends TestCase {

    public void testMaximumSearchSubtree() {
        Tree<Integer> tree = new Tree<>();
        Tree.Node<Integer> node1 = new Tree.Node<>(3);
        Tree.Node<Integer> node2 = new Tree.Node<>(9);
        Tree.Node<Integer> node3 = new Tree.Node<>(5);
        Tree.Node<Integer> node4 = new Tree.Node<>(4);
        Tree.Node<Integer> node5 = new Tree.Node<>(1);
        Tree.Node<Integer> node6 = new Tree.Node<>(0);
        Tree.Node<Integer> node7 = new Tree.Node<>(2);
        node1.left = node4;
        node1.right = node2;
        node4.right = node5;
        node5.left = node6;
        node5.right = node7;
        node2.right = node3;
        tree.root = node1;
        Tree.printTree(tree);
        Tree.Node<Integer> maximumSearchSubtreeHead = MaximumSearchSubtree.maximumSearchSubtreeRoot(tree);
        System.out.println(maximumSearchSubtreeHead);
        Tree.printTree(maximumSearchSubtreeHead);
    }

}
