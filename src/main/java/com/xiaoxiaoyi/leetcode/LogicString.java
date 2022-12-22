package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 给定一个逻辑字符串, 只包含&(与), |(或), ^(异或), 0(假), 1(真), 5种字符
 * 问一共有几种结合方式(画括号), 使得逻辑运算的结果为target(真或假)
 */
public class LogicString {

    public static int expression(String logicString, boolean desired) {
        if (logicString == null || "".equals(logicString)) {
            return 0;
        }
        char[] exp = logicString.toCharArray();
        if (isNotValid(exp)) {
            return 0;
        }
        return process(exp, desired, 0, exp.length - 1);
    }

    /**
     * left和right上不能是逻辑符号
     */
    private static int process(char[] exp, boolean desired, int left, int right) {
        if (left == right) {
            // base case
            if (exp[left] == '1') {
                // 如果期待结果为true而当前数字是1(真), 则说明可行, 返回1种方法
                return desired ? 1 : 0;
            } else {
                // 如果期待结果为false而当前数字是0(假), 则说明可行, 返回1种方法
                return desired ? 0 : 1;
            }
        }
        int res = 0;
        if (desired) {
            // 如果要达成的结果是true
            for (int i = left + 1; i < right; i += 2) {
                char sign = exp[i];
                if (sign == '&') {
                    // 符号位&, 则需要两边都为true
                    res += process(exp, true, left, i - 1) *
                            process(exp, true, i + 1, right);
                } else if (sign == '|') {
                    // 符号位是|, 则任意一边为true就行
                    int leftTrue = process(exp, true, left, i - 1);
                    int leftFalse = process(exp, false, left, i - 1);
                    int rightTrue = process(exp, true, i + 1, right);
                    int rightFalse = process(exp, false, i + 1, right);
                    res += leftTrue * rightTrue;
                    res += leftTrue * rightFalse;
                    res += leftFalse * rightTrue;
                } else {
                    // 符号位是^, 要达成true的结果, 需要左右两边结果不同
                    res += process(exp, true, left, i - 1) *
                            process(exp, false, i + 1, right);
                    res += process(exp, false, left, i - 1) *
                            process(exp, true, i + 1, right);
                }
            }
        } else {
            // 如果要达成的结果是false
            for (int i = left + 1; i < right; i += 2) {
                char sign = exp[i];
                if (sign == '|') {
                    // 符号位|, 则需要两边都为false
                    res += process(exp, false, left, i - 1) *
                            process(exp, false, i + 1, right);
                } else if (sign == '&') {
                    // 符号位是&, 则任意一边为false就行
                    int leftTrue = process(exp, true, left, i - 1);
                    int leftFalse = process(exp, false, left, i - 1);
                    int rightTrue = process(exp, true, i + 1, right);
                    int rightFalse = process(exp, false, i + 1, right);
                    res += leftFalse * rightFalse;
                    res += leftTrue * rightFalse;
                    res += leftFalse * rightTrue;
                } else {
                    // 符号位是^, 要达成false的结果, 需要左右两边结果相同
                    res += process(exp, true, left, i - 1) *
                            process(exp, true, i + 1, right);

                    res += process(exp, false, left, i - 1) *
                            process(exp, false, i + 1, right);
                }
            }
        }
        return res;
    }

    public static boolean isNotValid(char[] exp) {
        if ((exp.length & 1) == 0) {
            // 长度必须为奇数
            return true;
        }
        for (int i = 0; i < exp.length; i += 2) {
            char cur = exp[i];
            if (cur != '0' && cur != '1') {
                // 偶数位置上必须是0 1
                return true;
            }
        }

        for (int i = 1; i < exp.length; i += 2) {
            char cur = exp[i];
            if (cur != '&' && cur != '|' && cur != '^') {
                // 奇数位置上必须是& | ^
                return true;
            }
        }
        return false;
    }

    public static int dpExpression(String logicString, boolean desired) {
        if (logicString == null || "".equals(logicString)) {
            return 0;
        }
        char[] exp = logicString.toCharArray();
        if (isNotValid(exp)) {
            return 0;
        }
        return dpProcess(exp, desired);
    }

    public static int dpProcess(char[] exp, boolean desired) {
        int n = exp.length;
        // trueMap里面的当前数字默认代表的就是真(1)
        int[][] trueMap = new int[n][n];
        int[][] falseMap = new int[n][n];
        for (int i = 0; i < n; i += 2) {
            trueMap[i][i] = exp[i] == '1' ? 1 : 0;
            falseMap[i][i] = exp[i] == '1' ? 0 : 1;
        }
        // row代表left, col代表right
        for (int row = n - 3; row >= 0; row -= 2) {
            for (int col = row + 2; col < n; col += 2) {
                // i代表当前遍历到的符号位
                for (int i = row + 1; i < col; i += 2) {
                    char sign = exp[i];
                    if (sign == '&') {
                        // desired == true的情况
                        trueMap[row][col] += trueMap[row][i - 1] * trueMap[i + 1][col];
                        // desired == false的情况
                        falseMap[row][col] += falseMap[row][i - 1] * falseMap[i + 1][col];
                        falseMap[row][col] += falseMap[row][i - 1] * trueMap[i + 1][col];
                        falseMap[row][col] += trueMap[row][i - 1] * falseMap[i + 1][col];
                    } else if (sign == '|') {
                        // 同样分desired为true或者false
                        trueMap[row][col] += trueMap[row][i - 1] * trueMap[i + 1][col];
                        trueMap[row][col] += trueMap[row][i - 1] * falseMap[i + 1][col];
                        trueMap[row][col] += falseMap[row][i - 1] * trueMap[i + 1][col];

                        falseMap[row][col] += falseMap[row][i - 1] * falseMap[i + 1][col];
                    } else {
                        trueMap[row][col] += trueMap[row][i - 1] * falseMap[i + 1][col];
                        trueMap[row][col] += falseMap[row][i - 1] * trueMap[i + 1][col];

                        falseMap[row][col] += trueMap[row][i - 1] * trueMap[i + 1][col];
                        falseMap[row][col] += falseMap[row][i - 1] * falseMap[i + 1][col];
                    }
                }
            }
        }
        return desired ? trueMap[0][n - 1] : falseMap[0][n - 1];
    }
}
