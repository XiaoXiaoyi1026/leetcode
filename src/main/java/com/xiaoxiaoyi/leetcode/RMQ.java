package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomUtils;
import org.jetbrains.annotations.Contract;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 数据结构
 * 可以返回任意范围内的最大值/最小值,
 * 数据结构生成时间复杂度O(NlogN), 空间复杂度O(NlogN)
 * 查询时间复杂度O(1), 与线段树不同的是, 不支持数据的修改
 */
public class RMQ<T> {

    /**
     * 结构核心, max[i][j] 代表从原始数组下标j开始, 往后2^i次方个数中的最大值
     */
    final T[][] maxTable;

    /**
     * 元素比较器
     */
    final Comparator<T> comparator;

    /**
     * @param arr        原始数组
     * @param comparator 元素比较器
     */
    @SuppressWarnings("unchecked")
    public RMQ(T[] arr, Comparator<T> comparator) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr is null or empty");
        }
        int n = arr.length; // 一共n列
        int m = log2(n) + 1; // 最大值表一共有m行, 比如7个数据, 就要有3行, 8个数据就要有4行
        maxTable = (T[][]) new Object[m][n]; // 初始化最大值表
        System.arraycopy(arr, 0, maxTable[0], 0, n); // 初始化从原始数组每个位置开始, 往后2^0次方个数中的最大值, 即指数为0
        for (int power = 1; power < m; power++) { // 指数范围从1~m-1, 从1开始, 因为0行在上一步已经初始化完成
            for (int index = 0; index < n - (1 << power) + 1; index++) { // 每一行都从原始数组下标0开始, 下标不能大于 n - 2^n + 1, 因为后面不足2^n个数, 不用统计
                // maxTable[3][10] = max(maxTable[2][10], maxTable[2][10 + (1 << 2)])
                maxTable[power][index] = max(maxTable[power - 1][index], maxTable[power - 1][index + (1 << (power - 1))]);
            }
        }
        this.comparator = comparator;
    }

    /**
     * @param arr 原始数组
     */
    public RMQ(T[] arr) {
        this(arr, null);
    }

    /**
     * 求最大值/最小值, 时间复杂度O(1)
     *
     * @param object1 比较对象1
     * @param object2 比较对象2
     * @return 较大的对象
     */
    T max(T object1, T object2) {
        return compare(object1, object2) >= 0 ? object1 : object2;
    }

    /**
     * @param left  求取范围左边界
     * @param right 求取范围右边界
     * @return [left, right)范围内的最大值, 时间复杂度O(1)
     * @throws IllegalArgumentException 左边界不能大于右边界
     */
    public T max(int left, int right) {
        if (left >= right) {
            throw new IllegalArgumentException("左边界不能大于等于右边界");
        }
        if (left == right - 1) {
            return maxTable[0][left]; // 范围内只有1个数, 直接返回指数为0的最大值
        }
        int m = log2(right - left); // 范围内一共有right - left个数, 2^m是数轴左侧最接近right - right的值
        // 要求max(0, 8), 则从0开始, 往右数8位, 从8 - 1下标开始, 往左数8位, 也相当于从8 - 2^3开始往右数8位
        return max(maxTable[m][left], maxTable[m][right - (1 << m)]);
    }

    /**
     * @param object1 比较对象1
     * @param object2 比较对象2
     * @return 比较结果
     */
    @SuppressWarnings("unchecked")
    @Contract(pure = true)
    int compare(T object1, T object2) {
        if (comparator != null) {
            return comparator.compare(object1, object2); // 使用比较器进行比较
        }
        // 比较器为空, 则用对象本身的比较方法进行比较
        return ((Comparable<T>) object1).compareTo(object2);
    }

    /**
     * @param n 数字
     * @return log2(n) 向下取整的值
     */
    static int log2(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("底数不能小于等于0"); // 不允许负数或0的情况
        }
        // n为1时, 返回0, 否则递归去
        return n == 1 ? 0 : 1 + log2(n >> 1);
    }

    /**
     * 检查方法, 同于测试
     *
     * @param arr   测试数组
     * @param left  起始下标
     * @param right 结束下标
     * @return [left, right)范围内的最大值, 时间复杂度O(right - left)
     * @throws IllegalArgumentException 左边界不能大于右边界
     */
    static int check(int[] arr, int left, int right) {
        if (left >= right) {
            throw new IllegalArgumentException("左边界不能大于等于右边界");
        }
        int ans = 0;
        for (int i = left; i < right; i++) {
            ans = Math.max(ans, arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int length; // 测试数组最大长度
        int[] test; // 随机生成的测试数组
        Integer[] arr; // 转换后的包装数组
        int testTimes = 20000; // 测试次数, 每次测试都会重新生成测试数组, 时间复杂度O(NlogN)
        int left; // 测试范围左边界, [left, right)范围内的最大值, 时间复杂度O(1)
        int right;
        int ans1; // 测试方法返回的最大值, 时间复杂度O(1)
        int ans2; // 检查方法返回的最大值, 时间复杂度O(right - left)
        RMQ<Integer> rmq; // 测试数据结构
        System.out.println("test start");
        for (int i = 0; i < testTimes; i++) {
            length = RandomUtils.nextInt(20, 100);
            test = RandomUtils.array(length, 100);
            arr = Arrays.stream(test).boxed().toArray(Integer[]::new);
            rmq = new RMQ<>(arr);
            left = RandomUtils.nextInt(arr.length);
            right = left + RandomUtils.nextInt(arr.length - left) + 1;
            ans1 = rmq.max(left, right);
            ans2 = check(test, left, right);
            if (ans1 != ans2) {
                System.out.println("error");
                System.out.println(Arrays.toString(test));
                System.out.println(left + " " + right);
                System.out.println(ans1);
                System.out.println(ans2);
                return;
            }
        }
        System.out.println("test finish");
    }

}
