package com.xiaoxiaoyi.recursion;

import junit.framework.TestCase;

public class ConvertToLetterStringTest extends TestCase {

    public void testConvertToLetterString() {
        int testTimes = 100;
        for (int i = 0; i < testTimes; i++) {
            int num = 20000 + (int) (Math.random() * 30000);
            int ans1 = ConvertToLetterString.convertToLetterString(num);
            int ans2 = ConvertToLetterString.convertToLetterString2(num);
            int ans3 = ConvertToLetterString.dpProcess(num);
            int ans4 = ConvertToLetterString.dpProcess2(num);
            if (ans1 != ans2 || ans2 != ans3 || ans3 != ans4) {
                System.out.println("ooops!");
                System.out.println(num);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
                break;
            }
        }
    }

}
