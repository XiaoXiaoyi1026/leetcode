package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

import java.util.Comparator;

public class AvlSearchTreeTest extends TestCase {

    private AvlSearchTree avlSearchTree;

    public void createTree(int[] input) {
        avlSearchTree = new AvlSearchTree(
                Comparator.comparingInt(o -> (Integer) o.getVal())
        );
        for (int i : input) {
            avlSearchTree.insert(new AvlSearchTree.AvlTreeNode(i));
        }
    }

    public void testAddNode() {
        int[] input = new int[]{3, 4, 1, 2, 5, 6, 0};
        createTree(input);
        Morris.morrisInorderTraversal(avlSearchTree.getRoot());
        System.out.println("=====================");
        System.out.println(avlSearchTree.getRoot());
        System.out.println("=====================");
        avlSearchTree.insert(new AvlSearchTree.AvlTreeNode(7));
        System.out.println(avlSearchTree.getRoot());
        System.out.println("=====================");
        Morris.morrisInorderTraversal(avlSearchTree.getRoot());
    }

    public void testRemoveNode() {
        int[] input = new int[]{3, 4, 1, 2, 5, 6, 0};
        createTree(input);
        Morris.morrisInorderTraversal(avlSearchTree.getRoot());
        System.out.println("=====================");
        System.out.println(avlSearchTree.getRoot());
        System.out.println("=====================");
        avlSearchTree.removeNode(new AvlSearchTree.AvlTreeNode(4));
        System.out.println(avlSearchTree.getRoot());
        System.out.println("=====================");
        Morris.morrisInorderTraversal(avlSearchTree.getRoot());
    }

}
