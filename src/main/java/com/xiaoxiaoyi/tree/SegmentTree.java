package com.xiaoxiaoyi.tree;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * 线段树
 */
@Getter
@Setter
public class SegmentTree extends ElementBinaryTree<Integer> {
    /**
     * 数组, 存放原始数据
     */
    private final int[] arr;

    @Getter
    @Setter
    public static class Node extends ElementBinaryTree.Node<Integer> {
        /**
         * 节点范围: [start, end)
         */
        final int start;
        final int end;
        /**
         * 懒累加
         */
        int lazyAdd;
        /**
         * 懒更新
         */
        int lazyUpdate;
        /**
         * 懒更新是否有效
         */
        boolean lazyUpdateFlag;

        /**
         * [start, end) 范围内的最大值(懒更新)
         */
        int lazyMax;

        public Node(int start, int end) {
            this(start, end, 0);
        }

        public Node(int start, int end, int sum) {
            super(sum);
            this.start = start;
            this.end = end;
            this.lazyAdd = 0;
            this.lazyUpdate = 0;
            this.lazyUpdateFlag = false;
            this.lazyMax = Integer.MIN_VALUE;
        }

        @Override
        public Node getLeft() {
            return (Node) super.left;
        }

        @Override
        public Node getRight() {
            return (Node) super.right;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) return false;
            if (getClass() != other.getClass()) return false;
            return this.start == ((Node) other).start && this.end == ((Node) other).end && Objects.equals(this.element, ((Node) other).element);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.start, this.end, this.element);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", max=" + lazyMax +
                    '}';
        }
    }

    public SegmentTree(int length) {
        this.arr = new int[length];
        root = build(0, length);
    }

    public SegmentTree(@NotNull int[] arr) {
        this.arr = new int[arr.length];
        System.arraycopy(arr, 0, this.arr, 0, arr.length);
        root = build(0, arr.length);
    }

    @Override
    public Node getRoot() {
        return (Node) root;
    }

    @Override
    public String toString() {
        this.print();
        return "";
    }

    public void add(int to, int value) {
        add(0, to, value);
    }

    /**
     * 范围累加
     *
     * @param from  范围左边界(包含)
     * @param to    范围右边界(不包含)
     * @param value 增加值
     */
    public void add(int from, int to, int value) {
        // 从根节点开始累加
        add(getRoot(), from, to, value);
    }

    /**
     * 范围累加
     *
     * @param curNode 处理的节点
     * @param from    范围左边界(包含)
     * @param to      范围右边界(不包含)
     * @param value   增加值
     */
    public void add(Node curNode, int from, int to, int value) {
        if (from >= to || curNode == null) {
            // 如果区间不合法或者处理节点为空, 直接返回
            return;
        }
        if (curNode.start >= from && curNode.end <= to) {
            // 如果当前节点被包含在累加的区间内, 则当前节点记录懒累加信息
            curNode.lazyAdd += value;
            // 更新自己的信息
            curNode.element += value * (curNode.end - curNode.start);
            curNode.lazyMax += value;
            return;
        }
        // 如果当前节点没被累加区间完全包含, 那么需要将当前节点的累加和更新任务向子节点进行传递
        pushDown(curNode);
        if (curNode.getLeft() != null && from < curNode.getLeft().end) {
            // 左子节点范围是: [curNode.start, curNode.getLeft().end), from < curNode.getLeft().end 说明操作范围与左子节点范围有交集, 需要传递任务给左子结点
            add(curNode.getLeft(), from, to, value);
        }
        if (curNode.getRight() != null && to > curNode.getRight().start) {
            // 右子节点范围是: [curNode.getRight().start, curNode.end), to > curNode.getRight().start 说明操作范围与右子节点范围有交集, 需要传递任务给右子结点
            add(curNode.getRight(), from, to, value);
        }
        // 子节点更新完后, 自己更新
        pushUp(curNode);
    }

    public void update(int to, int value) {
        update(to - 1, to, value);
    }

    /**
     * 范围更新
     *
     * @param from  范围左边界(包含)
     * @param to    范围右边界(不包含)
     * @param value 更新值
     */
    public void update(int from, int to, int value) {
        // 从根节点开始更新
        update(getRoot(), from, to, value);
    }

    /**
     * 范围更新
     *
     * @param curNode 处理的节点
     * @param from    范围左边界(包含)
     * @param to      范围右边界(不包含)
     * @param value   更新值
     */
    public void update(Node curNode, int from, int to, int value) {
        if (from >= to || curNode == null) {
            return;
        }
        if (curNode.start >= from && curNode.end <= to) {
            // 如果当前节点被更新区间完全包含, 则记录懒更新信息
            curNode.lazyUpdate = value;
            curNode.lazyUpdateFlag = true;
            // 更新当前节点的信息
            curNode.element = value * (curNode.end - curNode.start);
            curNode.lazyAdd = 0;
            curNode.lazyMax = value;
            return;
        }
        // 如果当前节点没被累加区间完全包含, 那么需要将当前节点的累加和更新任务向子节点进行传递
        pushDown(curNode);
        if (curNode.getLeft() != null && from < curNode.getLeft().end) {
            update(curNode.getLeft(), from, to, value);
        }
        if (curNode.getRight() != null && to > curNode.getRight().start) {
            update(curNode.getRight(), from, to, value);
        }
        pushUp(curNode);
    }

    public int get(int to) {
        return get(0, to);
    }

    /**
     * 查询范围信息
     *
     * @param from 范围左边界(包含)
     * @param to   范围右边界(不包含)
     * @return 范围信息
     */
    public int get(int from, int to) {
        return get(getRoot(), from, to);
    }

    /**
     * 查询范围信息
     *
     * @param curNode 处理的节点
     * @param from    范围左边界(包含)
     * @param to      范围右边界(不包含)
     * @return 范国数据
     */
    public int get(Node curNode, int from, int to) {
        if (from >= to || curNode == null) {
            return 0;
        }
        if (curNode.start >= from && curNode.end <= to) {
            // 如果查询范围完全包含当前节点范围, 那么直接返回当前节点的信息
            return curNode.element;
        }
        // 如果查询范围并未完全包含当前节点范围, 那么当前节点先下发任务给子节点, 让子节点去更新
        pushDown(curNode);
        int res = 0;
        if (curNode.getLeft() != null && from < curNode.getLeft().end) {
            res += get(curNode.getLeft(), from, to);
        }
        if (curNode.getRight() != null && to > curNode.getRight().start) {
            res += get(curNode.getRight(), from, to);
        }
        return res;
    }

    public int max(int to) {
        return max(0, to);
    }

    public int max(int from, int to) {
        return max(getRoot(), from, to);
    }

    public int max(Node curNode, int from, int to) {
        if (from >= to || curNode == null) {
            return Integer.MIN_VALUE;
        }
        if (curNode.start >= from && curNode.end <= to) {
            return curNode.lazyMax;
        }
        pushDown(curNode);
        int res = 0;
        if (curNode.getLeft() != null && from < curNode.getLeft().end) {
            res = Math.max(res, max(curNode.getLeft(), from, to));
        }
        if (curNode.getRight() != null && to > curNode.getRight().start) {
            res = Math.max(res, max(curNode.getRight(), from, to));
        }
        pushUp(curNode);
        return res;
    }

    /**
     * 节点的懒更新和懒累加信息传递给子节点
     *
     * @param curNode 处理的节点
     */
    private void pushDown(@NotNull Node curNode) {
        if (curNode.lazyUpdateFlag) {
            // 如果当前节点存在懒更新信息, 则先执行懒更新下发任务, 再执行懒累加下发任务
            if (curNode.getLeft() != null) {
                // 如果存在左节点, 则发给左节点
                curNode.getLeft().lazyUpdate = curNode.lazyUpdate;
                curNode.getLeft().element = curNode.lazyUpdate * (curNode.getLeft().end - curNode.getLeft().start);
                curNode.getLeft().lazyUpdateFlag = true;
                curNode.getLeft().lazyAdd = 0;
                curNode.getLeft().lazyMax = curNode.lazyUpdate;
            }
            if (curNode.getRight() != null) {
                // 如果存在右节点, 则发给右节点
                curNode.getRight().lazyUpdate = curNode.lazyUpdate;
                curNode.getRight().element = curNode.lazyUpdate * (curNode.getRight().end - curNode.getRight().start);
                curNode.getRight().lazyUpdateFlag = true;
                curNode.getRight().lazyAdd = 0;
                curNode.getRight().lazyMax = curNode.lazyUpdate;
            }
            // 当前节点的更新任务失效
            curNode.lazyUpdateFlag = false;
        }
        // 执行完懒更新下发任务后, 再下发懒累加任务给子节点
        if (curNode.lazyAdd != 0) {
            if (curNode.getLeft() != null) {
                curNode.getLeft().lazyAdd += curNode.lazyAdd;
                curNode.getLeft().element += curNode.lazyAdd * (curNode.getLeft().end - curNode.getLeft().start);
                curNode.getLeft().lazyMax += curNode.lazyAdd;
            }
            if (curNode.getRight() != null) {
                curNode.getRight().lazyAdd += curNode.lazyAdd;
                curNode.getRight().element += curNode.lazyAdd * (curNode.getRight().end - curNode.getRight().start);
                curNode.getRight().lazyMax += curNode.lazyAdd;
            }
            // 下发完成后, 当前节点的懒累加任务清空
            curNode.lazyAdd = 0;
        }
    }

    /**
     * 初始化线段树
     *
     * @param from 线段范围(包含)
     * @param to   线段范围(不包含)
     */
    @Nullable
    private Node build(int from, int to) {
        if (from >= to) {
            return null;
        }
        Node curNode = new Node(from, to);
        if (from == to - 1) {
            curNode.setElement(arr[from]);
            curNode.setLazyMax(arr[from]);
        } else {
            int mid = from + ((to - from) >> 1);
            curNode.setLeft(build(from, mid));
            curNode.setRight(build(mid, to));
            pushUp(curNode);
        }
        return curNode;
    }

    private void pushUp(@NotNull Node curNode) {
        int leftElement = curNode.getLeft() == null ? 0 : curNode.getLeft().element;
        int rightElement = curNode.getRight() == null ? 0 : curNode.getRight().element;
        curNode.setElement(leftElement + rightElement);
        int leftMax = curNode.getLeft() == null ? Integer.MIN_VALUE : curNode.getLeft().lazyMax;
        int rightMax = curNode.getRight() == null ? Integer.MIN_VALUE : curNode.getRight().lazyMax;
        curNode.setLazyMax(Math.max(leftMax, rightMax));
    }

    public void print() {
        ElementBinaryTree.print(this.getRoot());
    }

}
