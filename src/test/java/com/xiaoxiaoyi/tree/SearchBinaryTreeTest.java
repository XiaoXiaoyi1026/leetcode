package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

import java.util.Comparator;

public class SearchBinaryTreeTest extends TestCase {

    private SearchBinaryTree searchBinaryTree;

    public void createTree(int[] input) {
        searchBinaryTree = new SearchBinaryTree(
                Comparator.comparingInt(o -> (Integer) o.getVal())
        );
        for (int i : input) {
            searchBinaryTree.addNode(new Node(i));
        }
    }

    public void testAddNode() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6};
        createTree(input);
        Morris.morrisInorderTraversal(searchBinaryTree.getRoot());
    }

    public void testRemoveNode() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6};
        createTree(input);
        Node node = new Node(3);
        searchBinaryTree.removeNode(node);
        Morris.morrisInorderTraversal(searchBinaryTree.getRoot());
    }

}
