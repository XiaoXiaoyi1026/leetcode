package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.CommonUtil;
import com.xiaoxiaoyi.utils.RandomGenerate;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 基数排序
 * @date 3/22/2023 5:37 PM
 */
public class RadixSort {
    public static int radix = 10;

    public static void setRadix(int radix) {
        RadixSort.radix = radix;
    }

    public static void main(String[] args) {
        int[] arr = RandomGenerate.array(20, 50);
        System.out.println(Arrays.toString(arr));
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static int getWeiShu(int num) {
        int weiShu = 0;
        num = Math.abs(num);
        while (num > 0) {
            weiShu++;
            num /= radix;
        }
        return weiShu;
    }

    @Contract(pure = true)
    public static void radixSort(@NotNull int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            max = Math.max(max, num);
        }
        radixSort(arr, 0, arr.length - 1, getWeiShu(max));
    }

    @Contract(pure = true)
    public static void radixSort(@NotNull int[] arr, int left, int right, int weiShu) {
        // 准备基数大小的count数组, 用于统计每个数字的出现次数
        int[] count;
        // 辅助数组准备长度个
        int[] help = new int[right - left + 1];
        int tmp;
        // 位数决定一共要入桶和出桶的次数
        for (int wei = 1, index; wei <= weiShu; wei++) {
            count = new int[radix];
            // 所有元素按照从低位到高位的优先级入桶
            for (index = left; index <= right; index++) {
                // 统计每个数字的出现次数
                count[getWei(arr[index], wei)]++;
            }
            // 转换为前缀和数组
            CommonUtil.toQianZhui(count);
            // 从右往左的顺序扫描出桶
            for (index = right; index >= left; index--) {
                // 将每个数放到对应的位置上
                help[--count[getWei(arr[index], wei)]] = arr[index];
            }
            // 放完之后将该轮辅助数组的结果拷贝回arr
            for (index = left, tmp = 0; index <= right; index++) {
                arr[index] = help[tmp++];
            }
        }
    }

    public static int getWei(int num, int wei) {
        return ((num / (int) (Math.pow(10, wei - 1))) % 10);
    }

}
