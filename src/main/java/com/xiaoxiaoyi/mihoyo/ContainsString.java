package com.xiaoxiaoyi.mihoyo;

import java.util.Scanner;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 包含字符
 * @date 3/17/2023 3:48 PM
 */
public class ContainsString {
    
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        String input = INPUT.nextLine();
        String start = "<div>";
        String end = "</div>";
        int n = input.length();
        int ans = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            if (input.charAt(i) == '<') {
                if (i + 5 <= n && input.substring(i, i + 5).equals(start)) {
                    cnt++;
                }
                if (i + 6 <= n && input.substring(i, i + 6).equals(end)) {
                    if (cnt > 0) {
                        cnt--;
                        ans++;
                    }
                }
            }
        }
        System.out.println(ans);
    }
    
}
