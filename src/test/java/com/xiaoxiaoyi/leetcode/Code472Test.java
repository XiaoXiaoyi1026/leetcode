package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class Code472Test extends TestCase {

    public void testCode472() {
        String[] input = new String[]{
                "cat",
                "cats",
                "catsdogcats",
                "dog",
                "dogcatsdog",
                "hippopotamuses",
                "rat",
                "ratcatdogcat"
        };
        System.out.println(Code472.findAllConcatenatedWordsInaDict(input));
    }

}
