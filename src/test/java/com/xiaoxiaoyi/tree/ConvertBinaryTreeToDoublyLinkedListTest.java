package com.xiaoxiaoyi.tree;

import com.xiaoxiaoyi.tree.orderedlist.BinarySearchTree;
import com.xiaoxiaoyi.tree.orderedlist.BinarySearchTreeTest;

public class ConvertBinaryTreeToDoublyLinkedListTest extends BinarySearchTreeTest {

    public void testConvertBinaryTreeToDoublyLinkedList() {
        int[] nodeValues = {1, 2, 3, 4, 5, 6, 7, 8};
        createTree(nodeValues);
        Tree.printTree(binarySearchTree.root);
        BinarySearchTree.BinarySearchTreeNode<Integer> head = ConvertBinaryTreeToDoublyLinkedList.convert(binarySearchTree);
        System.out.println(head);
        ConvertBinaryTreeToDoublyLinkedList.printDoublyLinkedList(head);
    }

}
