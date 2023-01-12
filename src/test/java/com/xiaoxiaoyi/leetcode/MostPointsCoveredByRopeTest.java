package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class MostPointsCoveredByRopeTest extends TestCase {

    public void testGet() {
        int[] points = {1, 3, 4, 7, 13, 16, 17};
        int ropeLength = 4;
        System.out.println(MostPointsCoveredByRope.get(points, ropeLength));
        System.out.println(MostPointsCoveredByRope.get2(points, ropeLength));
    }

}
