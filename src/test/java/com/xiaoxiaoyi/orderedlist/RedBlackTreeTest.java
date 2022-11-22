package com.xiaoxiaoyi.orderedlist;

import java.util.Comparator;

public class RedBlackTreeTest extends BinarySearchTreeTest {

    private final RedBlackTree redBlackTree = new RedBlackTree(
            Comparator.comparingInt(o -> (Integer) o.element)
    );

    public void testInsertNode() {
        redBlackTree.insert(1);
    }

}
