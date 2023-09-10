package com.xiaoxiaoyi.tree.orderedlist;

import com.xiaoxiaoyi.tree.ElementBinaryTree;
import junit.framework.TestCase;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

import static com.xiaoxiaoyi.tree.BinarySearchTree.print;

public class SelfBalancingBinaryTreeTest extends TestCase {

    private SelfBalancingBinaryTree<Integer> rotateTree;

    private void createTree(@NotNull int[] input) {
        rotateTree = new SelfBalancingBinaryTree<>(
                Comparator.comparingInt(ElementBinaryTree.Node::getElement)
        );
        for (int i : input) {
            rotateTree.insert(i);
        }
        print(rotateTree.getRoot());
    }

    public void testRotateLeft() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6, 3, 8, 1, 0};
        createTree(input);
        rotateTree.setRoot(rotateTree.rotateLeft(rotateTree.getRoot()));
        System.out.println("======================");
        print(rotateTree.getRoot());
        System.out.println("======================");
    }

    public void testRotateRight() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6};
        createTree(input);
        rotateTree.setRoot(rotateTree.rotateRight(rotateTree.getRoot()));
        System.out.println("======================");
        print(rotateTree.getRoot());
        System.out.println("======================");
    }
}
