package com.xiaoxiaoyi.window;

import junit.framework.TestCase;

public class CordCoverMaxPointTest extends TestCase {

    public void testCordCoverMaxPoint() {
        int[] points = {0, 13, 24, 35, 46, 57, 60, 72, 87};
        int cord = 6;
        System.out.println(CordCoverMaxPoint.cordCoverMaxPoint(points, cord));
    }

}
