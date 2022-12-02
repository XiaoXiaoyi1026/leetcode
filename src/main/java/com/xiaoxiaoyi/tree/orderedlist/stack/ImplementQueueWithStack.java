package com.xiaoxiaoyi.tree.orderedlist.stack;

import java.util.Stack;

/**
 * @author xiaoxiaoyi
 * 只使用堆栈实现队列
 */
public class ImplementQueueWithStack {

    public static class MyQueue<T extends Comparable<T>> {
        private final Stack<T> pushStack;
        private final Stack<T> popStack;

        MyQueue() {
            pushStack = new Stack<>();
            popStack = new Stack<>();
        }

        public void add(T element) {
            pushStack.push(element);
        }

        public T poll() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
            if (popStack.isEmpty()) {
                throw new RuntimeException("Queue is empty");
            }
            return popStack.pop();
        }
    }

}
