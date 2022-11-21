package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

import java.util.Comparator;

public class BinarySearchTreeTest extends TestCase {

    private BinarySearchTree binarySearchTree;

    public void createTree(int[] input) {
        binarySearchTree = new BinarySearchTree(
                Comparator.comparingInt(o -> (Integer) o.element)
        );
        for (int i : input) {
            binarySearchTree.insert(i);
        }
    }

    public void testAddNode() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6};
        createTree(input);
        Morris.morrisInorderTraversal(binarySearchTree.root);
    }

    public void testRemoveNode() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6};
        createTree(input);
        Morris.morrisInorderTraversal(binarySearchTree.root);
        System.out.println("=====================");
        binarySearchTree.remove(3);
        System.out.println(binarySearchTree.root);
        System.out.println("==================");
        Morris.morrisInorderTraversal(binarySearchTree.root);
    }

    public void testGetMaximum() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6};
        createTree(input);
        System.out.println(binarySearchTree.getMaximum(binarySearchTree.root));
    }

}
