package com.xiaoxiaoyi.kmp;

import junit.framework.TestCase;

public class SpinWordsTest extends TestCase {

    public void testSpinWords() {
        String str1 = "12345";
        String str2 = "23451";
        System.out.println(SpinWords.spinWords(str1, str2));
    }

}
