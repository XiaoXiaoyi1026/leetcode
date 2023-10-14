package com.xiaoxiaoyi.linkedlist;

/**
 * 链表节点
 */
public class Node<T> {

    Node<T> next;

    Node<T> pre;

    T value;

    Node(T value) {
        next = null;
        pre = null;
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
