package com.xiaoxiaoyi.dynamic;

import junit.framework.TestCase;

public class PossibleBinaryTreeNumberTest extends TestCase {

    public void testPossibleBinaryTreeNumber() {
        for (int i = 0; i < 10; i++) {
            System.out.println(PossibleBinaryTreeNumber.possibleBinaryTreeNumber(i));
        }
    }

    public void testPossibleBinaryTreeNumberDp() {
        for (int i = 0; i < 10; i++) {
            System.out.println(PossibleBinaryTreeNumber.possibleBinaryTreeNumberDp(i));
        }
    }

    public void testLogarithm() {
        for (int i = 0; i < 10; i++) {
            int ans1 = PossibleBinaryTreeNumber.possibleBinaryTreeNumber(i);
            int ans2 = PossibleBinaryTreeNumber.possibleBinaryTreeNumberDp(i);
            if (ans1 != ans2) {
                System.out.println("ooops!");
            }
        }
    }

}
