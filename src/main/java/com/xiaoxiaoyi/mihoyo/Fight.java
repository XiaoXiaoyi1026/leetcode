package com.xiaoxiaoyi.mihoyo;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 打架
 * @date 3/17/2023 3:57 PM
 */
@Slf4j(topic = "c.Fight")
public class Fight {

    private static final Scanner SHURU = new Scanner(System.in);

    private static int huiHeShu = 0;

    public static void main(String[] args) {
        int n = SHURU.nextInt();
        for (int i = 0; i < n; i ++) {
            int shenMing = SHURU.nextInt();
            int gongji = SHURU.nextInt();
            int fangYu = SHURU.nextInt();
            int suDu = SHURU.nextInt();
            Human v2v = new V2V(shenMing, gongji, fangYu, suDu);
            shenMing = SHURU.nextInt();
            gongji = SHURU.nextInt();
            fangYu = SHURU.nextInt();
            suDu = SHURU.nextInt();
            Human qianJie = new QianJie(shenMing, gongji, fangYu, suDu);
            Human yingJia;
            huiHeShu = 0;
            if (v2v.suDu > qianJie.suDu) {
                yingJia = fight(v2v, qianJie);
            } else {
                yingJia = fight(qianJie, v2v);
            }
            if (yingJia instanceof QianJie) {
                System.out.println("qian jie win");
            } else {
                System.out.println("v2v win");
            }
        }
    }

    public static Human fight(@NotNull Human xianShou, Human houShou) {
        V2V v2v;
        QianJie qianJie;
        if (xianShou instanceof V2V) {
            v2v = (V2V) xianShou;
            qianJie = (QianJie) houShou;
        } else {
            v2v = (V2V) houShou;
            qianJie = (QianJie) xianShou;
        }
        while (xianShou.shengMing > 0 && houShou.shengMing > 0) {
            huiHeShu++;
            System.out.println("回合: " + huiHeShu);
            log.debug("v2v 生命值: {}", v2v.shengMing);
            log.debug("千劫生命值: {}", qianJie.shengMing);
            if (v2v.shengMing < 31 && !v2v.beiDongChuFa) {
                v2v.beiDong(qianJie);
            }
            xianShou.xingDong(houShou, huiHeShu);
            if (xianShou.shengMing < 0) {
                return houShou;
            }
            if (houShou.shengMing > 0) {
                houShou.xingDong(xianShou, huiHeShu);
            } else {
                return xianShou;
            }
        }
        return houShou;
    }
}

@Slf4j(topic = "c.Human")
abstract class Human {
    int shengMing;
    int gongJi;
    int fangYu;
    int suDu;

    Human(int shengMing, int gongJi, int fangYu, int suDu) {
        this.shengMing = shengMing;
        this.gongJi = gongJi;
        this.fangYu = fangYu;
        this.suDu = suDu;
    }

    public void puGong(@NotNull Human target) {
        int shangHai = this.gongJi - target.fangYu;
        shangHai = Math.max(0, shangHai);
        target.shengMing -= shangHai;
        log.debug("普攻造成{}点伤害", shangHai);
    }

    public abstract void zhuDong(Human target);

    public abstract void beiDong(Human target);

    public abstract void xingDong(Human target, int huiHeShu);
}

@Slf4j(topic = "c.V2V")
class V2V extends Human {

    boolean beiDongChuFa = false;

    V2V(int shengMing, int gongJi, int fangYu, int suDu) {
        super(shengMing, gongJi, fangYu, suDu);
    }

    @Override
    public void zhuDong(@NotNull Human target) {
        QianJie qianJie = (QianJie) target;
        int shangHai = this.gongJi - target.fangYu;
        qianJie.shengMing -= shangHai;
        qianJie.hunLuan = true;
        log.debug("v2v 使用主动技能, 对千劫造成{}点伤害", shangHai);
    }

    @Override
    public void beiDong(@NotNull Human target) {
        this.shengMing += 20;
        target.shengMing += 20;
        int chuShiGongJi = this.gongJi;
        this.gongJi += 15;
        beiDongChuFa = true;
        log.debug("v2v 被动触发, 攻击力{} -> {}", chuShiGongJi, this.gongJi);
    }

    @Override
    public void xingDong(Human target, int huiHeShu) {
        log.debug("v2v 行动");
        if (huiHeShu % 3 != 0) {
            this.puGong(target);
        } else {
            this.zhuDong(target);
        }
    }
}

@Slf4j(topic = "c.QianJie")
class QianJie extends Human {

    int chuShiShengMing;

    int chuShiGongJi;

    public boolean hunLuan;

    public boolean xiuXi;

    QianJie(int shengMing, int gongJi, int fangYu, int suDu) {
        super(shengMing, gongJi, fangYu, suDu);
        chuShiShengMing = shengMing;
        chuShiGongJi = gongJi;
        hunLuan = false;
    }

    @Override
    public void zhuDong(@NotNull Human target) {
        this.shengMing -= 10;
        int shangHai = 45 - target.fangYu + 20;
        target.shengMing -= Math.max(0, shangHai);
        xiuXi = true;
        log.debug("千劫主动技能触发, 对v2v造成{}点伤害", shangHai);
    }

    @Override
    public void beiDong(Human target) {
        int zhiQian = this.gongJi;
        gongJi = (int) (chuShiGongJi + Math.floor((chuShiShengMing - this.shengMing) / 5.0));
        log.debug("千劫被动触发, 攻击力由{} -> {}", zhiQian, gongJi);
    }

    @Override
    public void xingDong(Human target, int huiHeShu) {
        log.debug("千劫行动");
        beiDong(target);
        if (!xiuXi) {
            if (hunLuan) {
                log.debug("千劫混乱了!将攻击目标改为了自己!");
                this.puGong(this);
                hunLuan = false;
                return;
            }
            if (huiHeShu % 3 == 0) {
                if (this.shengMing >= 11) {
                    zhuDong(target);
                } else {
                    puGong(target);
                }
            } else {
                puGong(target);
            }
        } else {
            log.debug("千劫休息了!");
            xiuXi = false;
        }
    }
}


