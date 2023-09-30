package com.xiaoxiaoyi.utils;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomGenerate {

    static final Random RANDOM;

    static {
        RANDOM = new Random();
    }

    private RandomGenerate() {
    }

    @NotNull
    public static int[] set(int length, int max) {
        return set(length, max, 0);
    }

    @NotNull
    public static int[] set(int length, int max, boolean randomLength) {
        if (randomLength) {
            length = RANDOM.nextInt(length);
        }
        return set(length, max, 0);
    }

    @NotNull
    public static int[] set(int length, int max, int min) {
        Set<Integer> set = new HashSet<>();
        while (set.size() < length) {
            set.add(RANDOM.nextInt(max - min) + min);
        }
        int[] arr = new int[length];
        int index = 0;
        for (Integer i : set) {
            arr[index++] = i;
        }
        return arr;
    }

    /**
     * @param length 随机数组长度
     * @param max    元素最大值(不包含)
     * @return 数组
     */
    @NotNull
    public static int[] array(int length, int max) {
        return array(length, max, 0);
    }

    /**
     * @param length 数组长度
     * @param max    数组元素最大值(不包含)
     * @param min    数组元素最小值(包含)
     * @return 数组
     */
    @NotNull
    public static int[] array(int length, int max, int min) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = RANDOM.nextInt(max - min) + min;
        }
        return arr;
    }

    @NotNull
    public static int[] array(int length, int max, boolean randomLength) {
        return array(length, max, 0, randomLength);
    }

    /**
     * @param length 数组长度
     * @param max    数组元素最大值(不包含)
     * @param min    数组元素最小值(包含)
     * @param seed   随机数种子
     * @return 数组
     */
    @NotNull
    public static int[] array(int length, int max, int min, int seed) {
        RANDOM.setSeed(seed);
        return array(length, max, min);
    }

    /**
     * @param length       数组长度
     * @param max          数组元素最大值(不包含)
     * @param min          数组元素最小值(包含)
     * @param randomLength 数组长度是否随机
     * @return 数组
     */
    @NotNull
    public static int[] array(int length, int max, int min, boolean randomLength) {
        return array(length, max, min, RANDOM.nextInt(), randomLength);
    }

    /**
     * @param length       数组长度
     * @param max          数组元素最大值(不包含)
     * @param min          数组元素最小值(包含)
     * @param seed         随机数种子
     * @param randomLength 数组长度是否随机
     * @return 数组
     */
    @NotNull
    public static int[] array(int length, int max, int min, int seed, boolean randomLength) {
        RANDOM.setSeed(seed);
        if (randomLength) {
            length = 1 + RANDOM.nextInt(length);
        }
        return array(length, max, min);
    }

    /**
     * @param characters 字符集
     * @param length     字符串长度
     * @return 只包含characters中字符的字符串
     */
    public static String string(char[] characters, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters[RANDOM.nextInt(characters.length)]);
        }
        return String.valueOf(sb);
    }

    /**
     * @param characters 字符集
     * @param length     随机字符串长度
     * @param seed       随机种子
     * @return 随机字符串
     */
    public static String string(char[] characters, int length, int seed) {
        RANDOM.setSeed(seed);
        return string(characters, length);
    }

    /**
     * @param characters   字符集
     * @param length       随机字符串长度
     * @param randomLength 是否随机长度
     * @return 随机字符串
     */
    public static String string(char[] characters, int length, boolean randomLength) {
        return string(characters, length, RANDOM.nextInt(), randomLength);
    }

    /**
     * @param characters   字符集
     * @param length       长度
     * @param seed         种子
     * @param randomLength 是否随机长度
     * @return 随机字符串
     */
    private static String string(char[] characters, int length, int seed, boolean randomLength) {
        RANDOM.setSeed(seed);
        if (randomLength) {
            length = RANDOM.nextInt(length);
        }
        return string(characters, length);
    }

    /**
     * @param rows 矩阵行数
     * @param cols 矩阵列数
     * @param max  元素最大值
     * @return 矩阵
     */
    @NotNull
    public static int[][] matrix(int rows, int cols, int max) {
        return matrix(rows, cols, max, 0);
    }

    /**
     * @param rows 矩阵行数
     * @param cols 矩阵列数
     * @param max  元素最大值
     * @param min  元素最小值
     * @return 矩阵
     */
    @NotNull
    public static int[][] matrix(int rows, int cols, int max, int min) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            matrix[i] = array(cols, max, min);
        }
        return matrix;
    }

    public static Random getRandom() {
        return RANDOM;
    }

    public static int nextInt(int bound) {
        return RANDOM.nextInt(bound);
    }
}
