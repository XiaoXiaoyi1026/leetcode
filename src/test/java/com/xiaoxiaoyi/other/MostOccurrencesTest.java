package com.xiaoxiaoyi.other;

import junit.framework.TestCase;

import java.util.Arrays;

public class MostOccurrencesTest extends TestCase {

    public void testMostOccurrences() {
        String[] words = {
                "a",
                "b",
                "c",
                "d",
                "a",
                "a",
                "b",
                "c",
                "b",
                "a"
        };
        String[] ans = MostOccurrences.mostOccurrences(words, 3);
        System.out.println(Arrays.toString(ans));
    }

}
