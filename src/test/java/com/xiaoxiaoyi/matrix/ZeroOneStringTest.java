package com.xiaoxiaoyi.matrix;

import junit.framework.TestCase;

public class ZeroOneStringTest extends TestCase {

    public void testZeroOneString() {
        for (int i = 1; i <= 20; i++) {
            int ans1 = ZeroOneString.upToStandard1(i);
            int ans2 = ZeroOneString.upToStandard2(i);
            if (ans1 != ans2) {
                System.out.println("ooops!");
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }
    }

}
