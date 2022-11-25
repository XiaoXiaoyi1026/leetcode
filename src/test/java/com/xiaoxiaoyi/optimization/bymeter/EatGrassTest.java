package com.xiaoxiaoyi.optimization.bymeter;

import junit.framework.TestCase;

public class EatGrassTest extends TestCase {

    public void testEatGrass() {
        for (int i = 0; i <= 50; i++) {
            String ans1 = EatGrass.eatGrass(i);
            String ans2 = EatGrass.bestSolution(i);
            if (!ans1.equals(ans2)) {
                System.out.println("ooops!");
            }
        }
    }

}
