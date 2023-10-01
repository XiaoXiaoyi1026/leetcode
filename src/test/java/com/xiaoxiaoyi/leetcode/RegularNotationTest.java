package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class RegularNotationTest extends TestCase {

    public void testProcess() {
        String str = "aaaa";
        String exp = "a*b*c*";
        System.out.println(RegularExpression.exp(str, exp));
        System.out.println(RegularExpression.dp(str, exp));
    }

}
