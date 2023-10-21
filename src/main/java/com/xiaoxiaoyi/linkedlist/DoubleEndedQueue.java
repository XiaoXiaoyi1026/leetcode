package com.xiaoxiaoyi.linkedlist;

public class DoubleEndedQueue<T> {

    int size;

    Node<T> head;

    Node<T> tail;

    int capacity;

    private static final int DEFAULT_CAPACITY = 10;

    public DoubleEndedQueue(int capacity) {
        size = 0;
        head = tail = null;
        this.capacity = capacity;
    }

    public DoubleEndedQueue() {
        this(DEFAULT_CAPACITY);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean addHead(T value) {
        return addHead(new Node<>(value));
    }

    private boolean addHead(Node<T> node) {
        if (isFull()) {
            return false;
        }
        if (head != null) {
            head.pre = node;
            node.next = head;
        } else {
            tail = node;
        }
        head = node;
        size++;
        return true;
    }

    public T removeHead() {
        Node<T> remove = head;
        head = head.next;
        remove.next = null;
        head.pre = null;
        size--;
        return remove.value;
    }

    public boolean addTail(T value) {
        return addTail(new Node<>(value));
    }

    private boolean addTail(Node<T> node) {
        if (isFull()) {
            return false;
        }
        if (tail != null) {
            tail.next = node;
            node.pre = tail;
        } else {
            head = node;
        }
        tail = node;
        size++;
        return true;
    }

    public T removeTail() {
        Node<T> remove = tail;
        tail = tail.pre;
        tail.next = null;
        remove.pre = null;
        size--;
        return remove.value;
    }

}
