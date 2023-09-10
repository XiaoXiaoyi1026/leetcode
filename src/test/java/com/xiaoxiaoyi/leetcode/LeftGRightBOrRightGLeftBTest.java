package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomGenerate;
import junit.framework.TestCase;

public class LeftGRightBOrRightGLeftBTest extends TestCase {

    public void testMinimumNumberOfExchanges() {
        String GBString = RandomGenerate.string(
                new char[]{'G', 'B'}, (int) (Math.random() * 20)
        );
        System.out.println(GBString);
        System.out.println(LeftGRightBOrRightGLeftB.minimumNumberOfExchanges(GBString));
    }

    public void testAll() {
        for (int i = 0; i < 1000; i++) {
            String GBString = RandomGenerate.string(
                    new char[]{'G', 'B'}, (int) (Math.random() * 20)
            );
            int ans1 = LeftGRightBOrRightGLeftB.minimumNumberOfExchanges(GBString);
            int ans2 = LeftGRightBOrRightGLeftB.getMinimum2(GBString);
            if (ans1 != ans2) {
                System.out.println("ooops!");
                System.out.println(GBString);
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }
    }

}
