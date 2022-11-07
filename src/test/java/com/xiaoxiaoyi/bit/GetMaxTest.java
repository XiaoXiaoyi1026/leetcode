package com.xiaoxiaoyi.bit;

import junit.framework.TestCase;

public class GetMaxTest extends TestCase {

    public void testGetMax() {
        int a = 123, b = -2;
        System.out.println(GetMax.bitCompare(a, b));
        System.out.println(GetMax.bitCompare2(a, b));
        a = 2147483647;
        b = -2147480000;
        // 溢出导致错误
        System.out.println(GetMax.bitCompare(a, b));
        // 不会出错
        System.out.println(GetMax.bitCompare2(a, b));
    }

}
