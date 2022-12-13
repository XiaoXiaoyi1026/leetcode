package com.xiaoxiaoyi.other;

import junit.framework.TestCase;

public class PrintIntegerInChineseTest extends TestCase {

    public void testPrint() {
        // 1 ~ 9
        int num1 = (int) (Math.random() * 9) + 1;
        System.out.println(num1);
        System.out.println(PrintIntegerInChinese.print(num1));
        System.out.println("==================");
        // 10 ~ 99
        int num2 = (int) (Math.random() * 90) + 10;
        System.out.println(num2);
        System.out.println(PrintIntegerInChinese.print(num2));
        System.out.println("==================");
        // 100 ~ 999
        int num3 = (int) (Math.random() * 900) + 100;
        System.out.println(num3);
        System.out.println(PrintIntegerInChinese.print(num3));
        System.out.println("==================");
        // 1000 ~ 9999
        int num4 = (int) (Math.random() * 9000) + 1000;
        System.out.println(num4);
        System.out.println(PrintIntegerInChinese.print(num4));
        System.out.println("==================");
        // 10000 ~ 99999999
        int num5 = (int) (Math.random() * 99990000) + 10000;
        System.out.println(num5);
        System.out.println(PrintIntegerInChinese.print(num5));
        System.out.println("==================");
        // 100000000 ~ Integer.MAX_VALUE
        int num6 = (int) (Math.random() * Integer.MAX_VALUE - 99999999) + 100000000;
        System.out.println(num6);
        System.out.println(PrintIntegerInChinese.print(num6));
        System.out.println("==================");
        // Integer.MIN_VALUE
        System.out.println(Integer.MIN_VALUE);
        System.out.println(PrintIntegerInChinese.print(Integer.MIN_VALUE));
        System.out.println("==================");
    }

}
