package com.xiaoxiaoyi.orderedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoxiaoyi
 * 跳表
 */
public class SkipListMap {

    /**
     * 跳表节点
     *
     * @param <K> key 要求继承自Comparable, 即可以进行比较
     * @param <V> value
     */
    public static class SkipListNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        // 指向下一个节点的指针数组
        public List<SkipListNode<K, V>> nextNodes;

        public SkipListNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.nextNodes = new ArrayList<>();
        }

        /**
         * 判断this.key是否小于otherKey
         */
        public boolean keyLessThan(K otherKey) {
            return otherKey != null &&
                    (this.key == null
                            || this.key.compareTo(otherKey) < 0
                    );
        }

        /**
         * 判断this.key是否等于otherKey
         */
        public boolean keyEqual(K otherKey) {
            return (this.key == null && otherKey == null)
                    || (this.key != null && otherKey != null && this.key.compareTo(otherKey) == 0);
        }
    }

    public static class SkipList<K extends Comparable<K>, V> {
        // 产生下一条指针的概率(每个节点都可能产生1~多个next指针)
        private static final double PROBABILITY = 0.5;
        // 头结点
        public final SkipListNode<K, V> head;
        // 跳表大小(节点个数)
        private int size;
        // 跳表节点中最大的层数(链表个数)
        public int maxLevel;

        public SkipList() {
            head = new SkipListNode<>(null, null);
            head.nextNodes.add(null);
            size = 0;
            maxLevel = 0;
        }

        /**
         * @param key   小于等于的key
         * @param cur   当前遍历到的节点
         * @param level 当前遍历到的层
         */
        private SkipListNode<K, V> rightMostLessNodeAtLevel(K key, SkipListNode<K, V> cur, int level) {
            // 记录当前节点的当前层的下一个节点
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            // 当没有走到最后且, 当前层的下一个节点key比目标key小, 说明在当前层可以往右走
            while (next != null && next.keyLessThan(key)) {
                // 往右走
                cur = next;
                // 更新next
                next = cur.nextNodes.get(level);
            }
            // 走到最后或者next > key时返回
            return cur;
        }

        /**
         * 寻找树上最右边的小于key的节点
         */
        private SkipListNode<K, V> rightMostLessNodeOnTree(K key) {
            if (key == null) {
                return null;
            }
            int level = maxLevel;
            // 从头结点开始找
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                // 一层一层地遍历寻找最右边的小于key的节点
                cur = rightMostLessNodeAtLevel(key, cur, level--);
            }
            return cur;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            // 先找到小于它的最右节点
            SkipListNode<K, V> less = rightMostLessNodeOnTree(key);
            // 判断它的右边节点是否等于目标, 等于则包含, 不等于则不包含
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.keyEqual(key);
        }

        /**
         * 新增记录
         */
        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            // 找到最右的小于它的节点
            SkipListNode<K, V> less = rightMostLessNodeOnTree(key);
            // 找到要插入节点的位置
            SkipListNode<K, V> find = less.nextNodes.get(0);
            if (find != null && find.keyEqual(key)) {
                // 目标位置不为空且key值重复, 则指用更新value
                find.value = value;
            } else {
                // 插入节点, size + 1
                this.size++;
                // 准备随机生成next指针, level为next指针的个数
                int newNodeLevel = 0;
                // PROBABILITY: 生成next指针的概率
                while (Math.random() < PROBABILITY) {
                    // 新增一个next指针
                    newNodeLevel++;
                }
                while (newNodeLevel > maxLevel) {
                    // 保证head节点的next指针始终保持最大
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                SkipListNode<K, V> newNode = new SkipListNode<>(key, value);
                // 新节点的next指针有newNodeLevel + 1个(至少1个)
                for (int i = 0; i <= newNodeLevel; i++) {
                    newNode.nextNodes.add(null);
                }
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                // 遍历每一层
                while (level >= 0) {
                    // 在当前层找到最右边的小于新节点的节点为新节点该层的前趋节点(跳跃的过程)
                    pre = rightMostLessNodeAtLevel(key, pre, level);
                    // 当前遍历到的层数比新节点的层数小时
                    if (level <= newNodeLevel) {
                        // 新节点指向前驱结点当前层的next
                        newNode.nextNodes.set(level, pre.nextNodes.get(level));
                        // 前驱结点当前层的next指向新节点(单链表的插入操作)
                        pre.nextNodes.set(level, newNode);
                    }
                    // 继续去下一层
                    level--;
                }
            }
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            // 找到树上小于该key的最右节点
            SkipListNode<K, V> less = rightMostLessNodeOnTree(key);
            // 获取该节点的next节点
            SkipListNode<K, V> next = less.nextNodes.get(0);
            // 如果next的key等于目标key, 则找到了
            return next != null && next.keyEqual(key) ? next.value : null;
        }

        public void remove(K key) {
            if (containsKey(key)) {
                // 包含该节点才能删除
                size--;
                // 从顶层开始遍历
                int level = maxLevel;
                // 前驱结点
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    // 在当前层上找小于目标节点key的最右节点(跳跃的过程)
                    pre = rightMostLessNodeAtLevel(key, pre, level);
                    // 获取当前层上找到的最右节点的next
                    SkipListNode<K, V> next = pre.nextNodes.get(level);
                    if (next != null && next.keyEqual(key)) {
                        // 找到了目标节点next(链表的删除操作, 前趋节点的next跳过原来的指向删除节点的next)
                        pre.nextNodes.set(level, next.nextNodes.get(level));
                    }
                    if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
                        // 如果当前层没有节点了, 则删除当前层
                        head.nextNodes.remove(level);
                        maxLevel--;
                    }
                    // 继续遍历下一层
                    level--;
                }
            }
        }

        /**
         * 获取树上的第一个key
         */
        public K firstKey() {
            return head.nextNodes.get(0) != null ? head.nextNodes.get(0).key : null;
        }

        /**
         * 获取树上的最后一个key
         */
        public K lastKey() {
            // 从顶层开始
            int level = maxLevel;
            // 从头结点开始
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                // 跳跃(对于底层而言)
                SkipListNode<K, V> next = cur.nextNodes.get(level);
                while (next != null) {
                    // 不停跳到当前层的最后1个节点
                    cur = next;
                    next = cur.nextNodes.get(level);
                }
                // 继续去下一层
                level--;
            }
            return cur.key;
        }

        /**
         * key向上取整(大于等于key的第1个key)
         */
        public K ceilingKey(K key) {
            if (key == null) {
                // 认为null比所有key小, 所有第一个key肯定大于它
                return firstKey();
            }
            // 找到树上最右边的小于key的节点
            SkipListNode<K, V> less = rightMostLessNodeOnTree(key);
            // less的next必定 >= key
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null ? next.key : null;
        }

        /**
         * 向下取整的key(小于等于目标key)
         */
        public K floorKey(K key) {
            if (key == null) {
                // null已经是最小的key了, 没有比它更小的
                return null;
            }
            SkipListNode<K, V> less = rightMostLessNodeOnTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            // 判断是否可能等于key, 不等于则返回less
            return next != null && next.keyEqual(key) ? next.key : less.key;
        }

        public int size() {
            return size;
        }
    }
}
