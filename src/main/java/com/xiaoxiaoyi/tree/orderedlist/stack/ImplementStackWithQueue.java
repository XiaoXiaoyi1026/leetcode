package com.xiaoxiaoyi.tree.orderedlist.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xiaoxiaoyi
 * 用队列实现栈
 */
public class ImplementStackWithQueue {

    public static class MyStack<T> {
        private Queue<T> pushQueue;
        private Queue<T> popQueue;

        MyStack() {
            pushQueue = new LinkedList<>();
            popQueue = new LinkedList<>();
        }

        public void push(T element) {
            pushQueue.add(element);
        }

        public T pop() {
            if (pushQueue.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            while (pushQueue.size() > 1) {
                popQueue.add(pushQueue.poll());
            }
            Queue<T> tmp = pushQueue;
            pushQueue = popQueue;
            popQueue = tmp;
            return popQueue.poll();
        }

    }

}
