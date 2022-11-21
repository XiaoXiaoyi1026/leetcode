package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

import java.util.Comparator;

public class AvlSearchTreeTest extends TestCase {

    private AvlSearchTree avlSearchTree;

    private void createTree(int[] input) {
        avlSearchTree = new AvlSearchTree(
                Comparator.comparingInt(o -> (Integer) o.element)
        );
        for (int i : input) {
            avlSearchTree.insert(i);
        }
    }

    public void testAddNode() {
        int[] input = new int[]{3, 4, 1, 2, 5, 6, 0};
        createTree(input);
        Morris.morrisInorderTraversal(avlSearchTree.root);
        System.out.println("=====================");
        System.out.println(avlSearchTree.root);
        System.out.println("=====================");
        avlSearchTree.insert(new AvlSearchTree.AvlSearchTreeNode(7));
        System.out.println(avlSearchTree.root);
        System.out.println("=====================");
        Morris.morrisInorderTraversal(avlSearchTree.root);
    }

    public void testRemoveNode() {
        int[] input = new int[]{3, 4, 1, 2, 5, 6, 0};
        createTree(input);
        Morris.morrisInorderTraversal(avlSearchTree.root);
        System.out.println("=====================");
        System.out.println(avlSearchTree.root);
        System.out.println("=====================");
        avlSearchTree.remove(new AvlSearchTree.AvlSearchTreeNode(4));
        System.out.println(avlSearchTree.root);
        System.out.println("=====================");
        Morris.morrisInorderTraversal(avlSearchTree.root);
    }

}
