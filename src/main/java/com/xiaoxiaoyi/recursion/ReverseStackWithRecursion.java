package com.xiaoxiaoyi.recursion;

import java.util.Stack;

/**
 * @author xiaoxiaoyi
 * 使用递归翻转栈
 */
public class ReverseStackWithRecursion {

    public static void reverseStackWithRecursion(Stack<Object> stack) {
        if (stack == null || stack.isEmpty()) {
            return;
        }
        // 记录栈底元素
        Object res = f(stack);
        // 递归翻转除栈底元素外的栈
        reverseStackWithRecursion(stack);
        // 都逆序好了之后再把栈底元素加回去
        stack.push(res);
    }

    /**
     * 移除栈底元素
     *
     * @param stack 栈
     * @return 移除栈底后的栈
     */
    private static Object f(Stack<Object> stack) {
        // 弹出栈顶结点
        Object result = stack.pop();
        if (stack.isEmpty()) {
            // 如果弹出后栈空了，则返回栈底结点
            return result;
        } else {
            // 如果弹出后栈不为空，则继续向下
            Object last = f(stack);
            // 向下返回后再将当前栈顶元素压回栈中
            stack.push(result);
            // 返回下面返回的栈底元素
            return last;
        }
    }

}
