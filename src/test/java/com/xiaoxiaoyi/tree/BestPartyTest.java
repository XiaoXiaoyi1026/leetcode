package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

public class BestPartyTest extends TestCase {

    public void testBestParty() {
        BestParty.Employee boss = new BestParty.Employee(10);
        BestParty.Employee employee1 = new BestParty.Employee(20);
        BestParty.Employee employee2 = new BestParty.Employee(30);
        BestParty.Employee employee3 = new BestParty.Employee(60);
        BestParty.Employee employee4 = new BestParty.Employee(30);
        BestParty.Employee employee5 = new BestParty.Employee(50);
        BestParty.Employee employee6 = new BestParty.Employee(10);
        boss.getLowLevelEmployees().add(employee1);
        boss.getLowLevelEmployees().add(employee2);
        employee1.getLowLevelEmployees().add(employee3);
        employee1.getLowLevelEmployees().add(employee4);
        employee2.getLowLevelEmployees().add(employee5);
        employee2.getLowLevelEmployees().add(employee6);
        System.out.println(BestParty.maxHappy(boss));
    }

}
