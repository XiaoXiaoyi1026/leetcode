package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomGenerate;
import junit.framework.TestCase;

import java.util.Arrays;

public class MostPointsCoveredByRopeTest extends TestCase {

    public void testGet() {
        for (int i = 0; i < 1000; i++) {
            int[] points = RandomGenerate.array((int) (Math.random() * 20), (int) (Math.random() * 100));
            int ropeLength = (int) (Math.random() * 20);
            int ans1 = MostPointsCoveredByRope.get(points, ropeLength);
            int ans2 = MostPointsCoveredByRope.get2(points, ropeLength);
            if (ans1 != ans2) {
                System.out.println("ooops!");
                System.out.println(Arrays.toString(points));
                System.out.println(ropeLength);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
    }

}
