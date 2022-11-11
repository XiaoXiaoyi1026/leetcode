package com.xiaoxiaoyi.dynamic;

import junit.framework.TestCase;

public class BobLiveTest extends TestCase {

    public void testBobLive() {
        int m = 10, n = 10, x = 3, y = 4, step = 10;
        System.out.println(BobLive.bob1(m, n, x, y, step));
        System.out.println(BobLive.bob2(m, n, x, y, step));
    }

}
