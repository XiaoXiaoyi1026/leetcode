package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class MakePalindromeTest extends TestCase {

    public void testMakePalindrome() {
        String str = "a12bc321";
        System.out.println(MakePalindrome.get(str));
        System.out.println(MakePalindrome.get2(str));
        System.out.println(MakePalindrome.getPalindrome(str));
    }

}
