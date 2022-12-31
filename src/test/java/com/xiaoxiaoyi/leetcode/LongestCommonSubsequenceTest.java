package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class LongestCommonSubsequenceTest extends TestCase {

    public void testLongestCommonSubsequence() {
        String str1 = "a1b2c3df4g5";
        String str2 = "123a45g";
        System.out.println(LongestCommonSubsequence.get(str1, str2));
        System.out.println(LongestCommonSubsequence.get2(str1, str2));
    }

}
