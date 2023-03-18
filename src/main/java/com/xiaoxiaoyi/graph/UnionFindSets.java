package com.xiaoxiaoyi.graph;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @param <V> 并查集保存的元素类型
 *            并查集结构
 * @author xiaoxiaoyi
 */
public class UnionFindSets<V> {

    /**
     * key: 样本的值
     * value: 对应UnionFind中的element
     */
    private final Map<V, Element<V>> elementMap;

    /**
     * key: element
     * value: element的父element
     */
    private final Map<Element<V>, Element<V>> fatherMap;

    /**
     * key: 每个小集合的代表元素
     * value: 这个集合的大小
     */
    private final Map<Element<V>, Integer> sizeMap;

    /**
     * 封装一个样本的数据结构
     *
     * @param <V> 元素
     */
    public static class Element<V> {
        public V value;

        public Element(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "value=" + value +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Element<?> element)) {
                return false;
            }

            return Objects.equals(value, element.value);
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }
    }

    /**
     * 并查集初始化
     *
     * @param values 要添加的所有value组成的集合
     */
    public UnionFindSets(@NotNull List<V> values) {
        // 初始化3个map
        elementMap = new HashMap<>();
        fatherMap = new HashMap<>();
        sizeMap = new HashMap<>();
        // 遍历样本进行初始化
        for (V value : values) {
            // 把每一个样本封装成element
            Element<V> element = new Element<>(value);
            elementMap.put(value, element);
            // 一开始每个element的父指针都指向自己
            fatherMap.put(element, element);
            // 一开始集合中只有它自己一个element
            sizeMap.put(element, 1);
        }
    }

    /**
     * 查找一个样本的最高能到达的父节点
     *
     * @param element 样本
     * @return 最高父节点
     */
    private Element<V> findHead(Element<V> element) {
        // 记录往上经过的所有element
        Stack<Element<V>> path = new Stack<>();
        Element<V> father = fatherMap.get(element);
        // 最高父节点的父指针应该指向自己
        while (father != element) {
            path.push(element);
            element = father;
            // 一定要更新father！！！！！
            father = fatherMap.get(element);
        }
        // 此时的element就是要返回的结果
        while (!path.isEmpty()) {
            // 将沿途经过的路径“压扁”，即更改其父节点为element
            fatherMap.put(path.pop(), element);
        }
        // 返回最高父节点
        return element;
    }

    /**
     * 查询两个样本是否在一个集合
     *
     * @param a 样本a
     * @param b 样本b
     * @return a和b在不在同一个集合
     */
    public boolean isSameSet(V a, V b) {
        if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
            // 两个样本都在并查集里
            return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
        }
        return false;
    }

    /**
     * 合并两个样本所在的集合
     *
     * @param a 样本a
     * @param b 样本b
     */
    public void union(V a, V b) {
        if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
            // 两个样本都在并查集中，先查找2个样本的代表节点
            Element<V> aFather = findHead(elementMap.get(a));
            Element<V> bFather = findHead(elementMap.get(b));
            if (aFather != bFather) {
                try {
                    // 两个样本的父节点不一样才进行合并
                    Element<V> bigSet = sizeMap.get(aFather) >= sizeMap.get(bFather) ? aFather : bFather;
                    Element<V> smallSet = bigSet == aFather ? bFather : aFather;
                    // 小的集合挂到大的集合下边
                    fatherMap.put(smallSet, bigSet);
                    // 更新大集合的大小
                    sizeMap.put(bigSet, sizeMap.get(bigSet) + sizeMap.get(smallSet));
                    // 小集合不是代表节点了
                    sizeMap.remove(smallSet);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
    }

}
