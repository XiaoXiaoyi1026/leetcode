package com.xiaoxiaoyi.manacher;

import junit.framework.TestCase;

public class ManacherTest extends TestCase {

    public void testManacher() {
        System.out.println(Manacher.manacherString("12323231231"));
        System.out.println(Manacher.maxPalindromicRadiusLength("12323231231"));
    }

}
