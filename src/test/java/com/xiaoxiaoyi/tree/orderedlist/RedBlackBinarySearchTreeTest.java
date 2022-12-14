package com.xiaoxiaoyi.tree.orderedlist;

import com.xiaoxiaoyi.tree.BinarySearchTree;
import junit.framework.TestCase;

import java.util.Comparator;

public class RedBlackBinarySearchTreeTest extends TestCase {

    private RedBlackTree<Integer> redBlackTree;

    public void createTree(int[] input) {
        redBlackTree = new RedBlackTree<>(
                Comparator.comparingInt(o -> o.element)
        );
        for (int i : input) {
            redBlackTree.insert(i);
        }
    }

    public void testInsertNode() {
        int[] input = new int[]{3, 4, 1, 2, 5, 6, 0};
        createTree(input);
        BinarySearchTree.print(redBlackTree.getRoot());
        System.out.println("===================");
        redBlackTree.insert(7);
        BinarySearchTree.print(redBlackTree.getRoot());
    }

    public void testRemoveNode() {
        int[] input = new int[]{3, 4, 1, 2, 5, 6, 0};
        createTree(input);
        BinarySearchTree.print(redBlackTree.getRoot());
        System.out.println("===================");
        redBlackTree.remove(1);
        redBlackTree.remove(2);
        redBlackTree.remove(3);
        BinarySearchTree.print(redBlackTree.getRoot());
    }



}
