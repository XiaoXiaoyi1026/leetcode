package com.xiaoxiaoyi.other;

import com.xiaoxiaoyi.utils.RandomUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * 时间复杂度O(n * logn), 数学推论
 * 空间复杂度O(logn), 每次都划分中点
 *
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 使用partition(分区)进行的快速排序(升序)
 * @date 3/20/2023 4:49 PM
 */
public class PartitionQuickSort {

    public static void main(String[] args) {
        int[] nums;
        for (int i = 0; i < 100; i++) {
            nums = RandomUtils.array((int) (1 + Math.random() * 20), 50);
            System.out.println(Arrays.toString(nums));
            quickSort(nums);
            System.out.println(Arrays.toString(nums));
        }
    }

    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            // 在范围上随机选择一个数作为基准, 放到当前范围的最右边
            swap(nums, (int) (left + Math.random() * (right - left + 1)), right);
            // 执行分区操作, 返回等于区的左边界和右边界
            int[] pRes = partition(nums, left, right);
            // 递归去等于区的左边部分(小于区)
            quickSort(nums, left, pRes[0] - 1);
            // 递归去等于区的右边部分(大于区)
            quickSort(nums, pRes[1] + 1, right);
        }
    }

    @NotNull
    @Contract("_, _, _ -> new")
    public static int[] partition(@NotNull int[] nums, int left, int right) {
        // 基准数
        int standard = nums[right];
        // 小于区右边界(包含)
        int less = left - 1;
        // 大于区左边界(包含)
        int more = right;
        // 当left指针遍历到大于区时停
        while (left < more) {
            if (nums[left] < standard) {
                // 如果当前数小于标准, 则将小于区右边界上的数和当前数交换, 小于区右扩, left++
                swap(nums, ++less, left++);
            } else if (nums[left] > standard) {
                // 如果当前数大于基准, 则将大于区左边界上的数和当前数交换, 大于区左扩, left不动
                swap(nums, --more, left);
            } else {
                // 如果是等于, 则直接跳过
                left++;
            }
        }
        // 最后将大于区的左边界和基准数交换
        swap(nums, more, right);
        // 返回等于区的左边界和右边界
        return new int[]{less + 1, more};
    }

    public static void swap(@NotNull int[] nums, int from, int to) {
        int tmp = nums[from];
        nums[from] = nums[to];
        nums[to] = tmp;
    }

}
