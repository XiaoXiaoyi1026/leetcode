package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

import static com.xiaoxiaoyi.tree.ElementBinaryTree.print;

public class MaximumBinarySearchSubtreeTest extends TestCase {

    public void testMaximumSearchSubtree() {
        ElementBinaryTree<Integer> elementBinaryTree = new ElementBinaryTree<>();
        ElementBinaryTree.Node<Integer> Node1 = new ElementBinaryTree.Node<>(3);
        ElementBinaryTree.Node<Integer> Node2 = new ElementBinaryTree.Node<>(9);
        ElementBinaryTree.Node<Integer> Node3 = new ElementBinaryTree.Node<>(5);
        ElementBinaryTree.Node<Integer> Node4 = new ElementBinaryTree.Node<>(4);
        ElementBinaryTree.Node<Integer> Node5 = new ElementBinaryTree.Node<>(1);
        ElementBinaryTree.Node<Integer> Node6 = new ElementBinaryTree.Node<>(0);
        ElementBinaryTree.Node<Integer> Node7 = new ElementBinaryTree.Node<>(2);
        Node1.left = Node4;
        Node1.right = Node2;
        Node4.right = Node5;
        Node5.left = Node6;
        Node5.right = Node7;
        Node2.right = Node3;
        elementBinaryTree.root = Node1;
        print(elementBinaryTree);
        ElementBinaryTree.Node<Integer> maximumSearchSubtreeHead = MaximumBinarySearchSubtree.maximumSearchSubtreeRoot(elementBinaryTree);
        System.out.println(maximumSearchSubtreeHead);
        print(maximumSearchSubtreeHead);
    }

}
