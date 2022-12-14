package com.xiaoxiaoyi.stack;

import junit.framework.TestCase;

public class MinStackTest extends TestCase {

    public void testMinStack() {
        MinStack<Integer> minStack = new MinStack<>();
        minStack.push(3);
        minStack.push(4);
        System.out.println(minStack.getMin());
        minStack.push(2);
        System.out.println(minStack.getMin());
        minStack.push(6);
        System.out.println(minStack.getMin());
        System.out.println("pop: " + minStack.pop());
        System.out.println(minStack.getMin());
        System.out.println("pop: " + minStack.pop());
        System.out.println(minStack.getMin());
    }

}
