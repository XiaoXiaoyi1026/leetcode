package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

public class MorrisTest extends TestCase {

    Tree.Node<Integer> node1 = new Tree.Node<>(1);
    Tree.Node<Integer> node2 = new Tree.Node<>(1);
    Tree.Node<Integer> node3 = new Tree.Node<>(1);
    Tree.Node<Integer> node4 = new Tree.Node<>(1);
    Tree.Node<Integer> node5 = new Tree.Node<>(1);
    Tree.Node<Integer> node6 = new Tree.Node<>(1);
    Tree.Node<Integer> node7 = new Tree.Node<>(1);

    public void createTree() {
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
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
