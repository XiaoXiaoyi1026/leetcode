package com.xiaoxiaoyi.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoxiaoyi
 * DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。
 * 例如，"ACGAATTCCG" 是一个 DNA序列 。
 * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/repeated-dna-sequences">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code187 {

    private static final int N = (int) 1e5+10, P = 131313;
    /**
     * H记录DNA序列中计算到每一个字符时的哈希值，p为累乘
     */
    private static final long[] H = new long[N], p = new long[N];

    /**
     * 使用字符串hash + 前缀和
     */
    public static List<String> findRepeatedDnaSequences(String s) {
        // 记录结果
        List<String> ans = new ArrayList<>();
        // 字符串长度
        int n = s.length();
        // 累乘的首位为1
        p[0] = 1;
        // 遍历1~n
        for (int i = 1; i <= n; i++) {
            // 计算s中每个字符的hash
            H[i] = H[i - 1] * P + s.charAt(i - 1);
            p[i] = p[i - 1] * P;
        }
        // 存储字符串的hash及其出现过的次数
        Map<Long, Integer> map = new HashMap<>(n);
        for (int i = 1; i + 10 - 1 <= n; i++) {
            // i开头的字符串的右边界
            int j = i + 10 - 1;
            // 计算当前字符的hash值
            long hash = H[j] - H[i - 1] * p[j - i + 1];
            // 去map里找当前字符串出现次数
            int cnt = map.getOrDefault(hash, 0);
            // 如果当前字符串已经出现过一次，则加入结果数组
            if (cnt == 1) {
                ans.add(s.substring(i - 1, i + 10 - 1));
            }
            // 当前字符串的出现次数 + 1
            map.put(hash, cnt + 1);
        }
        return ans;
    }

}
