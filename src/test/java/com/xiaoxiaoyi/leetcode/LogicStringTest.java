package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class LogicStringTest extends TestCase {

    public String generateInput(int length) {
        char[] chars = new char[length];
        for (int i = 0; i < length; i += 2) {
            chars[i] = Math.random() < 0.5 ? '0' : '1';
        }

        for (int i = 1; i < length; i += 2) {
            double random;
            do {
                random = Math.random();
                if (random < 0.3) {
                    chars[i] = '&';
                } else if (random < 0.6) {
                    chars[i] = '|';
                } else if (random < 0.9) {
                    chars[i] = '^';
                }
            } while (random >= 0.9);
        }
        return String.valueOf(chars);
    }

    public void testLogicString() {
        for (int i = 0; i < 20; i++) {
            String logicString = generateInput((int) (Math.random() * 5) * 2 + 1);
            System.out.println(logicString);
            System.out.println("expression: " +
                    LogicString.expression(logicString, true));
            System.out.println("dp expression: " +
                    LogicString.dpExpression(logicString, true));
        }
        LogicString.expression("1^1|0&0&1", true);
        LogicString.dpExpression("1^1|0&0&1", true);
    }

}
