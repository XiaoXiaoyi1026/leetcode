package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

public class BinarySearchTreeMaxDistanceTest extends TestCase {

    public void testTreeDp() {
        TreeMaxDistance.Node node1 = new TreeMaxDistance.Node();
        TreeMaxDistance.Node node2 = new TreeMaxDistance.Node();
        TreeMaxDistance.Node node3 = new TreeMaxDistance.Node();
        TreeMaxDistance.Node node4 = new TreeMaxDistance.Node();
        TreeMaxDistance.Node node5 = new TreeMaxDistance.Node();
        TreeMaxDistance.Node node6 = new TreeMaxDistance.Node();
        TreeMaxDistance.Node node7 = new TreeMaxDistance.Node();
        TreeMaxDistance.Node node8 = new TreeMaxDistance.Node();
        TreeMaxDistance.Node node9 = new TreeMaxDistance.Node();
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node6.setRight(node7);
        node5.setLeft(node8);
        node8.setLeft(node9);
        System.out.println(TreeMaxDistance.maxDistance(node1));
    }

}
