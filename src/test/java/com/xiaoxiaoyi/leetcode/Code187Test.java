package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.List;

public class Code187Test extends TestCase {

    public void testCode187() {
        List<String> stringList = Code187.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        System.out.println(stringList);
    }

}
