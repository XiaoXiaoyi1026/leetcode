package com.xiaoxiaoyi.tree.orderedlist;

import com.xiaoxiaoyi.tree.TreeTest;

public class SkipListTest extends TreeTest {

    private SkipListMap.SkipList<String, String> skipList;

    private void createSkipList() {
        skipList = new SkipListMap.SkipList<>();
        skipList.put("A", "100");
        skipList.put("B", "200");
        skipList.put("C", "300");
        skipList.put("D", "400");
        skipList.put("E", "200");
        skipList.put("A", "50");
        printAll(skipList);
        System.out.println("==================");
    }

    private void printAll(SkipListMap.SkipList<String, String> obj) {
        for (int i = obj.maxLevel; i >= 0; i--) {
            System.out.print("Level " + i + " : ");
            SkipListMap.SkipListNode<String, String> cur = obj.head;
            while (cur.nextNodes.get(i) != null) {
                SkipListMap.SkipListNode<String, String> next = cur.nextNodes.get(i);
                System.out.print("(" + next.key + " , " + next.value + ") ");
                cur = next;
            }
            System.out.println();
        }
    }

    public void testPut() {
        createSkipList();
        skipList.put("A", "100");
        System.out.println("==================");
        printAll(skipList);
    }

    public void testRemove() {
        createSkipList();
        skipList.remove("D");
        System.out.println("==================");
        printAll(skipList);
    }
    
    public void testAboutKey() {
        createSkipList();
        System.out.println("======================");
        System.out.println(skipList.containsKey("B"));
        System.out.println(skipList.containsKey("Z"));
        System.out.println(skipList.firstKey());
        System.out.println(skipList.lastKey());
        System.out.println(skipList.floorKey("D"));
        System.out.println(skipList.ceilingKey("D"));
        System.out.println("======================");
        skipList.remove("D");
        System.out.println(skipList.floorKey("D"));
        System.out.println(skipList.ceilingKey("D"));
    }
}
