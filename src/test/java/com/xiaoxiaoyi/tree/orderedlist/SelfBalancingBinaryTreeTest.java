package com.xiaoxiaoyi.tree.orderedlist;

import junit.framework.TestCase;

import java.util.Comparator;

import static com.xiaoxiaoyi.tree.BinarySearchTree.print;

public class SelfBalancingBinaryTreeTest extends TestCase {

    private SelfBalancingBinaryTree<Integer> rotateTree;

    private void createTree(int[] input) {
        rotateTree = new SelfBalancingBinaryTree<>(
                Comparator.comparingInt(o -> o.element)
        );
        for (int i : input) {
            rotateTree.insert(i);
        }
        print(rotateTree.getRoot());
    }

    public void testRotateLeft() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6, 3, 8, 1, 0};
        createTree(input);
        rotateTree.root = (
                rotateTree.rotateLeft(rotateTree.getRoot())
        );
        System.out.println("======================");
        print(rotateTree.getRoot());
        System.out.println("======================");
    }

    public void testRotateRight() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6};
        createTree(input);
        rotateTree.root =
                rotateTree.rotateRight(rotateTree.getRoot());
        System.out.println("======================");
        print(rotateTree.getRoot());
        System.out.println("======================");
    }
}
