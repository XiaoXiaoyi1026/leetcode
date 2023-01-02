package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class SplitPalindromeSubstringTest extends TestCase {

    public void testSplitPalindromeSubstring() {
        String str = "dasdads";
        System.out.println(SplitPalindromeSubstring.get(str));
        System.out.println(SplitPalindromeSubstring.dpGet(str));
    }

}
