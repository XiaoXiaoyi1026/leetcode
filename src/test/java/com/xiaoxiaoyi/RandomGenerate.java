package com.xiaoxiaoyi;

public class RandomGenerate {

    /**
     * @param length 随机数组长度
     * @param max    元素最大值
     * @return 数组
     */
    public static int[] array(int length, int max) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    /**
     * @param characters 字符集
     * @param length     字符串长度
     * @return 只包含characters中字符的字符串
     */
    public static String containsCharactersString(char[] characters, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters[(int) (Math.random() * characters.length)]);
        }
        return String.valueOf(sb);
    }

}
