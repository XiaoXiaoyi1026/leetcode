package com.xiaoxiaoyi.stack;

/**
 * 最大栈测试
 */
public class MaxStackTest {

    public static void main(String[] args) {
        MaxStack<Integer> stack = new MaxStack<>();
        stack.push(1);
        System.out.println(stack.peekMax());
        stack.push(2);
        System.out.println(stack.peekMax());
        stack.push(3);
        System.out.println(stack.peekMax());
        stack.push(4);
        System.out.println(stack.peekMax());
        stack.push(5);
        System.out.println(stack.peekMax());
        while (!stack.isEmpty()) {
            System.out.println(stack.popMax());
        }
    }

}
