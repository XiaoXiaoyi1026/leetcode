package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class LFUCacheTest extends TestCase {

    public void testSetAndGet() {
        LFUCache<String, String> lfuCache = new LFUCache<>(3);
        lfuCache.set("B", "刻晴");
        System.out.println(lfuCache.get("B"));
        lfuCache.set("A", "香菱");
        System.out.println(lfuCache.get("A"));
        lfuCache.set("C", "甘雨");
        System.out.println(lfuCache.get("C"));
        lfuCache.set("C", "凝光");
        System.out.println(lfuCache.get("C"));
        lfuCache.set("A", "妮露");
        System.out.println(lfuCache.get("A"));
        System.out.println(lfuCache.get("D"));
        lfuCache.set("D", "优菈");
        System.out.println(lfuCache.get("D"));
        System.out.println(lfuCache.get("A"));
        System.out.println(lfuCache.get("B"));
        System.out.println(lfuCache.get("C"));
    }
}
