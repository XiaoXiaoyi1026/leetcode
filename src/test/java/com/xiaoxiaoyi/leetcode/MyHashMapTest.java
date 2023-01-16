package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class MyHashMapTest extends TestCase {

    public void testAll() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();
        myHashMap.put(1, 3);
        myHashMap.put(2, 9);
        System.out.println(myHashMap.get(1));
        System.out.println("================");
        myHashMap.setAll(7);
        System.out.println(myHashMap.get(1));
        myHashMap.put(17, 4);
        System.out.println(myHashMap.get(17));
        System.out.println(myHashMap.get(2));
    }

}
