package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 */
public class GluttonousSnake {

    public static class Info {
        /*
        没用能力所能到达的最长长度
         */
        public int no;
        /*
        用1次能力所能到达的最长长度
         */
        public int yes;

        Info(int no, int yes) {
            this.no = no;
            this.yes = yes;
        }
    }

    /**
     * 要求蛇只能从地图最左边进入, 每个位置的值
     * 规则是贪吃蛇一开始长度为0, 长度<0时死亡
     * 贪吃蛇到达matrix[row][col]位置时, 长度会加上该位置的数字
     * 蛇有1次机会可以让当前位置的数变为相反数
     *
     * @param map 地图
     * @return 贪吃蛇所能到达的最大长度
     */
    public static int maximumLength(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                // 因为每个位置都可能产生最优解, 所以每个位置都要尝试
                Info info = trialProcess(map, row, col);
                ans = Math.max(ans, Math.max(info.no, info.yes));
            }
        }
        return ans;
    }

    public static Info trialProcess(int[][] map, int row, int col) {
        // base case, 当前位置在最左列时(col == 0)
        if (col == 0) {
            return new Info(map[row][col], -map[row][col]);
        }
        // 当前位置之前的2个信息
        int preNo = -1;
        int preYes = -1;
        // 有3种方式到达当前位置, 1种是从当前位置的左上角
        if (row > 0) {
            // row > 0代表当前位置存在左上角
            Info leftUpper = trialProcess(map, row - 1, col - 1);
            if (leftUpper.no >= 0) {
                preNo = leftUpper.no;
            }
            if (leftUpper.yes >= 0) {
                preYes = leftUpper.yes;
            }
        }
        // 第2种是从左边到达当前位置
        Info left = trialProcess(map, row, col - 1);
        if (left.no >= 0) {
            preNo = Math.max(preNo, left.no);
        }
        if (left.yes >= 0) {
            preYes = Math.max(preYes, left.yes);
        }
        // 第3种是从左下角
        if (row < map.length - 1) {
            // row < map.length - 1代表当前位置存在左下角
            Info leftDown = trialProcess(map, row + 1, col - 1);
            if (leftDown.no >= 0) {
                preNo = Math.max(preNo, leftDown.no);
            }
            if (leftDown.yes >= 0) {
                preYes = Math.max(preYes, leftDown.yes);
            }
        }
        /*
        到这里之前更新的是当前位置之前的所有可能中的最优情况, 接下来更新当前位置的最优
        当前位置可以在前面没有死亡且没有用过能力的情况下选择用/不用能力
        也可以在前面用过能力且没有死亡的情况下选择不用能力
         */
        int no = -1;
        int yes = -1;
        if (preNo >= 0) {
            // 如果当前选择不用能力
            no = preNo + map[row][col];
            // 如果当前选择用能力
            yes = preNo - map[row][col];
        }
        if (preYes >= 0) {
            // 如果前面用过能力且没有死亡, 当前位置只能保持用过的状态且不能再使用能力了
            yes = Math.max(yes, preYes + map[row][col]);
        }
        return new Info(no, yes);
    }


    public static int maximumLength2(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        int m = map.length, n = map[0].length;
        Info[][] cache = new Info[m][n];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                // 因为每个位置都可能产生最优解, 所以每个位置都要尝试
                Info info = trialProcess2(map, row, col, cache);
                ans = Math.max(ans, Math.max(info.no, info.yes));
            }
        }
        return ans;
    }

    public static Info trialProcess2(int[][] map, int row, int col, Info[][] cache) {
        // 判断缓存有没有命中
        if (cache[row][col] == null) {
            // 如果缓存未命中, base case, 当前位置在最左列时(col == 0)
            if (col == 0) {
                cache[row][col] = new Info(map[row][col], -map[row][col]);
            } else {
                // 当前位置之前的2个信息
                int preNo = -1;
                int preYes = -1;
                // 有3种方式到达当前位置, 1种是从当前位置的左上角
                if (row > 0) {
                    // row > 0代表当前位置存在左上角
                    Info leftUpper = trialProcess2(map, row - 1, col - 1, cache);
                    if (leftUpper.no >= 0) {
                        preNo = leftUpper.no;
                    }
                    if (leftUpper.yes >= 0) {
                        preYes = leftUpper.yes;
                    }
                }
                // 第2种是从左边到达当前位置
                Info left = trialProcess2(map, row, col - 1, cache);
                if (left.no >= 0) {
                    preNo = Math.max(preNo, left.no);
                }
                if (left.yes >= 0) {
                    preYes = Math.max(preYes, left.yes);
                }
                // 第3种是从左下角
                if (row < map.length - 1) {
                    // row < map.length - 1代表当前位置存在左下角
                    Info leftDown = trialProcess2(map, row + 1, col - 1, cache);
                    if (leftDown.no >= 0) {
                        preNo = Math.max(preNo, leftDown.no);
                    }
                    if (leftDown.yes >= 0) {
                        preYes = Math.max(preYes, leftDown.yes);
                    }
                }
                /*
                到这里之前更新的是当前位置之前的所有可能中的最优情况, 接下来更新当前位置的最优
                当前位置可以在前面没有死亡且没有用过能力的情况下选择用/不用能力
                也可以在前面用过能力且没有死亡的情况下选择不用能力
                 */
                int no = -1;
                int yes = -1;
                if (preNo >= 0) {
                    // 如果当前选择不用能力
                    no = preNo + map[row][col];
                    // 如果当前选择用能力
                    yes = preNo - map[row][col];
                }
                if (preYes >= 0) {
                    // 如果前面用过能力且没有死亡, 当前位置只能保持用过的状态且不能再使用能力了
                    yes = Math.max(yes, preYes + map[row][col]);
                }
                cache[row][col] = new Info(no, yes);
            }
        }
        return cache[row][col];
    }
}
