package com.xiaoxiaoyi.tree.orderedlist;

import com.xiaoxiaoyi.tree.Tree;

import java.util.Comparator;

public class RedBlackTreeTest extends BinarySearchTreeTest {

    private RedBlackTree<Integer> redBlackTree;

    @Override
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
        Tree.printTree(redBlackTree.root);
        System.out.println("===================");
        redBlackTree.insert(7);
        Tree.printTree(redBlackTree.root);
    }

    public void testRemoveNode() {
        int[] input = new int[]{3, 4, 1, 2, 5, 6, 0};
        createTree(input);
        Tree.printTree(redBlackTree.root);
        System.out.println("===================");
        redBlackTree.remove(1);
        redBlackTree.remove(2);
        redBlackTree.remove(3);
        Tree.printTree(redBlackTree.root);
    }



}
