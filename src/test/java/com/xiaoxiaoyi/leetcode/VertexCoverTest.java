package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class VertexCoverTest extends TestCase {

    /**
     * 生成由'.'和'X'组成的字符串
     */
    protected String generateStr(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (Math.random() < 0.7) {
                stringBuilder.append('.');
            } else {
                stringBuilder.append('X');
            }
        }
        return String.valueOf(stringBuilder);
    }

    public void testStreetLight() {
        for (int i = 0; i < 10; i++) {
            String str = generateStr((int) (Math.random() * 20));
            System.out.println(str);
            System.out.println("ans = " + VertexCover.streetLight(str));
        }
    }

}
