package com.xiaoxiaoyi.tree.orderedlist.stack;

import junit.framework.TestCase;

import java.util.Stack;

public class StackSortTest extends TestCase {

    public void testStackSort() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(4);
        stack.push(1);
        stack.push(6);
        stack.push(3);
        stack.push(5);
        StackSort.stackSort(stack);
        System.out.println(stack);
    }

}
