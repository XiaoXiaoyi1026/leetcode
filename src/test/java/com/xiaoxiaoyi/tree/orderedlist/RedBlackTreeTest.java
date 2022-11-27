package com.xiaoxiaoyi.tree.orderedlist;

import java.util.Comparator;

public class RedBlackTreeTest extends BinarySearchTreeTest {

    private final RedBlackTree<Integer> redBlackTree = new RedBlackTree<>(
            Comparator.comparingInt(o -> o.element)
    );

    public void testInsertNode() {
        redBlackTree.insert(1);
    }

}
