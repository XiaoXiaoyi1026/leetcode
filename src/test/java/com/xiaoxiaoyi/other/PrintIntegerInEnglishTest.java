package com.xiaoxiaoyi.other;

import junit.framework.TestCase;

public class PrintIntegerInEnglishTest extends TestCase {

    public void testPrint() {
        // 1 ~ 19
        int num1 = (int) (Math.random() * 19) + 1;
        System.out.println(num1);
        System.out.println(PrintIntegerInEnglish.print(num1));
        System.out.println("==============");
        // 20 ~ 99
        int num2 = (int) (Math.random() * 80) + 20;
        System.out.println(num2);
        System.out.println(PrintIntegerInEnglish.print(num2));
        System.out.println("==============");
        // 100 ~ 999
        int num3 = (int) (Math.random() * 900) + 100;
        System.out.println(num3);
        System.out.println(PrintIntegerInEnglish.print(num3));
        System.out.println("==============");
        // 1000 ~ Integer.MAX_VALUE
        int num4 = (int) (Math.random() * (Integer.MAX_VALUE - 999)) + 1000;
        System.out.println(num4);
        System.out.println(PrintIntegerInEnglish.print(num4));
        System.out.println("==============");
        System.out.println(Integer.MIN_VALUE);
        System.out.println(PrintIntegerInEnglish.print(Integer.MIN_VALUE));
    }
}