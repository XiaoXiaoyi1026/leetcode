package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class LongestNonRepeatingSubstringTest extends TestCase {

    public String generateRandomString() {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G',
                'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                'V', 'W', 'X', 'Y', 'Z'
        };
        while (Math.random() < 0.7) {
            stringBuilder.append(chars[(int) (Math.random() * 27)]);
        }
        return String.valueOf(stringBuilder);
    }

    public void testLongestNonRepeatingSubstring() {
        for (int i = 0; i < 20; i++) {
            String input = generateRandomString();
            System.out.println(input);
            System.out.println(LongestNonRepeatingSubstring.get(input));
        }
    }

}
