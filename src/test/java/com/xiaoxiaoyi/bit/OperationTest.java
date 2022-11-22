package com.xiaoxiaoyi.bit;

import junit.framework.TestCase;

public class OperationTest extends TestCase {

    public void testAdd() {
        int a = 11, b = 9;
        System.out.println(Operation.add(a, b));
    }

    public void testSub() {
        int a = 11, b = -2;
        System.out.println(Operation.sub(a, b));
    }

    public void testMultiply() {
        int a = -20, b = -2;
        System.out.println(Operation.multiply(a, b));
    }

    public void testDiv() {
        int a = 7, b = -3;
        System.out.println(Operation.div(a, b));
    }

}
