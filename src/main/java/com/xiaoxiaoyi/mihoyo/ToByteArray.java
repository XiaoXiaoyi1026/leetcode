package com.xiaoxiaoyi.mihoyo;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 编码转换
 * @date 3/18/2023 3:21 PM
 */
public class ToByteArray {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println(Arrays.toString(toByteArray(n)));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    public static String[] toByteArray (int value) {
        if (value < 1) {
            return new String[]{String.valueOf(value)};
        }
        List<String> tmp = new ArrayList<>();
        // write code here
        while (value >= 1) {
            int yuShu = value % 128;
            value = value / 128;
            if (value >= 1) {
                yuShu += 128;
            }
            tmp.add(String.valueOf(convertTo16(yuShu)));
        }
        String[] res = new String[tmp.size()];
        int i = 0;
        for (String s : tmp) {
            res[i++] = s;
        }
        return res;
    }

    public static String convertTo16(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 16) {
            int shang = num / 16;
            append(sb, shang);
            num = num % 16;
        }
        append(sb, num);
        return String.valueOf(sb);
    }

    public static void append(StringBuilder sb, int num) {
        if (num < 10) {
            sb.append(num);
        } else {
            sb.append('A' + (num - 10));
        }
    }
}
