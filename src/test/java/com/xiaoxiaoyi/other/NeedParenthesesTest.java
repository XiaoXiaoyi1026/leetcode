package com.xiaoxiaoyi.other;

import junit.framework.TestCase;

public class NeedParenthesesTest extends TestCase {

    private String generateRandomParenthesesString(int length) {
        StringBuilder res = new StringBuilder();
        int tmp;
        for (int i = 0; i < length; i++) {
            // 0, 1, 2
            tmp = (int) (Math.random() * 3);
            switch (tmp) {
                case 0:
                    res.append("(");
                case 1:
                    res.append(")");
                default:
                    res.append("i");
            }
        }
        return String.valueOf(res);
    }

    public void testNeedParentheses() {
        int testTimes = 10, length;
        String testString;
        for (int i = 0; i < testTimes; i++) {
            length = (int) (Math.random() * 20);
            testString = generateRandomParenthesesString(length);
            System.out.println(testString + " -> " + NeedParentheses.needParentheses(testString));
        }
    }

}
