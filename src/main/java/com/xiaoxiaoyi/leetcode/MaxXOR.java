package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 返回子数组中的最大异或和
 */
public class MaxXOR {

    public static int get1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int start = 0; start < arr.length; start++) {
            for (int end = start; end < arr.length; end++) {
                int tmp = 0;
                for (int i = start; i <= end; i++) {
                    tmp ^= arr[i];
                }
                ans = Math.max(ans, tmp);
            }
        }
        return ans;
    }

    public static int get2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int ans = Math.max(0, arr[0]);
        int[] preXOR = new int[n];
        preXOR[0] = arr[0];
        for (int end = 1; end < n; end++) {
            preXOR[end] = preXOR[end - 1] ^ arr[end];
            ans = Math.max(ans, preXOR[end]);
        }
        for (int start = 1; start < n; start++) {
            for (int end = start; end < n; end++) {
                // start~end范围的异或和 = 0~end的异或和异或上0~start-1的异或和
                ans = Math.max(ans, (preXOR[end] ^ preXOR[start - 1]));
            }
        }
        return ans;
    }

    public static class TrieTree {
        public Node root;

        public static class Node {
            Node[] next;

            Node() {
                // 分别代表0和1两个分支
                next = new Node[2];
            }
        }

        TrieTree() {
            root = new Node();
        }

        public void add(int num) {
            Node cur = root;
            for (int i = 31; i >= 0; i--) {
                int path = (num >> i) & 1;
                cur.next[path] = cur.next[path] == null ?
                        new Node() : cur.next[path];
                cur = cur.next[path];
            }
        }

        public int bestXORMatch(int num) {
            Node cur = root;
            int res = 0;
            for (int i = 31; i >= 0; i--) {
                int path = ((num >> i) & 1) == 0 ? 1 : 0;
                if (cur.next[path] != null) {
                    res |= (1 << i);
                    cur = cur.next[path];
                } else {
                    cur = cur.next[path == 0 ? 1 : 0];
                }
            }
            return res;
        }
    }

    public static int trieTreeGet(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ans = 0;
        TrieTree trieTree = new TrieTree();
        // 在没有任何数异或的情况下, 结果为0
        int preXORSum = 0;
        trieTree.add(preXORSum);
        for (int num : arr) {
            preXORSum ^= num;
            ans = Math.max(ans, trieTree.bestXORMatch(preXORSum));
            trieTree.add(preXORSum);
        }
        return ans;
    }

}
