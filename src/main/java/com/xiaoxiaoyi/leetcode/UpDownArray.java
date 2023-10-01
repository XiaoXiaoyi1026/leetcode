package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * 升降数组
 * 假设一个数组arr为[1, 8, 6, 7]
 * 满足arr[0] < arr[1] > arr[2] < arr[3]
 * 称该数组为升降数组
 * 如果一个数组天然满足升降数组, 则返回0
 * 否则, 返回该数组在只能一个数的情况下, 能达成的升降数组个数
 */
public class UpDownArray {

    /**
     * 暴力方法, 为了验证
     *
     * @param arr 原始数组
     * @return 数组达不达标
     */
    public static int upDownArray1(int[] arr) {
        if (arr == null || arr.length < 2 || check(arr)) {
            return 0;
        }
        int n = arr.length;
        int ans = 0;
        for (int remove = 0; remove < n; remove++) {
            int[] newArr = remove(arr, remove);
            if (check(newArr)) {
                ans++;
            }
        }
        return ans;
    }

    public static int upDownArray2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length;
        boolean[] rightUp = new boolean[n];
        boolean[] rightDown = new boolean[n];
        rightUp[n - 1] = true;
        rightDown[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            rightUp[i] = arr[i] < arr[i + 1] && rightDown[i + 1];
            rightDown[i] = arr[i] > arr[i + 1] && rightUp[i + 1];
        }
        if (rightUp[0] || rightDown[0]) {
            return 0;
        }
        boolean leftUp = true;
        boolean leftDown = true;
        int ans = (rightUp[1] || rightDown[1]) ? 1 : 0;
        boolean tmp;
        for (int cur = 1, left = 0, right = 2; cur < n - 1; cur++, left++, right++) {
            if ((arr[left] < arr[right] && leftUp && rightDown[right]) || (arr[left] > arr[right] && leftDown && rightUp[right])) {
                ans++;
            }
            tmp = leftUp;
            leftUp = arr[cur] < arr[left] && leftDown;
            leftDown = arr[cur] > arr[left] && tmp;
        }
        ans += (leftUp || leftDown) ? 1 : 0;
        return ans;
    }

    @Contract(pure = true)
    private static boolean check(@NotNull int[] arr) {
        if (arr[0] == arr[1]) {
            return false;
        }
        boolean up = arr[0] < arr[1];
        for (int i = 1; i < arr.length - 1; i++) {
            if ((up && arr[i] <= arr[i + 1]) || (!up && arr[i] >= arr[i + 1])) {
                return false;
            }
            up = !up;
        }
        return true;
    }

    @NotNull
    private static int[] remove(@NotNull int[] arr, int i) {
        int[] newArr = new int[arr.length - 1];
        System.arraycopy(arr, 0, newArr, 0, i);
        System.arraycopy(arr, i + 1, newArr, i, arr.length - i - 1);
        return newArr;
    }

    public static void main(String[] args) {
        int testTimes = 100;
        int length = 20;
        int max = 40;
        int[] arr;
        int ans1;
        int ans2;
        System.out.println("测试开始!!!");
        for (int i = 0; i < testTimes; i++) {
            arr = RandomUtils.array(length, max, true);
            ans1 = upDownArray1(arr);
            ans2 = upDownArray2(arr);
            if (ans1 != ans2) {
                System.out.println(Arrays.toString(arr));
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束!!!");
    }

}
