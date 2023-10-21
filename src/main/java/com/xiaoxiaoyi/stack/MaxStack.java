package com.xiaoxiaoyi.stack;

import com.xiaoxiaoyi.heap.HeapGreater;

/**
 * 最大栈
 */
public class MaxStack<E extends Comparable<E>> {

    /**
     * 栈内节点
     *
     * @param <E> 数据类型
     */
    static class Node<E> {
        /**
         * 值
         */
        E value;
        /**
         * 前一个节点
         */
        Node<E> pre;
        /**
         * 下一个节点
         */
        Node<E> next;

        public Node(E value) {
            this.value = value;
            pre = null;
            next = null;
        }
    }

    /**
     * 栈顶
     */
    Node<E> top;

    /**
     * 加强堆, 用于快速查找栈内最大元素
     */
    HeapGreater<Node<E>> heapGreater;

    static final int DEFAULT_CAPACITY = 16;

    MaxStack() {
        this(DEFAULT_CAPACITY);
    }

    MaxStack(int capacity) {
        top = null;
        // 由于默认是小根堆, 故需要转换成大根堆
        heapGreater = new HeapGreater<>(capacity, (o1, o2) -> o2.value.compareTo(o1.value));
    }

    boolean isEmpty() {
        return top == null;
    }

    boolean push(Node<E> node) {
        if (heapGreater.isFull() || heapGreater.contains(node)) {
            return false;
        }
        if (top != null) {
            top.pre = node;
            node.next = top;
        }
        top = node;
        heapGreater.add(node);
        return true;
    }

    public boolean push(E e) {
        return push(new Node<>(e));
    }

    public E pop() {
        E res = top.value;
        if (top.next == null) {
            top = null;
        } else {
            top = top.next;
            top.pre = null;
        }
        heapGreater.remove(top);
        return res;
    }

    public E peek() {
        return top.value;
    }

    public E peekMax() {
        return heapGreater.peek().value;
    }

    public E popMax() {
        Node<E> node = heapGreater.remove();
        if (node == top) {
            return pop();
        }
        node.pre.next = node.next;
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        heapGreater.remove(node);
        if (heapGreater.isEmpty()) {
            top = null;
        }
        return node.value;
    }
}
