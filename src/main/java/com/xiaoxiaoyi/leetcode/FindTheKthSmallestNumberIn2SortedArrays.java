package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 从2个有序数组中找到第k小的数
 */
public class FindTheKthSmallestNumberIn2SortedArrays {

    public static int getKth(int[] arr1, int[] arr2, int k) {
        assert arr1 != null && arr1.length > 0 && arr2 != null && arr2.length > 0 && k > 0 && k <= arr1.length + arr2.length;
        int m = arr1.length;
        int n = arr2.length;
        if (k <= Math.min(m, n)) {
            // 情况1: 当k的范围在1~较短的数组长度之间时, 取出两个数组的前k个数求上中位数即可
            int[] arr3 = new int[k];
            int[] arr4 = new int[k];
            System.arraycopy(arr1, 0, arr3, 0, k);
            System.arraycopy(arr2, 0, arr4, 0, k);
            return getUpperMedian(arr3, arr4);
        } else if (k <= Math.max(m, n)) {
            /*
            m: 小数组的长度, n: 大数组的长度
            情况2: k大于小数组长度小于等于大数组长度 k > m && k <= n
            判断哪些数不可能: 小数组的第1个数如果大于大数组的第k-1个数, 那么它就是答案
            小数组的第m个数如果大于数组的第k-m个数, 那么它就是答案
            所以小数组中的所有数都可能
            大数组的前k - m - 1个数都不可能, 因为最多到k-1
            大数组的第k+1个到最后也不可能, 因为至少是k+1
            所以大数组剩下的可能的数有n - (k - m - 1) - (n - k)
             = m + 1个, 比小数组中可能的数多了1个, 所以手动判断大数组的第k-m个是不是结果
             如果它>小数组的最后1个数, 那么它是答案, 否则不是, 然后大数组就可以剩下m个数去和小数组的m个数求上中位数了
             此时前面已经淘汰了k - m个数, 求出的上中位数就是第k小的数
             */
            if (m > n) {
                // 保证arr1指向小数组, arr2指向大数组
                int[] tmp = arr1;
                arr1 = arr2;
                arr2 = tmp;
                // m为小数组的长度
                m = arr1.length;
            }
            if (arr2[k - m - 1] > arr1[m - 1]) {
                // 如果大数组的k - m个数大于小数组的最后一个数, 那么它就是答案
                return arr2[k - m - 1];
            }
            int[] arr3 = new int[m];
            System.arraycopy(arr2, k - m, arr3, 0, m);
            // 将小数组的全部和大数组的剩余可能部分拿去求上中位数
            return getUpperMedian(arr1, arr3);
        } else {
            /*
            m: arr1的长度, n: arr2的长度
            情况3: k大于大数组长度小于等于两数组长度之和
            考虑哪些数字不可能, arr1的前k - n - 1个数不可能
            考虑arr1的第k - n - 1个数, 最好情况下它干过了arr2中的所有数字
            也就是第k-1小, 到不了第k小, 那么它前面的数就更不可能
            同理, arr2中的第k - m - 1个数不可能, 其前面的所有数字也不可能
            那么arr1经过淘汰就还剩m - k + n + 1个数, arr2还剩n - k + m + 1个数
            再次手动判断arr1的第k - n个数和arr2的第k - m个数是不是答案
            如果都不是, 则arr1经过淘汰就还剩m - k + n个数
            大数组还剩n - k + m个数, 可以知道两个数组剩余长度相等
            可以求两数组剩余部分的上中位数, 即剩余部分中的第m - k + n小的数
            加上前面淘汰的部分即为(m - k + n) + (k - n) + (k - m) = k
            所以剩余部分的第m - k + n小的数就是两数组中第k小的数
             */
            if (arr1[k - n - 1] > arr2[n - 1]) {
                // 如果arr1中的第k-n个数(对应下标k-n-1)大于了arr2的最后一个数(n-1), 那么它就是第k小的数
                return arr1[k - n - 1];
            }
            // arr1的第k-n个数不是答案, 直接排除, 看arr2的第k-m个数
            if (arr2[k - m - 1] > arr1[m - 1]) {
                // 如果arr2的第k-m个数大于了arr1的所有数, 则它就是答案
                return arr2[k - m - 1];
            }
            // 如果2个都不是答案, 那么取两数组后面可能的部分计算上中位数
            int length = m + n - k;
            int[] arr3 = new int[length];
            int[] arr4 = new int[length];
            System.arraycopy(arr1, k - n, arr3, 0, length);
            System.arraycopy(arr2, k - m, arr4, 0, length);
            return getUpperMedian(arr3, arr4);
        }
    }

