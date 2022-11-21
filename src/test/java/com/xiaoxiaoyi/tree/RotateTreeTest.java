package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

import java.util.Comparator;

public class RotateTreeTest extends TestCase {

    private RotateSearchTree rotateTree;

    private void createTree(int[] input) {
        rotateTree = new RotateSearchTree(
                Comparator.comparingInt(o -> (Integer) o.element)
        );
        for (int i : input) {
            rotateTree.insert(i);
        }
    }

    public void testRotateLeft() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6, 3, 8, 1, 0};
        createTree(input);
        // 原来的根节点的右节点
        System.out.println(rotateTree.root.getRight());
        rotateTree.root = (
                rotateTree.rotateLeft(rotateTree.root)
        );
        System.out.println("======================");
        Morris.morrisInorderTraversal(rotateTree.root);
        System.out.println("======================");
        // 旋转后的根节点(应该等于原根节点的右节点)
        System.out.println(rotateTree.root);
    }

    public void testRotateRight() {
        int[] input = new int[]{3, 5, 2, 7, 4, 7, 6};
        createTree(input);
        System.out.println(rotateTree.root.getLeft());
        rotateTree.root =
                rotateTree.rotateRight(rotateTree.root);
        System.out.println("======================");
        Morris.morrisInorderTraversal(rotateTree.root);
        System.out.println("======================");
        System.out.println(rotateTree.root);
    }
}
