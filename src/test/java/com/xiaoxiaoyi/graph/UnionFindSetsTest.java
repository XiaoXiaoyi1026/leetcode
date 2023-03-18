package com.xiaoxiaoyi.graph;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class UnionFindSetsTest extends TestCase {

    public void testUnionFind() {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(5);
        input.add(6);
        input.add(7);
        UnionFindSets<Integer> unionFindSets = new UnionFindSets<>(input);
        unionFindSets.union(4, 6);
        System.out.println(unionFindSets.isSameSet(4, 6));
        System.out.println(unionFindSets.isSameSet(2, 1));
    }

}
