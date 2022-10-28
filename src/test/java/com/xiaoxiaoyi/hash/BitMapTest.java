package com.xiaoxiaoyi.hash;

import junit.framework.TestCase;

public class BitMapTest extends TestCase {

    public void testBitMap() {
        BitMap bitMap = new BitMap();
        BitMap bitMap1 = new BitMap(2);
        bitMap.setBit(3);
        System.out.println(bitMap.getBit(3));
        bitMap1.setBit(5);
        System.out.println(bitMap1.getBit(5));
    }

}
