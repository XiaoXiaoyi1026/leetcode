package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class LongestCommonSubstringTest extends TestCase {

    public void testDp1() {
        String str1 = "aabcaac";
        String str2 = "aaabccab";
        System.out.println(LongestCommonSubstring.dp1(str1, str2));
        System.out.println(LongestCommonSubstring.dp2(str1, str2));
    }

}
