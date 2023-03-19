package com.xiaoxiaoyi.tree;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author xiaoxiaoyi
 */
public class PrintSystemFileDirectory {
    public static class Node implements Comparable<Node> {
        /**
         * 文件/目录名称
         */
        public String name;
        /**
         * 子目录/文件
         */
        public List<Node> nextNodes;

        public Node(String name) {
            this.name = name;
            nextNodes = new ArrayList<>();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Node node)) {
                return false;
            }

            return Objects.equals(name, node.name);
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }

        @Override
        public int compareTo(Node o) {
            return this.name.compareTo(o.name);
        }
    }

    public static class PathTrieTree {
        public Node root;

        public PathTrieTree() {
            root = new Node("");
        }

        public void add(@NotNull String path) {
            // split会因为转义将\\\\变为\\
            // 然后因为正则表达式将\\变为\
            String[] names = path.split("\\\\");
            Node cur = root;
            for (String name : names) {
                Node newNode = new Node(name);
                List<Node> nextNodes = cur.nextNodes;
                if (!nextNodes.contains(newNode)) {
                    // 如果当前节点的nextNodes中不包含新节点, 那么新增一条分支
                    nextNodes.add(newNode);
                    cur = newNode;
                } else {
                    // 如果当前节点后继有新节点, 那么cur就指向已存在的后继节点
                    cur = nextNodes.get(nextNodes.indexOf(newNode));
                }
            }
        }
    }

    public static void printPaths(String[] paths) {
        if (paths == null || paths.length == 0) {
            return;
        }
        // 生成路径树
        Node root = generatePathTrieTree(paths);
        // 开始打印
        printProcess(root, 0);
    }

    private static void printProcess(Node curNode, int layer) {
        // 不打印根节点
        if (layer != 0) {
            // 打印当前层
            System.out.println(getSpaces(layer) + curNode.name);
        }
        // 深度优先遍历, 打印完当前层直接去下层
        for (Node nextNode : curNode.nextNodes) {
            printProcess(nextNode, layer + 1);
        }
    }

    @NotNull
    private static String getSpaces(int layer) {
        // 每一层的空格数 = (layer - 1) * 2
        return "  ".repeat(Math.max(0, layer - 1));
    }

    public static Node generatePathTrieTree(@NotNull String[] paths) {
        PathTrieTree pathTrieTree = new PathTrieTree();
        for (String path : paths) {
            pathTrieTree.add(path);
        }
        return pathTrieTree.root;
    }

}
