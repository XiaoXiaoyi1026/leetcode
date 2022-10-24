package com.xiaoxiaoyi.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoxiaoyi
 * 打印字符串的全排列(不重复)
 */
public class PrintAllPermutations {

    public static List<String> printAllPermutations(String str) {
        char[] chs = str.toCharArray();
        List<String> res = new ArrayList<>();
        process(chs, 0, res);
        return res;
    }

    private static void process(char[] str, int i, List<String> res) {
        if (i == str.length - 1) {
            res.add(String.valueOf(str));
            return;
        }
        // 记录后续每一个小写字符是否访问过，剪枝
        boolean[] visited = new boolean[26];
        for (int j = i; j < str.length; j++) {
            if (!visited[str[j] - 'a']) {
                // 当这个小写字符没有重复过时，才进行交换
                visited[str[j] - 'a'] = true;
                swap(str, i, j);
                process(str, i + 1, res);
                swap(str, i, j);
            }
        }
    }

    private static void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

}
