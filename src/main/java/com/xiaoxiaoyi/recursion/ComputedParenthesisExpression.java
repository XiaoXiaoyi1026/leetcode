package com.xiaoxiaoyi.recursion;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

/**
 * @author xiaoxiaoyi
 * 给定一个字符串, 其中字符包含数字, 加减乘除符号, 和左右小括号, 计算出表达式的结果
 */
public class ComputedParenthesisExpression {

    public static int getAnswer(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return value(str.toCharArray(), 0)[0];
    }

    /**
     * @return res[0] 代表当前负责的这一区域计算的结果, res[1]代表当前负责的这一区域计算到的位置
     */
    @NotNull
    @Contract("_, _ -> new")
    private static int[] value(@NotNull char[] chars, int cur) {
        Stack<String> help = new Stack<>();
        int num = 0;
        // 越界或者遇到右括号时退出
        while (cur < chars.length && chars[cur] != ')') {
            char c = chars[cur];
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else if (c == '(') {
                // 遇到左括号, 递归调用
                int[] res = value(chars, cur + 1);
                num = res[0];
                cur = res[1];
            } else {
                // 如果遇到的是普通的计算符号
                addNum(help, num);
                help.push(String.valueOf(c));
                num = 0;
            }
            cur++;
        }
        addNum(help, num);
        return new int[]{getAnswer(help), cur};
    }

    private static void addNum(@NotNull Stack<String> help, int num) {
        if (!help.isEmpty()) {
            String sign = help.pop();
            if ("+".equals(sign) || "-".equals(sign)) {
                help.push(sign);
            } else {
                int cur = Integer.parseInt(help.pop());
                num = "*".equals(sign) ? (cur * num) : (cur / num);
            }
        }
        help.push(String.valueOf(num));
    }

    private static int getAnswer(@NotNull Stack<String> help) {
        int ans = Integer.parseInt(help.pop());
        while (!help.isEmpty()) {
            String sign = help.pop();
            if ("+".equals(sign)) {
                ans += Integer.parseInt(help.pop());
            } else {
                ans = Integer.parseInt(help.pop()) - ans;
            }
        }
        return ans;
    }

    public static int compute(String parenthesisExpression) {
        if (parenthesisExpression == null || parenthesisExpression.length() == 0) {
            return 0;
        }
        return f(parenthesisExpression.toCharArray(), 0)[0];
    }

    @NotNull
    @Contract("_, _ -> new")
    private static int[] f(@NotNull char[] chars, int cur) {
        // 辅助栈
        Stack<String> help = new Stack<>();
        // 数字
        StringBuilder num = new StringBuilder();
        while (cur < chars.length && chars[cur] != ')') {
            char c = chars[cur];
            if (c == '-') {
                if (cur > 0) {
                    char pre = chars[cur - 1];
                    if (pre >= '0' && pre <= '9' || pre == ')') {
                        // 如果前面是数字或者右括号, 那么说明这个'-'代表减号, 如果不为空判断栈顶是否是乘号或者除号
                        if (!help.isEmpty()) {
                            // 弹出栈顶
                            String sign = help.pop();
                            if ("*".equals(sign) || "/".equals(sign)) {
                                // 如果此时的栈顶是*或者/, 则与当前获得的num计算后再push回栈中
                                help.push(String.valueOf(
                                        calc(help.pop(), String.valueOf(num), sign)
                                ));
                            } else {
                                // 如果栈顶是+或者-, 则将符号和数字入栈
                                help.push(sign);
                                help.push(String.valueOf(num));
                            }
                        } else {
                            // 如果栈为空, 则直接将num加进去
                            help.push(String.valueOf(num));
                        }
                        // 减号入栈
                        help.push("-");
                        // 计算完后重置num
                        num = new StringBuilder();
                    } else {
                        // 如果-代表的是数字前面的负号, 则加入num中
                        num.append(c);
                    }
                } else {
                    // 如果cur == 0, 那么减号只可能表示负号
                    num.append(c);
                }
            } else if (c >= '0' && c <= '9') {
                // 当前字符是普通的数字字符
                num.append(c);
            } else if (c == '(') {
                // 遇到左括号, 跨过左括号开始递归调用
                int[] res = f(chars, cur + 1);
                // res[0]为小括号内的计算结果, res[1]为小括号最终计算到了哪个位置
                num.append(res[0]);
                // 准备从小括号后面开始继续计算
                cur = res[1];
            } else {
                // 如果遇到的符号是+或者*或者/, 判断栈顶是不是*或/, 如果是, 则计算后再push
                if (!help.isEmpty()) {
                    String sign = help.pop();
                    if ("*".equals(sign) || "/".equals(sign)) {
                        help.push(String.valueOf(
                                calc(help.pop(), String.valueOf(num), sign)
                        ));
                    } else {
                        help.push(sign);
                        help.push(String.valueOf(num));
                    }
                } else {
                    help.push(String.valueOf(num));
                }
                help.push(String.valueOf(c));
                num = new StringBuilder();
            }
            cur++;
        }
        // 如果cur到了最后, 则计算最终结果并返回
        int ans = Integer.parseInt(String.valueOf(num));
        while (!help.isEmpty()) {
            String sign = help.pop();
            ans = calc(help.pop(), String.valueOf(ans), sign);
        }
        return new int[]{ans, cur};
    }

    private static int calc(String numStr1, String numStr2, @NotNull String sign) {
        int num1 = Integer.parseInt(numStr1);
        int num2 = Integer.parseInt(numStr2);
        return switch (sign) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new RuntimeException("Calc sign wrong!");
        };
    }
}
