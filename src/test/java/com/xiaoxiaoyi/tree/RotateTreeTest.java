package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

import java.util.Comparator;

public class RotateTreeTest extends TestCase {

    private SearchBinaryTree searchBinaryTree;

    public void createTree(int[] input) {
        searchBinaryTree = new SearchBinaryTree(
                Comparator.comparingInt(o -> (Integer) o.getVal())
        );
        for (int i : input) {
            searchBinaryTree.addNode(new Node(i));
        }
    }

    public void testRotateLeft() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6};
        createTree(input);
        RotateTree.rotateLeft(searchBinaryTree);
        System.out.println(searchBinaryTree.getRoot());
    }

    public void testRotateRight() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6};
        createTree(input);
        RotateTree.rotateRight(searchBinaryTree);
        System.out.println(searchBinaryTree.getRoot());
    }
}
