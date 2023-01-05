package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class SplitNumberTest extends TestCase {

    public void testSplitNumber() {
        for (int num = 1; num <= 10; num++) {
            System.out.println(SplitNumber.schemes(num));
            System.out.println(SplitNumber.schemes2(num));
            System.out.println(SplitNumber.schemes3(num));
        }
    }

}
