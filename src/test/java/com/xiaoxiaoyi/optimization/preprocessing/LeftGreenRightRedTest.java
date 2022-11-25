package com.xiaoxiaoyi.optimization.preprocessing;

import junit.framework.TestCase;

public class LeftGreenRightRedTest extends TestCase {

    public void testLeftGreenRightRed() {
        String GreenRedString = "RGRGR";
        System.out.println(LeftGreenRightRed.minimalStaining(GreenRedString));
    }

}
