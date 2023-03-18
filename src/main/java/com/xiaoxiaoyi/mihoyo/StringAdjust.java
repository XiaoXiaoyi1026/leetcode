package com.xiaoxiaoyi.mihoyo;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 调整字符串数组
 * @date 3/18/2023 3:41 PM
 */
public class StringAdjust {

    public static void main(String[] args) {
        // ["louis","john","tom","","jerry","rabbit",""]
        String[] input = new String[]{"louis", "john", "tom", "", "jerry", "rabbit", ""};
        System.out.println(Arrays.toString(adjust(input)));
    }

    @NotNull
    @Contract(pure = true)
    public static String[] adjust(@NotNull String[] nickNameArray) {
        int n = nickNameArray.length;
        if (n < 2) {
            return nickNameArray;
        }
        int cur = n - 2, tmp, right = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (!"".equals(nickNameArray[i])) {
                break;
            }
            right++;
        }
        while (cur >= 0) {
            if ("".equals(nickNameArray[cur])) {
                tmp = cur;
                while (tmp < n - 1 - right) {
                    swap(nickNameArray, tmp, tmp + 1);
                    tmp++;
                }
                right++;
            }
            cur--;
        }
        return nickNameArray;
    }

    public static void swap(@NotNull String[] strings, int from, int to) {
        String tmp = strings[from];
        strings[from] = strings[to];
        strings[to] = tmp;
    }

}
