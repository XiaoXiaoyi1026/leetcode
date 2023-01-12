package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.RandomGenerate;
import junit.framework.TestCase;

public class LeftGRightBOrRightGLeftBTest extends TestCase {

    public void testMinimumNumberOfExchanges() {
        String GBString = RandomGenerate.containsCharactersString(
                new char[]{'G', 'B'}, (int) (Math.random() * 20)
        );
        System.out.println(GBString);
        System.out.println(LeftGRightBOrRightGLeftB.minimumNumberOfExchanges(GBString));
    }

}
