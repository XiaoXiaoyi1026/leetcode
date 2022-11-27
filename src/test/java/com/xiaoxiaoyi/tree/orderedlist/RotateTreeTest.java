package com.xiaoxiaoyi.tree.orderedlist;

import junit.framework.TestCase;

import java.util.Comparator;

public class RotateTreeTest extends TestCase {

    private RotateSearchTree<Integer> rotateTree;

    private void createTree(int[] input) {
        rotateTree = new RotateSearchTree<>(
                Comparator.comparingInt(o -> o.element)
        );
        for (int i : input) {
            rotateTree.insert(i);
        }
        rotateTree.printTree();
    }

    public void testRotateLeft() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6, 3, 8, 1, 0};
        createTree(input);
        rotateTree.root = (
                rotateTree.rotateLeft(rotateTree.root)
        );
        System.out.println("======================");
        rotateTree.printTree();
        System.out.println("======================");
    }

    public void testRotateRight() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6};
        createTree(input);
        rotateTree.root =
                rotateTree.rotateRight(rotateTree.root);
        System.out.println("======================");
        rotateTree.printTree();
        System.out.println("======================");
    }
}
