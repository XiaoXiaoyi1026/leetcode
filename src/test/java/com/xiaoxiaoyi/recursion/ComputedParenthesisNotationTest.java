package com.xiaoxiaoyi.recursion;

import junit.framework.TestCase;

public class ComputedParenthesisNotationTest extends TestCase {

    public void testComputedParenthesisExpression() {
        String parenthesisExpression = "3*(4+2*(5-7)-34)+4";
        System.out.println(ComputedParenthesisExpression.compute(parenthesisExpression));
        System.out.println(ComputedParenthesisExpression.getAnswer(parenthesisExpression));
    }

}
