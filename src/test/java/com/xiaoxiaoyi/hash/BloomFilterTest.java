package com.xiaoxiaoyi.hash;

import junit.framework.TestCase;

public class BloomFilterTest extends TestCase {

    public void testBloomFilter()  {
        long n = (long) Math.pow(10, 8);
        double p = 0.0001;
        BloomFilter bloomFilter = new BloomFilter(n, p);
        System.out.println(bloomFilter.n);
        System.out.println(bloomFilter.p);
        System.out.println(bloomFilter.m);
        System.out.println(bloomFilter.k);
        System.out.println(bloomFilter.p1);
    }

}
