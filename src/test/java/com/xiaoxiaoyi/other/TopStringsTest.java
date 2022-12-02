package com.xiaoxiaoyi.other;

import junit.framework.TestCase;

public class TopStringsTest extends TestCase {

    public void testTopStrings() {
        TopStrings topStrings = new TopStrings(3);
        topStrings.add("a");
        topStrings.add("b");
        topStrings.add("c");
        System.out.println(topStrings.getTopStrings());
        topStrings.add("d");
        topStrings.add("d");
        System.out.println(topStrings.getTopStrings());
        topStrings.add("a");
        System.out.println(topStrings.getTopStrings());
    }

}
