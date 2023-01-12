package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

import java.util.Comparator;

public class AdjustBinarySearchTreeTest extends TestCase {

    public void testFindWrongNode() {
        AdjustBinarySearchTree<Integer> tree = new AdjustBinarySearchTree<>(
                Comparator.comparingInt(o -> o.element)
        );
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.exchangeNodes(1, 2);
        AdjustBinarySearchTree.print(tree);
        tree.findWrongNodes();
        System.out.println(tree.getWrongNodes());
        tree.exchangeWrongNodes();
        BinarySearchTree.print(tree);
    }

}
