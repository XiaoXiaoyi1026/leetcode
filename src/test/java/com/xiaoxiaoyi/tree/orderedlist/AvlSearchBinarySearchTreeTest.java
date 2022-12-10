package com.xiaoxiaoyi.tree.orderedlist;

import com.xiaoxiaoyi.tree.Tree;
import junit.framework.TestCase;

import java.util.Comparator;

public class AvlSearchBinarySearchTreeTest extends TestCase {

    private AvlBinarySearchTree<Integer> avlSearchTree;

    private void createTree(int[] input) {
        avlSearchTree = new AvlBinarySearchTree<>(
                Comparator.comparingInt(o -> o.element)
        );
        for (int i : input) {
            avlSearchTree.insert(i);
        }
    }

    public void testAddNode() {
        int[] input = new int[]{3, 4, 1, 2, 5, 6, 0};
        createTree(input);
        Tree.printTree(avlSearchTree.root);
        System.out.println("=====================");
        System.out.println(avlSearchTree.root);
        System.out.println("=====================");
        avlSearchTree.insert(7);
        System.out.println(avlSearchTree.root);
        System.out.println("=====================");
        Tree.printTree(avlSearchTree.root);
    }

    public void testRemoveNode() {
        int[] input = new int[]{3, 4, 1, 2, 5, 6, 0};
        createTree(input);
        Tree.printTree(avlSearchTree.root);
        System.out.println("=====================");
        System.out.println(avlSearchTree.root);
        System.out.println("=====================");
        avlSearchTree.remove(new AvlBinarySearchTree.AvlSearchTreeNode<>(4));
        System.out.println(avlSearchTree.root);
        System.out.println("=====================");
        Tree.printTree(avlSearchTree.root);
    }

}
