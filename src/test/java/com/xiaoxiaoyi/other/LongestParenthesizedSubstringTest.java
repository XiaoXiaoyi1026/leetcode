package com.xiaoxiaoyi.other;

import junit.framework.TestCase;

public class LongestParenthesizedSubstringTest extends TestCase {

    public void testLongestParenthesizedSubstring() {
        String str = "((()()()())";
        int ans = LongestParenthesizedSubstring.longestParenthesizedSubstring(str);
        System.out.println(ans);
    }

}
