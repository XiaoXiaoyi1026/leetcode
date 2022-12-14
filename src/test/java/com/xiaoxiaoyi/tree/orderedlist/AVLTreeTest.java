package com.xiaoxiaoyi.tree.orderedlist;

import com.xiaoxiaoyi.tree.BinarySearchTree;
import junit.framework.TestCase;

import java.util.Comparator;

public class AVLTreeTest extends TestCase {

    private AVLTree<Integer> avlTree;

    private void createTree(int[] input) {
        avlTree = new AVLTree<>(
                Comparator.comparingInt(o -> o.element)
        );
        for (int i : input) {
            avlTree.insert(i);
        }
    }

    public void testAddNode() {
        int[] input = new int[]{3, 4, 1, 2, 5, 6, 0};
        createTree(input);
        BinarySearchTree.print(avlTree.getRoot());
        System.out.println("=====================");
        System.out.println(avlTree.root);
        System.out.println("=====================");
        avlTree.insert(7);
        System.out.println(avlTree.root);
        System.out.println("=====================");
        BinarySearchTree.print(avlTree.getRoot());
    }

    public void testRemoveNode() {
        int[] input = new int[]{3, 4, 1, 2, 5, 6, 0};
        createTree(input);
        System.out.println("=====================");
        BinarySearchTree.print(avlTree.getRoot());
        System.out.println("=====================");
        System.out.println(avlTree.root);
        System.out.println("=====================");
        avlTree.remove(new AVLTree.AvlNode<>(4));
        System.out.println(avlTree.root);
        System.out.println("=====================");
        BinarySearchTree.print(avlTree.getRoot());
    }

}
