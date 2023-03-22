package com.xiaoxiaoyi.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 通用工具
 * @date 3/18/2023 6:54 PM
 */
public class CommonUtil {
    public static void swap(@NotNull int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Contract(pure = true)
    public static void toQianZhui(@NotNull int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            arr[i] += arr[i - 1];
        }
    }
}
