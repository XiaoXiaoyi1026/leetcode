package com.xiaoxiaoyi.hash;

import junit.framework.TestCase;

public class RandomPoolTest extends TestCase {

    public void testRandomPool() {
        RandomPool<String> randomPool = new RandomPool<>();
        randomPool.insert("abc");
        randomPool.insert("cab");
        randomPool.insert("ccc");
        randomPool.insert("abc");
        randomPool.insert("caa");

        System.out.println(randomPool.randomGet());
        System.out.println(randomPool.randomGet());
        System.out.println(randomPool.randomGet());

        randomPool.remove("ccc");
    }

}
