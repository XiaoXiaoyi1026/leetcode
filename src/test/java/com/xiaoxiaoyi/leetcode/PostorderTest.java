package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class PostorderTest extends TestCase {

    public void testPostorder() {
        char[] preorder = {'1', '2', '4', '5', '3', '6', '7'};
        char[] inorder = {'4', '2', '5', '1', '6', '3', '7'};
        System.out.println(Postorder.generate(preorder, inorder));
    }

}
