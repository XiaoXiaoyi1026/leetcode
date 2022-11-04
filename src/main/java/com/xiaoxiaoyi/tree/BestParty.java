package com.xiaoxiaoyi.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoxiaoyi
 * 树形DP, 一个公司将要开展宴会, 规定一个员工有0个或多个下级, 基层员工无下级, 非基层员工至少有1个下级
 * boss没有上级, 其余员工有且只有1个上级, 每个员工都有一个快乐值, 规定如果上级来了, 那么他的所有下级都不能来
 * 问如何邀请员工参加宴会能使宴会的总体快乐值最大
 */
public class BestParty {

    /**
     * 员工
     */
    public static class Employee {
        /**
         * 快乐值
         */
        private final int happy;
        /**
         * 下级员工集合
         */
        private final List<Employee> lowLevelEmployees;

        Employee(int happy) {
            this.happy = happy;
            this.lowLevelEmployees = new ArrayList<>();
        }

        public int getHappy() {
            return happy;
        }

        public List<Employee> getLowLevelEmployees() {
            return lowLevelEmployees;
        }
    }

    /**
     * 每个节点为根的树要返回的信息
     * 即该员工参加/不参加宴会的最大快乐值
     */
    private static class Info {
        /**
         * 该节点参加宴会的最大快乐值
         */
        private final int comeMaxHappy;
        /**
         * 该节点不参加宴会的最大快乐值
         */
        private final int notComeMaxHappy;

        Info(int comeMaxHappy, int notComeMaxHappy) {
            this.comeMaxHappy = comeMaxHappy;
            this.notComeMaxHappy = notComeMaxHappy;
        }

        public int getComeMaxHappy() {
            return comeMaxHappy;
        }

        public int getNotComeMaxHappy() {
            return notComeMaxHappy;
        }
    }

    /**
     *
     * @param boss 公司老板
     * @return 老板来/不来的宴会最大快乐值
     */
    public static int maxHappy(Employee boss) {
        Info info = process(boss);
        return Math.max(info.getComeMaxHappy(), info.getNotComeMaxHappy());
    }

    /**
     *
     * @param employee 当前员工
     * @return 当前员工来/不来的最大快乐值
     */
    private static Info process(Employee employee) {
        if (employee.getLowLevelEmployees().isEmpty()) {
            // 如果该员工没有下级员工, 直接返回信息
            return new Info(employee.getHappy(), 0);
        }
        List<Employee> lowLevelEmployees = employee.getLowLevelEmployees();
        // 参加宴会的情况下的最大快乐值
        int comeMaxHappy = employee.getHappy();
        // 不参加宴会的情况下的最大快乐值
        int notComeMaxHappy = 0;
        for (Employee lowLevelEmployee : lowLevelEmployees) {
            // 获取其每一个下级参加/不参加宴会的最大快乐值
            Info info = process(lowLevelEmployee);
            // 如果他要来, 则下级不能来
            comeMaxHappy += info.getNotComeMaxHappy();
            // 如果他不来, 则下级可来可不来
            notComeMaxHappy += Math.max(info.getComeMaxHappy(), info.getNotComeMaxHappy());
        }
        return new Info(comeMaxHappy, notComeMaxHappy);
    }

}
