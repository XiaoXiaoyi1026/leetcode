package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

public class NumberOfCompleteBinaryTreeNodesTest extends TestCase {

    private BinaryTree completeBinaryTree;

    public void generateCompleteBinaryTree() {
        completeBinaryTree = new BinaryTree();
        BinaryTree.Node Node1 = new BinaryTree.Node();
        BinaryTree.Node Node2 = new BinaryTree.Node();
        BinaryTree.Node Node3 = new BinaryTree.Node();
        BinaryTree.Node Node4 = new BinaryTree.Node();
        BinaryTree.Node Node5 = new BinaryTree.Node();
        BinaryTree.Node Node6 = new BinaryTree.Node();
        BinaryTree.Node Node7 = new BinaryTree.Node();
        BinaryTree.Node Node8 = new BinaryTree.Node();
        BinaryTree.Node Node9 = new BinaryTree.Node();
        BinaryTree.Node Node10 = new BinaryTree.Node();
        BinaryTree.Node Node11 = new BinaryTree.Node();
        BinaryTree.Node Node12 = new BinaryTree.Node();
        completeBinaryTree.root = Node1;
        Node1.left = Node2;
        Node1.right = Node3;
        Node2.left = Node4;
        Node2.right = Node5;
        Node3.left = Node6;
        Node3.right = Node7;
        Node4.left = Node8;
        Node4.right = Node9;
        Node5.left = Node10;
        Node5.right = Node11;
        Node6.left = Node12;
    }

    public void testNumberOfCompleteBinaryTreeNodes() {
        generateCompleteBinaryTree();
        System.out.println(NumberOfCompleteBinaryTreeNodes
                .numberOfCompleteBinaryTreeNodes(completeBinaryTree));
    }

}
