package com.xiaoxiaoyi.mihoyo;

import java.util.Stack;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 判断出栈序列的合法性
 * @date 3/18/2023 4:19 PM
 */
public class Zhan {

    public static void main(String[] args) {
        int[] pushSequence = new int[]{1, 2, 3, 4, 5};
        int[] popSequence = new int[] {4, 3, 5, 2, 1};
        System.out.println(isPopOrder(pushSequence, popSequence));
    }

    public static boolean isPopOrder (int[] pushSequence, int[] popSequence) {
        // write code here
        if (pushSequence == null) {
            return popSequence == null;
        }
        if (popSequence == null) {
            return false;
        }
        int m = pushSequence.length;
        int n = popSequence.length;
        if (m != n) {
            return false;
        }
        int popIndex = 0;
        Stack<Integer> help = new Stack<>();
        for (int num : pushSequence) {
            help.push(num);
            while (!help.isEmpty() && help.peek() == popSequence[popIndex]) {
                popIndex++;
                help.pop();
            }
        }
        return help.isEmpty();
    }

}
