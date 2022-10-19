package com.xiaoxiaoyi.greedy;

import junit.framework.TestCase;

public class GetTheMedianTest extends TestCase {

    public void testGetTheMedian() {
        GetTheMedian getTheMedian = new GetTheMedian();
        getTheMedian.addNumber(5).addNumber(3).addNumber(7).addNumber(4).addNumber(1);
        System.out.println(getTheMedian.getTheMedian());
    }

}
