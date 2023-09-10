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
            return;
        }
        // 如果当前节点没被累加区间完全包含, 那么需要将当前节点的累加和更新任务向子节点进行传递
        pushDown(curNode);
        int mid = curNode.start + ((curNode.end - curNode.start) >> 1);
        if (from < mid) {
            // 左子结点范围是: [curNode.start, mid), from < mid 说明操作范围与左子节点范围有交集, 需要传递任务给左子结点
            add(curNode.getLeft(), from, to, value);
        }
        if (to > mid) {
            // 右子节点范围是: [mid, curNode.end), to >= mid 说明操作范围与右子节点范围有交集, 需要传递任务给右子结点
            add(curNode.getRight(), from, to, value);
        }
        // 子节点更新完后, 自己更新
        pushUp(curNode);
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
            return;
        }
        // 如果当前节点没被累加区间完全包含, 那么需要将当前节点的累加和更新任务向子节点进行传递
        pushDown(curNode);
        int mid = curNode.start + ((curNode.end - curNode.start) >> 1);
        if (from < mid) {
            update(curNode.getLeft(), from, to, value);
        }
        if (to > mid) {
            update(curNode.getRight(), from, to, value);
        }
        pushUp(curNode);
    }

    /**
     * 查询范围信息
     *
     * @param from 范围左边界(包含)
     * @param to   范围右边界(不包含)
     * @return 范围信息
     */
    public int query(int from, int to) {
        return query(getRoot(), from, to);
    }

    /**
     * 查询范围信息
     *
     * @param curNode 处理的节点
     * @param from    范围左边界(包含)
     * @param to      范围右边界(不包含)
     * @return 范国数据
     */
    public int query(Node curNode, int from, int to) {
        if (from >= to || curNode == null) {
            return 0;
        }
        if (curNode.start >= from && curNode.end <= to) {
            // 如果查询范围完全包含当前节点范围, 那么直接返回当前节点的信息
            return curNode.element;
        }
        // 如果查询范围并未完全包含当前节点范围, 那么当前节点先下发任务给子节点, 让子节点去更新
        pushDown(curNode);
        int mid = curNode.start + ((curNode.end - curNode.start) >> 1);
        int res = 0;
        if (from < mid) {
            res += query(curNode.getLeft(), from, to);
        }
        if (to > mid) {
            res += query(curNode.getRight(), from, to);
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
            }
            if (curNode.getRight() != null) {
                // 如果存在右节点, 则发给右节点
                curNode.getRight().lazyUpdate = curNode.lazyUpdate;
                curNode.getRight().element = curNode.lazyUpdate * (curNode.getRight().end - curNode.getRight().start);
                curNode.getRight().lazyUpdateFlag = true;
                curNode.getRight().lazyAdd = 0;
            }
            // 当前节点的更新任务失效
            curNode.lazyUpdateFlag = false;
        }
        // 执行完懒更新下发任务后, 再下发懒累加任务给子节点
        if (curNode.lazyAdd != 0) {
            if (curNode.getLeft() != null) {
                curNode.getLeft().lazyAdd += curNode.lazyAdd;
                curNode.getLeft().element += curNode.lazyAdd * (curNode.getLeft().end - curNode.getLeft().start);
            }
            if (curNode.getRight() != null) {
                curNode.getRight().lazyAdd += curNode.lazyAdd;
                curNode.getRight().element += curNode.lazyAdd * (curNode.getRight().end - curNode.getRight().start);
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
        } else {
            int mid = from + ((to - from) >> 1);
            curNode.setLeft(build(from, mid));
            curNode.setRight(build(mid, to));
            pushUp(curNode);
        }
        return curNode;
    }

    private void pushUp(@NotNull Node curNode) {
        if (curNode.getLeft() != null && curNode.getRight() != null) {
            curNode.element = curNode.getLeft().element + curNode.getRight().element;
        }
    }

    public void print() {
        ElementBinaryTree.print(this.getRoot());
    }

}
