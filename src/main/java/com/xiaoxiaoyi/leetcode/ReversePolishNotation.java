package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.StringUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 将逆波兰式翻译为等价的中序表达式
 * 要求尽量少加小括号
 */
public class ReversePolishNotation {

    /**
     * 表达式类型
     */
    enum NotationType {
        /**
         * 加或减连接
         */
        ADD_OR_SUB,

        /**
         * 乘除连接
         */
        MUL_OR_DIV,
        /**
         * 单数字
         */
        NUMBER
    }

    /**
     * 表达式
     */
    static class Notation {
        /**
         * 表达式串
         */
        String notationString;

        /**
         * 表达式类型
         */
        NotationType notationType;
    }

    @NotNull
    @Contract(pure = true)
    public static String parentheses(String pattern) {
        return "(" + pattern + ")";
    }

    @NotNull
    public static String getInorderNotation(String inversePolishPattern) {
        if (StringUtils.isEmpty(inversePolishPattern)) {
            return "";
        }
        String[] patterns = inversePolishPattern.split(" ");
        Deque<Notation> helpStack = new LinkedList<>();
        Notation current;
        Notation left;
        Notation right;
        for (String pattern : patterns) {
            current = new Notation();
            if ("+".equals(pattern) || "-".equals(pattern)) {
                right = helpStack.pop();
                left = helpStack.pop();
                current.notationString = left.notationString + pattern + right.notationString;
                current.notationType = NotationType.ADD_OR_SUB;
            } else if ("*".equals(pattern) || "/".equals(pattern)) {
                right = helpStack.pop();
                if (right.notationType == NotationType.ADD_OR_SUB) {
                    right.notationString = parentheses(right.notationString);
                }
                left = helpStack.pop();
                if (left.notationType == NotationType.ADD_OR_SUB) {
                    left.notationString = parentheses(left.notationString);
                }
                current.notationString = left.notationString + pattern + right.notationString;
                current.notationType = NotationType.MUL_OR_DIV;
            } else {
                current.notationString = pattern;
                current.notationType = NotationType.NUMBER;
            }
            helpStack.push(current);
        }
        return helpStack.pop().notationString;
    }

    public static void main(String[] args) {
        String reversePolishNotation = "3 -5 13 + * 6 2 3 - 2 + / + 4 5 3 * * -";
        System.out.println(getInorderNotation(reversePolishNotation));
    }

}
