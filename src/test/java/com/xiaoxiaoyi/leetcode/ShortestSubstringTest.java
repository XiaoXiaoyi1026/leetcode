package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class ShortestSubstringTest extends TestCase {

    public void testContains() {
        String str1 = "aaaabbcacbba";
        String str2 = "babca";
        System.out.println(ShortestSubstring.contains(str1, str2));
    }

}
