package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

import java.util.Comparator;

public class BinarySearchTreeTest extends TestCase {

    private BinarySearchTree binarySearchTree;

    public void createTree(int[] input) {
        binarySearchTree = new BinarySearchTree(
                Comparator.comparingInt(o -> (Integer) o.getVal())
        );
        for (int i : input) {
            binarySearchTree.insert(new BinarySearchTree.SearchBinaryTreeNode(i));
        }
    }

    public void testAddNode() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6};
        createTree(input);
        Morris.morrisInorderTraversal(binarySearchTree.getRoot());
    }

    public void testRemoveNode() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6};
        createTree(input);
        Morris.morrisInorderTraversal(binarySearchTree.getRoot());
        System.out.println("=====================");
        BinarySearchTree.SearchBinaryTreeNode node = new BinarySearchTree.SearchBinaryTreeNode(3);
        binarySearchTree.removeNode(node);
        System.out.println(binarySearchTree.getRoot());
        System.out.println("==================");
        Morris.morrisInorderTraversal(binarySearchTree.getRoot());
    }

}
