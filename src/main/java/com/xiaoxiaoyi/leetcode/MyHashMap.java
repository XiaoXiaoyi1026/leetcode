package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.tree.orderedlist.RedBlackTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author xiaoxiaoyi
 * 自实现类HashMap的结构
 */
public class MyHashMap<K, V> {
    private List<MyEntry<K, V>> entrySet;
    private int size;
    /**
     * 单链表长度到达convert时转换为红黑树
     */
    private final int convert;
    /**
     * entrySet已填的空间entrySize/size >= extension时, 将entrySet扩充为原来的一倍
     */
    private final double extension;
    /**
     * 整个map最后一次操作的时间
     */
    private long lastOperationTime;
    /**
     * 最后一次setAll的值
     */
    private V all;
    /**
     * 最后一次setAll的时间
     */
    private long lastSetAllTime;

    public static class MyEntry<K, V> extends RedBlackTree.Node<K> {
        private V value;
        /**
         * 记录插入的时间
         */
        private long time;
        /**
         * 节点所在的树
         */
        private RedBlackTree<K> tree;

        public MyEntry(K key, V value) {
            super(key, RedBlackTree.ColorEnum.BLACK);
            this.value = value;
            tree = null;
        }

        @Override
        public MyEntry<K, V> getLeft() {
            return (MyEntry<K, V>) super.getLeft();
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object other) {
            if (other == null) return false;
            if (getClass() != other.getClass()) return false;
            return this.getElement() == ((MyEntry<K, V>) other).getElement();
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.getElement());
        }
    }

    public MyHashMap() {
        entrySet = initializeSet(16);
        size = 0;
        convert = 8;
        extension = 0.75;
        lastOperationTime = 0;
        all = null;
        lastSetAllTime = Long.MIN_VALUE;
    }

    private void operation(MyEntry<K, V> myEntry) {
        lastOperationTime++;
        if (myEntry != null) {
            myEntry.time = lastOperationTime;
        }
    }

    public void setAll(V value) {
        all = value;
        lastSetAllTime = ++lastOperationTime;
    }

    /**
     * 查询包不包含目标key
     *
     * @param key 键
     * @return map中有无这个key
     */
    public boolean containsKey(K key) {
        int index = key.hashCode() % entrySet.size();
        MyEntry<K, V> curEntry = entrySet.get(index);
        if (curEntry == null) {
            operation(null);
            return false;
        } else {
            if (curEntry.tree == null) {
                // 如果还没有转变为红黑树, 则在单链表上查找key
                while (curEntry != null && curEntry.getElement() != key) {
                    curEntry = curEntry.getLeft();
                }
            } else {
                // 如果转变为红黑树了, 则在红黑树上查找key
                curEntry = (MyEntry<K, V>) curEntry.tree.findNode(key);
            }
            operation(curEntry);
            return curEntry != null;
        }
    }

    /**
     * 插入一条记录
     *
     * @param key   记录的键
     * @param value 值
     */
    public void put(K key, V value) {
        int index = key.hashCode() % entrySet.size();
        MyEntry<K, V> curEntry = entrySet.get(index);
        if (curEntry == null) {
            curEntry = new MyEntry<>(key, value);
            entrySet.set(index, curEntry);
            size++;
            if (size >= extension * entrySet.size()) {
                int newSize = entrySet.size() << 1;
                List<MyEntry<K, V>> newEntrySet = initializeSet(newSize);
                int highestOneBit = Integer.highestOneBit(newSize);
                for (int i = 0; i < size; i++) {
                    MyEntry<K, V> cur = entrySet.get(i);
                    if (cur != null) {
                        if ((cur.getElement().hashCode() & highestOneBit) == highestOneBit) {
                            newEntrySet.set(i + entrySet.size(), cur);
                        } else {
                            newEntrySet.set(i, cur);
                        }
                    }
                }
                entrySet = newEntrySet;
                size = newSize;
            }
        } else {
            int listSize = 0;
            MyEntry<K, V> tmp = curEntry;
            while (tmp != null && tmp.getElement() != key) {
                listSize++;
                tmp = tmp.getLeft();
            }
            if (tmp == null) {
                tmp = new MyEntry<>(key, value);
                if (listSize < convert) {
                    curEntry.setLeft(tmp);
                } else {
                    if (curEntry.tree == null) {
                        RedBlackTree<K> tree = new RedBlackTree<>(
                                Comparator.comparingInt(o -> o.getElement().hashCode())
                        );
                        while (curEntry != null) {
                            tree.insert(curEntry);
                            curEntry = curEntry.getLeft();
                        }
                        tree.insert(tmp);
                        MyEntry<K, V> root = getRoot(tree);
                        root.tree = tree;
                        entrySet.set(index, root);
                    } else {
                        curEntry.tree.insert(tmp);
                    }
                }
            } else {
                tmp.value = value;
            }
            operation(tmp);
        }
    }

    public V get(K key) {
        int index = key.hashCode() % entrySet.size();
        MyEntry<K, V> curEntry = entrySet.get(index);
        if (curEntry == null) {
            operation(null);
            return null;
        } else {
            if (curEntry.tree == null) {
                // 如果还没有转变为红黑树, 则在单链表上查找key
                while (curEntry != null && curEntry.getElement() != key) {
                    curEntry = curEntry.getLeft();
                }
            } else {
                // 如果转变为红黑树了, 则在红黑树上查找key
                curEntry = (MyEntry<K, V>) curEntry.tree.findNode(key);
            }
            V value = null;
            if (curEntry != null) {
                value = curEntry.time < lastSetAllTime ? all : curEntry.value;
            }
            operation(curEntry);
            return value;
        }
    }

    public int size() {
        return size;
    }

    private MyEntry<K, V> getRoot(RedBlackTree<K> tree) {
        return (MyEntry<K, V>) tree.getRoot();
    }

    private List<MyEntry<K, V>> initializeSet(int size) {
        List<MyEntry<K, V>> temp = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            temp.add(null);
        }
        return temp;
    }
}
