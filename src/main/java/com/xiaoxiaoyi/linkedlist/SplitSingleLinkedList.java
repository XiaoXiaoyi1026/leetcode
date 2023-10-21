package com.xiaoxiaoyi.linkedlist;

/**
 * 切割单链表
 * 给定一个值x, 要求在不改变链表中的元素相对次序的情况下
 * 将链表分为<=x和>x的两个部分
 * 比如 6 3 1 1 2 5 8 x = 4
 * 返回 3 1 1 2 6 5 8
 */
public class SplitSingleLinkedList {

    private SplitSingleLinkedList() {}

    public static Node<Integer> split(Node<Integer> head, int x) {
        Node<Integer> leftHead = null;
        Node<Integer> leftTail = null;
        Node<Integer> rightHead = null;
        Node<Integer> rightTail = null;
        Node<Integer> next;
        while (head != null) {
            next = head.next;
            if (head.value > x) {
                if (rightHead == null) {
                    rightHead = head;
                } else {
                    rightTail.next = head;
                }
                rightTail = head;
            } else {
                if (leftHead == null) {
                    leftHead = head;
                } else {
                    leftTail.next = head;
                }
                leftTail = head;
            }
            head = next;
        }
        if (leftTail == null) {
            return rightHead;
        }
        leftTail.next = rightHead;
        return leftHead;
    }

    public static void main(String[] args) {
        int[] arr = {6, 3, 1, 1, 2, 5, 8};
        Node<Integer> head = split(LinkedListUtils.generateSingle(arr), 4);
        System.out.println(LinkedListUtils.toSingleString(head));
    }

}
