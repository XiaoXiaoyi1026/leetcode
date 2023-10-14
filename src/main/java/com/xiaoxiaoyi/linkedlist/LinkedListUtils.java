package com.xiaoxiaoyi.linkedlist;

import org.jetbrains.annotations.NotNull;

public class LinkedListUtils {

    public static String toSingleString(Node<Integer> head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head).append("->");
            head = head.next;
        }
        return String.valueOf(sb.append("null"));
    }

    /**
     * 生成单链表
     *
     * @param arr 原始数组
     * @return 单链表头
     */
    public static Node<Integer> generateSingle(@NotNull int[] arr) {
        Node<Integer> cur;
        Node<Integer> pre = null;
        Node<Integer> head = null;
        for (int value : arr) {
            cur = new Node<>(value);
            if (head == null) {
                head = cur;
            }
            if (pre != null) {
                pre.next = cur;
            }
            pre = cur;
        }
        return head;
    }

}
