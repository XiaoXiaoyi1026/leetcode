package com.xiaoxiaoyi.tree;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author xiaoxiaoyi
 * 前缀树
 */
public class TrieTree {

    /**
     * 前缀树节点
     */
    public static class TrieNode {
        // 节点被经过的次数
        public int pass;
        // 是多少个字符串的结尾节点
        public int end;
        // 当前节点的后继结点集
        public TrieNode[] nextTrieNodes;
        // key: 边上的字符
        // value: 下一个节点
        // public HashMap<Character, TrieNode> nextTrieNodes;
        // 有序
        // public TreeSet<Character, TrieNode> nextTrieNodes;

        public TrieNode() {
            // 初始化
            pass = 0;
            end = 0;
            // nextTrieNodes[0] == null 代表没有走向'a'的路
            // nextTrieNodes[0] != null 代表有走向'a'的路
            // ...
            // nextTrieNodes[25] != null 代表有走向'z'的路
            nextTrieNodes = new TrieNode[26];
        }
    }

    /**
     * 初始化根节点
     */
    private final TrieNode root;

    /**
     * 构造前缀树
     */
    public TrieTree() {
        // 创建根节点
        root = new TrieNode();
    }

    /**
     * 向前缀树添加字符串
     *
     * @param word 添加的字符串
     */
    public void insert(String word) {
        if (word == null) {
            return;
        }
        // 字符串转化为字符数组
        char[] chars = word.toCharArray();
        // 当前节点指针
        TrieNode curNode = root;
        // 经过次数+1
        curNode.pass++;
        // 记录路径下标
        int index = 0;
        // 遍历字符数组
        for (char c : chars) {
            // 求出当前字符的路径下标
            index = c - 'a';
            // 判断是否存在这条路径
            if (curNode.nextTrieNodes[index] == null) {
                // 如果这条路不存在则创建
                curNode.nextTrieNodes[index] = new TrieNode();
            }
            // 当前节点指针指向这条路
            curNode = curNode.nextTrieNodes[index];
            // 经过这条路，pass+1
            curNode.pass++;
        }
        curNode.end++;
    }

    /**
     * 从前缀树上删除这个字符串
     *
     * @param word 要删除的字符串
     */
    public void remove(String word) {
        // word加入过前缀树才进行删除
        if (search(word) != 0) {
            char[] chars = word.toCharArray();
            // 从root出发
            TrieNode curNode = root;
            curNode.pass--;
            int index;
            for (char c : chars) {
                index = c - 'a';
                if (--curNode.nextTrieNodes[index].pass == 0) {
                    // 如果pass减到了0，说明后继所有节点都要删除
                    curNode.nextTrieNodes[index] = null;
                    return;
                }
                // 指向下一个
                curNode = curNode.nextTrieNodes[index];
            }
            curNode.end--;
        }
    }

    /**
     * 在前缀树上查找字符串之前加入过几次
     *
     * @param word 查找的目标字符串
     * @return 这个字符串在前缀树中出现过几次
     */
    public int search(String word) {
        if (word == null) {
            return 0;
        }
        char[] chars = word.toCharArray();
        // 当前节点从根节点开始
        TrieNode curNode = root;
        // 记录当前字符的路径下标
        int index;
        for (char c : chars) {
            index = c - 'a';
            if (curNode.nextTrieNodes[index] == null) {
                // 如果当前节点的后路径中没有这个字符，则代表其出现的次数为0次
                return 0;
            }
            // 当前节点指向下一个
            curNode = curNode.nextTrieNodes[index];
        }
        return curNode.end;
    }

    /**
     * 在前缀树上查找字符串作为前缀的次数
     *
     * @param pre 查找的目标前缀
     * @return 这个字符串作为前缀的出现次数
     */
    public int prefixCount(String pre) {
        if (pre == null) {
            return 0;
        }
        char[] chars = pre.toCharArray();
        // 当前节点从根节点开始
        TrieNode curNode = root;
        // 记录当前字符的路径下标
        int index;
        for (char c : chars) {
            index = c - 'a';
            if (curNode.nextTrieNodes[index] == null) {
                // 如果当前节点的后路径中没有这个字符，则代表其出现的次数为0次
                return 0;
            }
            // 当前节点指向下一个
            curNode = curNode.nextTrieNodes[index];
        }
        return curNode.pass;
    }

    /**
     * 打印当前的前缀树
     */
    public void print() {
        // 使用深度优先遍历
        Stack<TrieNode> stack = new Stack<>();
        // 记录已访问过的节点
        List<TrieNode> goesNodes = new ArrayList<>();
        System.out.println("pass[" + root.pass + "] end[" + root.end + "]");
        stack.push(root);
        goesNodes.add(root);
        TrieNode curNode;
        while (!stack.isEmpty()) {
            curNode = stack.pop();
            if (!goesNodes.contains(curNode)) {
                System.out.println("pass[" + curNode.pass + "] end[" + curNode.end + "]");
                goesNodes.add(curNode);
            }
            for (TrieNode node : curNode.nextTrieNodes) {
                if (!goesNodes.contains(node) && node != null) {
                    stack.push(curNode);
                    stack.push(node);
                    break;
                }
            }
        }
    }

}
