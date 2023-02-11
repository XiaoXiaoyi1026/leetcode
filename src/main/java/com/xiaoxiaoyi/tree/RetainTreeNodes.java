package com.xiaoxiaoyi.tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 保留树节点
 * @date 2/11/2023 3:05 PM
 */
public class RetainTreeNodes {

    public static class Node {
        private final int value;
        private boolean retain;
        private List<Node> nextNodes;

        public Node(int value, boolean retain, List<Node> nextNodes) {
            this.value = value;
            this.retain = retain;
            this.nextNodes = nextNodes;
        }

        public int getValue() {
            return value;
        }

        public List<Node> getNextNodes() {
            return nextNodes;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    "retain=" + retain +
                    ", nextNodes=" + nextNodes +
                    '}';
        }
    }

    /**
     * 调整以head为头的子树
     *
     * @param head 头结点
     * @return 调整后的头结点
     */
    public static @Nullable Node retain(@NotNull Node head) {
        // base case: 如果头结点为叶节点
        if (head.nextNodes.isEmpty()) {
            // 根据节点是否保留来判断
            return head.retain ? head : null;
        }
        // head新的后继结点集合
        List<Node> retainNextNodes = new ArrayList<>();
        // 不是叶节点
        for (Node nextNode : head.nextNodes) {
            if (retain(nextNode) != null) {
                // 如果后续节点需要保留
                retainNextNodes.add(nextNode);
            }
        }
        if (!retainNextNodes.isEmpty() || head.retain) {
            // 如果保留的后继结点不为空或者当前节点需要保留则返回当前节点
            head.retain = true;
            head.nextNodes = retainNextNodes;
            return head;
        }
        // 判定当前节点不需要保留
        return null;
    }

}
