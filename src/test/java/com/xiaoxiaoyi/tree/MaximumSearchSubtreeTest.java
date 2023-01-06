package com.xiaoxiaoyi.tree;

import com.xiaoxiaoyi.tree.orderedlist.MaximumSearchSubtree;
import junit.framework.TestCase;

public class MaximumSearchSubtreeTest extends TestCase {

    public MaximumSearchSubtree.Node<Integer> generateTree() {
        MaximumSearchSubtree.Node<Integer> node1 = new MaximumSearchSubtree.Node<>(1);
        MaximumSearchSubtree.Node<Integer> node2 = new MaximumSearchSubtree.Node<>(2);
        MaximumSearchSubtree.Node<Integer> node3 = new MaximumSearchSubtree.Node<>(3);
        MaximumSearchSubtree.Node<Integer> node4 = new MaximumSearchSubtree.Node<>(4);
        MaximumSearchSubtree.Node<Integer> node5 = new MaximumSearchSubtree.Node<>(5);
        MaximumSearchSubtree.Node<Integer> node6 = new MaximumSearchSubtree.Node<>(6);
        MaximumSearchSubtree.Node<Integer> node7 = new MaximumSearchSubtree.Node<>(7);
        MaximumSearchSubtree.Node<Integer> node8 = new MaximumSearchSubtree.Node<>(8);
        MaximumSearchSubtree.Node<Integer> node9 = new MaximumSearchSubtree.Node<>(9);
        MaximumSearchSubtree.Node<Integer> node10 = new MaximumSearchSubtree.Node<>(10);
        setChildes(node8, node6, node5);
        setChildes(node5, node7, null);
        setChildes(node6, node3, node10);
        setChildes(node3, node1, node4);
        setChildes(node10, node9, node2);
        return node8;
    }

    public void setChildes(MaximumSearchSubtree.Node<Integer> father,
                           MaximumSearchSubtree.Node<Integer> left,
                           MaximumSearchSubtree.Node<Integer> right) {
        father.left = left;
        father.right = right;
    }

    public void testGet() {
        MaximumSearchSubtree.Node<Integer> node = generateTree();
        System.out.println(MaximumSearchSubtree.get(node));
    }

    public void testGetContribution() {
        MaximumSearchSubtree.Node<Integer> node = generateTree();
        MaximumSearchSubtree.printContribution(node);
    }

}
