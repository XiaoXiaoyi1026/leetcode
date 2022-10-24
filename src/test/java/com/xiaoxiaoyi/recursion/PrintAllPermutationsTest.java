package com.xiaoxiaoyi.recursion;

import junit.framework.TestCase;

import java.util.List;

public class PrintAllPermutationsTest extends TestCase {

    public void testPrintAllPermutations() {
        List<String> stringList = PrintAllPermutations.printAllPermutations("bool");
        for (String s : stringList) {
            System.out.println(s);
        }
    }

}
