package com.xiaoxiaoyi.tree.orderedlist;

import com.xiaoxiaoyi.tree.BinarySearchTree;
import com.xiaoxiaoyi.tree.ElementBinaryTree;
import junit.framework.TestCase;

import java.util.Comparator;

public class BinarySearchTreeTest extends TestCase {

    protected BinarySearchTree<Integer> binarySearchTree;

    public void createTree(int[] input) {
        binarySearchTree = new BinarySearchTree<>(
                Comparator.comparingInt(ElementBinaryTree.Node::getElement)
        );
        for (int i : input) {
            binarySearchTree.insert(i);
        }
    }

    public void testAddNode() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6};
        createTree(input);
        BinarySearchTree.print(binarySearchTree.getRoot());
    }

    public void testRemoveNode() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6};
        createTree(input);
        BinarySearchTree.print(binarySearchTree.getRoot());
        System.out.println("=====================");
        binarySearchTree.remove(3);
        System.out.println(binarySearchTree.getRoot());
        System.out.println("==================");
        BinarySearchTree.print(binarySearchTree.getRoot());
    }

    public void testGetMaximum() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6};
        createTree(input);
        System.out.println(binarySearchTree.getMaximum(binarySearchTree.getRoot()));
    }

}