    /**
     * 求2个有序数组的上中位数, 要求2个数组的长度大于0且一样
     */
    public static int getUpperMedian(int[] arr1, int[] arr2) {
        int n = arr1.length;
        if (n == 1) {
            return Math.min(arr1[0], arr2[0]);
        }
        if (n == 0) {
            return 0;
        }
        // 普遍情况
        int mid1 = arr1[(n - 1) >> 1];
        int mid2 = arr2[(n - 1) >> 1];
        if (mid1 == mid2) {
            return mid1;
        }
        if ((n & 1) == 1) {
            if (mid1 > mid2) {
                /*
                因为mid1肯定大于前面n/2个数, 又大于mid2, 也大于mid2前面的n/2个数, 所以mid1及其后面的所有数字都不可能是结果
                因为mid2<mid1, 在mid2打不过mid1的情况下还是可能打过mid1前面的n/2个数的, 所以mid2及其后面的数字都有可能是结果
                此时arr1剩余的可能数字有n/2个, arr2剩余的数字有n/2+1个, 递归调用的前提是2个数组长度相等
                所以此时手动判断mid2是不是结果, 是就返回, 不是则排除mid2再递归
                 */
                if (mid2 > arr1[((n - 1) >> 1) - 1]) {
                    return mid2;
                }
                int[] arr3 = new int[n >> 1];
                int[] arr4 = new int[n >> 1];
                System.arraycopy(arr1, 0, arr3, 0, n >> 1);
                System.arraycopy(arr2, (n >> 1) + 1, arr4, 0, n >> 1);
                return getUpperMedian(arr3, arr4);
            } else {
                if (mid1 > arr2[((n - 1) >> 1) - 1]) {
                    return mid1;
                }
                int[] arr3 = new int[n >> 1];
                int[] arr4 = new int[n >> 1];
                System.arraycopy(arr1, (n + 1) >> 1, arr3, 0, n >> 1);
                System.arraycopy(arr2, 0, arr4, 0, n >> 1);
                return getUpperMedian(arr3, arr4);
            }
        } else {
            // 数组长度为偶数
            int[] arr3 = new int[n >> 1];
            int[] arr4 = new int[n >> 1];
            if (mid1 > mid2) {
                /*
                如果mid1>mid2, 那么mid1会压自己前面的n/2-1个数, 还会压住arr2的前n/2个数, 那么它自己有可能是结果
                它后面的就不可能是结果了
                mid2<mid1,  所以mid2最好情况也就干过自己和mid1前面的, 到不了上中位数的位置, 所以mid2及其之前的所有数字都不可能
                 */
                System.arraycopy(arr1, 0, arr3, 0, n >> 1);
                System.arraycopy(arr2, n >> 1, arr4, 0, n >> 1);
            } else {
                System.arraycopy(arr1, n >> 1, arr3, 0, n >> 1);
                System.arraycopy(arr2, 0, arr4, 0, n >> 1);
            }
            return getUpperMedian(arr3, arr4);
        }
    }

    public static int outerSort(int[] arr1, int[] arr2, int k) {
        assert arr1 != null && arr1.length > 0 && arr2 != null && arr2.length > 0;
        int pointer1 = 0, pointer2 = 0;
        // k - 2是因为一开始就指向了两个数组中最小的2个数
        for (int i = 0; i < k - 1; i++) {
            if (arr1[pointer1] > arr2[pointer2]) {
                pointer2++;
            } else {
                pointer1++;
            }
        }
        return Math.min(pointer1 >= arr1.length ? Integer.MAX_VALUE : arr1[pointer1],
                pointer2 >= arr2.length ? Integer.MAX_VALUE : arr2[pointer2]);
    }

    public static int binarySearchK(int[] arr1, int[] arr2, int k) {
        // 先假设第k小的数在arr1中
        int left = 0, right = arr1.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            // 去arr2里找它前面压了多少个数
            int find = binarySearch(arr2, arr1[mid]);
            if (mid + find == k - 1) {
                // 如果它前面压的数的数量find+mid刚好等于k-1, 那么说明它就是第k小的数
                return arr1[mid];
            } else if (mid + find > k - 1) {
                // 如果前面压的数量大于了k - 1, 说明应该去二分左半边
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // 如果arr1中未找到, 则去arr2里找
        left = 0;
        right = arr2.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int find = binarySearch(arr1, arr2[mid]);
            if (mid + find == k - 1) {
                return arr2[mid];
            } else if (mid + find > k - 1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找大于num的第1个数所在位置
     */
    public static int binarySearch(int[] arr, int num) {
        int left = 0, right = arr.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr[mid] > num) {
                // 只有在找到一个大于等于num的数时, 它才可能是结果
                ans = mid;
                // 去左边看看还有没有解
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // 如果ans为-1, 则说明arr中的所有数都比num小
        return ans == -1 ? arr.length : ans;
    }
}
