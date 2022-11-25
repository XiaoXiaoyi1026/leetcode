package com.xiaoxiaoyi.optimization.greedy;

import junit.framework.TestCase;

public class PackApplesTest extends TestCase {

    public void testPackApples() {
        for (int i = 6; i <= 1000; i += 2) {
            System.out.println(i + "->" + PackApples.packApples(i) +
                    "||" + PackApples.bestSolution(i));
        }
    }

}
