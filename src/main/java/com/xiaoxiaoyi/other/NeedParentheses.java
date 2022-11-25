package com.xiaoxiaoyi.other;

/**
 * 补充完整括号表达式需要添加的括号数
 */
public class NeedParentheses {

    public static int needParentheses(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int count = 0, ans = 0;
        for (char aChar : chars) {
            if (aChar == '(') {
                count++;
            } else if (aChar == ')') {
                if (count == 0) {
                    // count = 0时, 说明当前位置的右括号缺左括号, 给一个左括号(ans++)
                    ans++;
                } else {
                    // 如果此时count不为0, 则当前位置的右括号可以和一个左括号抵消, count--;
                    count--;
                }
            }
        }
        // 返回缺的左括号 + 缺的右括号
        return ans + count;
    }

}
