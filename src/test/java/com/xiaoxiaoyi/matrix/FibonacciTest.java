package com.xiaoxiaoyi.matrix;

import junit.framework.TestCase;

public class FibonacciTest extends TestCase {

    public void testFibonacci() {
        for (int i = 1; i < 20; i++) {
            System.out.println(Fibonacci.fibonacci(i));
        }
    }

}
