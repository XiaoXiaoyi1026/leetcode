package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomUtils;
import junit.framework.TestCase;

public class ABCSameNumberTest extends TestCase {

    public void test() {
        String stringABC = RandomUtils.string(new char[]{'A', 'B', 'C'}, 12);
        System.out.println(stringABC);
        System.out.println(ABCSameNumber.lessTimes(stringABC));
    }

}
