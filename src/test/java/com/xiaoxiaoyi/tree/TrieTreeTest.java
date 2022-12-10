package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

public class TrieTreeTest extends TestCase {

    public void testTrieTree() {
        TrieTree trieTree = new TrieTree();
        String[] strings = new String[]{"abc", "bce", "ace", "abe", "bcd", "abc"};
        for (String string : strings) {
            trieTree.insert(string);
        }
        System.out.println("input: ");
        trieTree.print();
        System.out.println("======================");
        System.out.println("search abc: " + trieTree.search("abc"));
        System.out.println("======================");
        System.out.println("search abd: " + trieTree.search("abd"));
        System.out.println("======================");
        System.out.println("prefix count a: " + trieTree.prefixCount("a"));
        System.out.println("======================");
        System.out.println("remove abc: ");
        trieTree.remove("abc");
        trieTree.print();
        System.out.println("======================");
        System.out.println("remove das: ");
        trieTree.remove("das");
        trieTree.print();
    }

}
