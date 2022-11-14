package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

public class MorrisTest extends TestCase {

    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    Node node5 = new Node(5);
    Node node6 = new Node(6);
    Node node7 = new Node(7);

    public void createTree() {
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
    }

    public void testProcess() {
        createTree();
        System.out.println("递归遍历: ");
        Morris.process(node1);
    }

    public void testMorris() {
        createTree();
        System.out.println("Morris遍历: ");
        Morris.morris(node1);
    }

    public void testMorrisPreorderTraversal() {
        createTree();
        System.out.println("Morris先序遍历: ");
        Morris.morrisPreorderTraversal(node1);
    }

    public void testMorrisInorderTraversal() {
        createTree();
        System.out.println("Morris中序遍历: ");
        Morris.morrisInorderTraversal(node1);
    }

    public void testMorrisPostorderTraversal() {
        createTree();
        System.out.println("Morris后序遍历: ");
        Morris.morrisPostorderTraversal(node1);
    }

}
