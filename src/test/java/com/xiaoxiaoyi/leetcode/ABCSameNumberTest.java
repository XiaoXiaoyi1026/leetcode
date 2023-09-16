package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomGenerate;
import junit.framework.TestCase;

public class ABCSameNumberTest extends TestCase {

    public void test() {
        String stringABC = RandomGenerate.string(new char[]{'A', 'B', 'C'}, 12);
        System.out.println(stringABC);
        System.out.println(ABCSameNumber.lessTimes(stringABC));
    }

}
