package com.xiaoxiaoyi.mihoyo;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 子序列
 * @date 3/19/2023 8:42 PM
 */
public class ZiXuLie {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String from, to;
        for (int i = 0; i < n; i++) {
            from = sc.next();
            to = sc.next();
            if (process(from, to)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    /**
     * 范围尝试
     */
    public static boolean process(@NotNull String from, @NotNull String to) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int n = from.length();
        int m = to.length();
        int[] help1 = new int[3];
        char c;
        for (int i = 0; i < n; i++) {
            c = from.charAt(i);
            if (c == 'm') {
                help1[0]++;
            } else if (c == 'h') {
                help1[1]++;
            } else if (c == 'y') {
                help1[2]++;
            } else {
                sb1.append(c);
            }
        }
        for (int i = 0; i < m; i++) {
            c = to.charAt(i);
            if (c == 'm') {
                help1[0]--;
            } else if (c == 'h') {
                help1[1]--;
            } else if (c == 'y') {
                help1[2]--;
            } else {
                sb2.append(c);
            }
        }
        return String.valueOf(sb1).equals(String.valueOf(sb2)) &&
                help1[0] == help1[1] && help1[1] == help1[2];
    }

}
