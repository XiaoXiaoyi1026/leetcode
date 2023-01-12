package com.xiaoxiaoyi;

public class RandomArray {

    public static int[] generate(int length, int max) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

}
