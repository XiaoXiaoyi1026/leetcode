package com.xiaoxiaoyi.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoxiaoyi
 * 实现LRU缓存, 要求建立缓存时传入缓存大小以及缓存存储的泛型, 还有缓存对象的比较器
 * 当缓存满的时候根据对象比较器用插入的对象替换掉优先级最低的那个对象
 * 缓存提供add和get方法, 要求2个方法的时间复杂度都是O(1)
 * 缓存中进行的所有操作都是O(1)
 */
public class LFUCache<K extends Comparable<K>, V> {
    private int size;
    private final int maxSize;
    private final Map<K, Element<K, V>> elementMap;
    private final Map<Integer, Bucket<K, V>> bucketMap;
    private Bucket<K, V> first;
    private Bucket<K, V> last;

    protected static class Bucket<K extends Comparable<K>, V> {
        /*
        桶内装的是CacheObject, first指向桶内第一个元素, last指向桶内最后一个元素
         */
        Element<K, V> first;
        Element<K, V> last;
        /*
        桶内装的都是操作次数为operations的cacheObject
         */
        int operations;
        Bucket<K, V> previous;
        Bucket<K, V> next;

        Bucket(int operations) {
            this.operations = operations;
            previous = next = null;
        }

        /**
         * 将bucket连接到this之后
         */
        protected void nextLink(Bucket<K, V> bucket) {
            bucket.next = this.next;
            bucket.previous = this;
            this.next = bucket;
            if (bucket.next != null) {
                bucket.next.previous = bucket;
            }
        }

        /**
         * 将bucket连接到this之前
         */
        protected void previousLink(Bucket<K, V> bucket) {
            bucket.previous = this.previous;
            bucket.next = this;
            this.previous = bucket;
            if (bucket.previous != null) {
                bucket.previous.next = bucket;
            }
        }

        /**
         * 在桶的最后插入一个元素
         */
        protected void addLast(Element<K, V> element) {
            if (last == null) {
                first = last = element;
            } else {
                last.nextLink(element);
                last = element;
            }
        }

        /**
         * 从桶中移除一个元素
         */
        protected void remove(Element<K, V> removeElement) {
            if (removeElement.previous != null) {
                removeElement.previous.next = removeElement.next;
            } else {
                // 如果移除的是第一个元素
                first = removeElement.next;
            }
            if (removeElement.next != null) {
                removeElement.next.previous = removeElement.previous;
            } else {
                // 如果移除的是最后一个元素
                last = removeElement.previous;
            }
        }

        /**
         * 判断桶是否只有一个元素
         */
        boolean onlyOne() {
            return first != null && first == last;
        }

        @Override
        public String toString() {
            return "Bucket{" +
                    "first=" + first +
                    ", last=" + last +
                    ", operations=" + operations +
                    '}';
        }
    }

    protected static class Element<K, V> {
        private final K key;
        private V value;
        private int operations;
        private Element<K, V> previous;
        private Element<K, V> next;

        Element(K key, V value) {
            this.key = key;
            this.value = value;
            operations = 1;
            previous = next = null;
        }

        /**
         * 在this后连上element
         */
        protected void nextLink(Element<K, V> element) {
            element.next = this.next;
            element.previous = this;
            this.next = element;
            if (element.next != null) {
                element.next.previous = element;
            }
        }

        @Override
        public String toString() {
            return "Element{" +
                    "key=" + key +
                    ", value=" + value +
                    ", operations=" + operations +
                    '}';
        }
    }

    LFUCache(int initialSize) {
        size = 0;
        maxSize = initialSize;
        elementMap = new HashMap<>(initialSize);
        bucketMap = new HashMap<>();
        first = last = null;
    }

    public Map<K, Element<K, V>> getElementMap() {
        return elementMap;
    }

    public Map<Integer, Bucket<K, V>> getBucketMap() {
        return bucketMap;
    }

    public void set(K key, V value) {
        // 从缓存map中取出要set的对象(O(1))
        Element<K, V> setElement = elementMap.get(key);
        if (setElement != null) {
            // 如果之前加入过该对象, 则更新其value
            setElement.value = value;
            operation(setElement);
        } else {
            // 之前没加入过该对象, 则新建该对象, 并设置好操作次数和最后操作时间
            add(new Element<>(key, value));
        }
    }

    public V get(K key) {
        Element<K, V> element = elementMap.get(key);
        if (element == null) {
            return null;
        }
        operation(element);
        return element.value;
    }

    protected void add(Element<K, V> element) {
        // 判断缓存满没满
        if (size < maxSize) {
            // 如果缓存没满, 将新加入的对象put到elementMap中去
            elementMap.put(element.key, element);
            size++;
            // 加入对应的bucket
            Bucket<K, V> bucket = bucketMap.get(1);
            if (bucket == null) {
                bucket = new Bucket<>(1);
                if (!isEmpty()) {
                    first.previousLink(bucket);
                }
                first = bucket;
                bucketMap.put(1, bucket);
            }
            bucket.addLast(element);
        } else {
            /*
            缓存满了, 根据规则删除数据, 先拿到操作次数最少的bucket(first)
            elementMap删除桶中第一个元素
             */
            elementMap.remove(first.first.key);
            if (first.onlyOne()) {
                // 桶里只有1个元素, 直接删除这个桶即可
                removeBucket(first);
                first = first.next;
            } else {
                // 桶里不止一个元素, 则删除桶里的第一个元素
                first.remove(first.first);
            }
            size--;
            add(element);
        }
    }

    protected void operation(Element<K, V> element) {
        // 先获取它所在的bucket
        Bucket<K, V> previousBucket = bucketMap.get(element.operations);
        // 该元素的操作次数+1
        element.operations++;
        // 判断操作次数+1的桶存不存在
        Bucket<K, V> nextBucket = bucketMap.get(element.operations);
        if (nextBucket != null) {
            // 如果存在, 则将该元素加到桶最后去
            nextBucket.addLast(element);
        } else {
            // 如果该桶不存在, 则新建
            nextBucket = new Bucket<>(element.operations);
            nextBucket.addLast(element);
            // 插入新桶, 保证升序
            previousBucket.nextLink(nextBucket);
            if (last == previousBucket) {
                last = nextBucket;
            }
            // bucketMap添加记录
            bucketMap.put(element.operations, nextBucket);
        }
        if (previousBucket.onlyOne()) {
            // 如果桶中只有一个元素, 直接删除该桶
            removeBucket(previousBucket);
        } else {
            // 从桶中移除该元素
            previousBucket.remove(element);
        }
    }

    protected void removeBucket(Bucket<K, V> bucket) {
        if (bucket.previous != null) {
            bucket.previous.next = bucket.next;
        } else {
            first = bucket.next;
        }
        if (bucket.next != null) {
            bucket.next.previous = bucket.previous;
        } else {
            last = bucket.previous;
        }
        bucketMap.remove(bucket.operations);
    }

    public boolean isEmpty() {
        return first == null && last == null;
    }
}
