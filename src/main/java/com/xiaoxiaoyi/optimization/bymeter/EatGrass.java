package com.xiaoxiaoyi.optimization.bymeter;

/**
 * @author xiaoxiaoyi
 * 吃草, 分先手和后手, 每次只能吃4的某次方个草, 谁刚好吃完谁赢
 */
public class EatGrass {

    public static String eatGrass(int grass) {
        if (grass < 0) {
            return "非法";
        }
        // 0     1   2    3   4
        // 后手 先手 后手 先手 先手
        if (grass < 5) {
            // 草的数量小于5个的情况
            return (grass == 0 || grass == 2) ? "后手" : "先手";
        }
        // 先手决定吃的草
        int base = 1;
        while (base <= grass) {
            if ("后手".equals(eatGrass(grass - base))) {
                // 子过程中的后手代表当前过程中的先手
                return "先手";
            }
            if (base > grass / 4) {
                // 防止base溢出
                break;
            }
            // 继续尝试下一种可能性
            base *= 4;
        }
        // 所有情况下都无法先手赢, 则后手赢
        return "后手";
    }

    public static String bestSolution(int grass) {
        if (grass < 0) {
            return "非法";
        }
        int res = grass % 5;
        return (res == 0) || (res == 2) ? "后手" : "先手";
    }

}
