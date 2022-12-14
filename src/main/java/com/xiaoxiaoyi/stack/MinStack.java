package com.xiaoxiaoyi.stack;

import java.util.Stack;

/**
 * @author xiaoxiaoyi
 * 实现一个栈的基本功能的基础上, 让获取栈中最小值的方法复杂度为O(1)
 */
public class MinStack<T extends Comparable<T>> {

    private final Stack<T> stack;
    private final Stack<T> minStack;

    MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(T element) {
        stack.push(element);
        minStack.push(
                (minStack.isEmpty() || element.compareTo(minStack.peek()) < 0)
                        ? element : minStack.peek()
        );
    }

    public T pop() {
        minStack.pop();
        return stack.pop();
    }

    public T getMin() {
        return minStack.peek();
    }

}
