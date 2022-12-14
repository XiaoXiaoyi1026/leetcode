package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

public class MorrisTest extends TestCase {

    BinaryTree.Node elementNode = new BinaryTree.Node();
    BinaryTree.Node elementNode2 = new BinaryTree.Node();
    BinaryTree.Node elementNode3 = new BinaryTree.Node();
    BinaryTree.Node elementNode4 = new BinaryTree.Node();
    BinaryTree.Node elementNode5 = new BinaryTree.Node();
    BinaryTree.Node elementNode6 = new BinaryTree.Node();
    BinaryTree.Node elementNode7 = new BinaryTree.Node();

    public void createTree() {
        elementNode.left = elementNode2;
        elementNode.right = elementNode3;
        elementNode2.left = elementNode4;
        elementNode2.right = elementNode5;
        elementNode3.left = elementNode6;
        elementNode3.right = elementNode7;
    }

    public void testProcess() {
        createTree();
        System.out.println("递归遍历: ");
        Morris.process(elementNode);
    }

    public void testMorris() {
        createTree();
        System.out.println("Morris遍历: ");
        Morris.morris(elementNode);
    }

    public void testMorrisPreorderTraversal() {
        createTree();
        System.out.println("Morris先序遍历: ");
        Morris.morrisPreorderTraversal(elementNode);
    }

    public void testMorrisInorderTraversal() {
        createTree();
        System.out.println("Morris中序遍历: ");
        Morris.morrisInorderTraversal(elementNode);
    }

    public void testMorrisPostorderTraversal() {
        createTree();
        System.out.println("Morris后序遍历: ");
        Morris.morrisPostorderTraversal(elementNode);
    }

}
