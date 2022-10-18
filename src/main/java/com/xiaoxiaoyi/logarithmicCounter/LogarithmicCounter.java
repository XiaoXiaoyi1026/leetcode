package com.xiaoxiaoyi.logarithmicCounter;

/**
 * @author xiaoxiaoyi
 */
public class LogarithmicCounter {

    private static final String ASC = "ASC";
    private static final String DESC = "DESC";

    /**
     * 判断一个数组是否有序
     *
     * @param arr 数组
     * @param method 排序方式
     * @return 是否有序
     */
    public static boolean isSorted(int[] arr, String method) {
        if (ASC.equals(method)) {
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] > arr[i]) {
                    return false;
                }
            }
        } else if (DESC.equals(method)) {
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] < arr[i]) {
                    return false;
                }
            }
        }
        return true;
    }

}
