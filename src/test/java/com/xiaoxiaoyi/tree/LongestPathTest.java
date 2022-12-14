package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

public class LongestPathTest extends TestCase {

    private BinarySearchTree.Node<Integer> createTree() {
        BinarySearchTree.Node<Integer> Node1 = new BinarySearchTree.Node<>(2);
        BinarySearchTree.Node<Integer> Node2 = new BinarySearchTree.Node<>(5);
        BinarySearchTree.Node<Integer> Node3 = new BinarySearchTree.Node<>(3);
        BinarySearchTree.Node<Integer> Node4 = new BinarySearchTree.Node<>(1);
        BinarySearchTree.Node<Integer> Node5 = new BinarySearchTree.Node<>(7);
        BinarySearchTree.Node<Integer> Node6 = new BinarySearchTree.Node<>(8);
        BinarySearchTree.Node<Integer> Node7 = new BinarySearchTree.Node<>(10);
        Node1.left = Node2;
        Node1.right = Node3;
        Node2.left = Node4;
        Node2.right = Node5;
        Node3.left = Node6;
        Node3.right = Node7;
        return Node1;
    }

    public void testLongestPath() {
        BinarySearchTree.Node<Integer> root = createTree();
        BinarySearchTree.print(root);
        System.out.println("==================");
        System.out.println(LongestPath.longestPath(root));
        System.out.println(LongestPath.longestPath2(root));
    }

}