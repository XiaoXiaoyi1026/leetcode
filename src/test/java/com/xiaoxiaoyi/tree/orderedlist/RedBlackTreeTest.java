package com.xiaoxiaoyi.tree.orderedlist;

import com.xiaoxiaoyi.tree.BinarySearchTree;
import com.xiaoxiaoyi.tree.ElementBinaryTree;
import junit.framework.TestCase;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class RedBlackTreeTest extends TestCase {

    private RedBlackTree<Integer> redBlackTree;

    public void createTree(@NotNull int[] input) {
        redBlackTree = new RedBlackTree<>(
                Comparator.comparingInt(ElementBinaryTree.Node::getElement)
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
