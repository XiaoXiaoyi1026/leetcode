package com.xiaoxiaoyi.bit;

import junit.framework.TestCase;

public class PowerTest extends TestCase {

    public void testPower() {
        int num = 512;
        System.out.println(Power.is2Power(num));
        System.out.println(Power.is4Power(num));
        System.out.println("===================");
        num = 1024;
        System.out.println(Power.is2Power(num));
        System.out.println(Power.is4Power(num));
    }

}
