package com.xiaoxiaoyi.recursion;

import junit.framework.TestCase;

import java.util.Stack;

public class ReverseStackWithRecursionTest extends TestCase {

    public void testReverseStackWithRecursion() {
        Stack<Object> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        ReverseStackWithRecursion.reverseStackWithRecursion(stack);
        for (Object o : stack) {
            System.out.println(o);
        }
    }

}
