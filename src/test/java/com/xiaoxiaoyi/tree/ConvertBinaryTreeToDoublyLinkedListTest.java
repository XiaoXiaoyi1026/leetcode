package com.xiaoxiaoyi.tree;

import com.xiaoxiaoyi.tree.orderedlist.BinarySearchTree;
import com.xiaoxiaoyi.tree.orderedlist.BinarySearchBinarySearchTreeTest;

public class ConvertBinaryTreeToDoublyLinkedListTest extends BinarySearchBinarySearchTreeTest {

    public void testConvertBinaryTreeToDoublyLinkedList() {
        int[] nodeValues = {1, 2, 3, 4, 5, 6, 7, 8};
        createTree(nodeValues);
        com.xiaoxiaoyi.tree.Tree.printTree(binarySearchTree.root);
        BinarySearchTree.Node<Integer> head = ConvertBinaryTreeToDoublyLinkedList.convert(binarySearchTree);
        System.out.println(head);
        ConvertBinaryTreeToDoublyLinkedList.printDoublyLinkedList(head);
    }

}
