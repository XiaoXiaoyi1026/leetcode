package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

public class SizeBalancedTree2Test extends TestCase {

    private SizeBalancedTree2.SizeBalancedTreeMap<String, Integer> sizeBalancedTree2;

    private void createTree() {
        sizeBalancedTree2 =
                new SizeBalancedTree2.SizeBalancedTreeMap<>(10000);

        sizeBalancedTree2.put("pos", 512);
        sizeBalancedTree2.put("zyp", 7123);
        sizeBalancedTree2.put("unz", 542);
        sizeBalancedTree2.put("abc", 5113);
        sizeBalancedTree2.put("yzk", 665);
        sizeBalancedTree2.put("fgi", 38776);
        sizeBalancedTree2.put("bke", 2500540);
        sizeBalancedTree2.put("lmn", 44334);
        sizeBalancedTree2.put("abc", 11);
        sizeBalancedTree2.put("abc", 111);
    }

    public void testPrintAllNodes() {
        createTree();
        for (int i = 0; i < sizeBalancedTree2.size(); i++) {
            System.out.println(sizeBalancedTree2.getIndexKey(i) + " , " + sizeBalancedTree2.getIndexValue(i));
        }
    }

    public void testAll() {
        createTree();
        System.out.println(sizeBalancedTree2.containsKey("abc"));
        System.out.println(sizeBalancedTree2.get("abc"));
        System.out.println(sizeBalancedTree2.firstKey());
        System.out.println(sizeBalancedTree2.lastKey());
        System.out.println(sizeBalancedTree2.floorKey("bke"));
        System.out.println(sizeBalancedTree2.ceilingKey("bke"));
        System.out.println(sizeBalancedTree2.floorKey("ooo"));
        System.out.println(sizeBalancedTree2.ceilingKey("ooo"));
        System.out.println(sizeBalancedTree2.floorKey("aaa"));
        System.out.println(sizeBalancedTree2.ceilingKey("aaa"));
        System.out.println(sizeBalancedTree2.floorKey("zzz"));
        System.out.println(sizeBalancedTree2.ceilingKey("zzz"));
    }
}
