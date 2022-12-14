package com.xiaoxiaoyi.stack;

import java.util.Stack;

/**
 * @author xiaoxiaoyi
 * 给你一个乱序数组, 在栈中, 要求只用1个栈结构, 给原来栈中的元素做一个排序
 */
public class StackSort {

    public static void stackSort(Stack<Integer> stack) {
        // 辅助栈
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            Integer cur = stack.pop();
            if (help.isEmpty()) {
                help.push(cur);
            } else {
                if (cur < help.peek()) {
                    help.push(cur);
                } else {
                    stack.push(help.pop());
                    stack.push(cur);
                }
            }
        }
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }
}
