package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 二分查找
 */
public class BinarySearch {

    public static int equals(int[] arr, int target) {
        return equals(arr, 0, arr.length - 1, target);
    }

    public static int equals(int[] arr, int begin, int end, int target) {
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return -1;
    }

    public static int lessOrEquals(int[] arr, int border) {
        return lessOrEquals(arr, 0, arr.length - 1, border);
    }

    public static int lessOrEquals(int[] arr, int begin, int end, int border) {
        int res = -1;
        while (begin <= end) {
            int mid = (begin + end) >> 1;
            if (arr[mid] > border) {
                end = mid - 1;
            } else {
                res = mid;
                begin = mid + 1;
            }
        }
        return res;
    }

    public static int greatOrEquals(int[] arr, int border) {
        return greatOrEquals(arr, 0, arr.length - 1, border);
    }

    public static int greatOrEquals(int[] arr, int begin, int end, int border) {
        int res = -1;
        while (begin <= end) {
            int mid = (begin + end) >> 1;
            if (arr[mid] < border) {
                begin = mid + 1;
            } else {
                res = mid;
                end = mid - 1;
            }
        }
        return res;
    }

}
