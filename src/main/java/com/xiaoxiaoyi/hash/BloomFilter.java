package com.xiaoxiaoyi.hash;

/**
 * @author xiaoxiaoyi
 * 布隆过滤器 类似于黑名单系统，没有从黑名单中删除元素操作
 * 布隆过滤器使用一个长度为m的bitmap来映射每个元素的hash，检索时对元素进行hash，然后去bitmap里判断即可
 * 可能会出现不是黑名单里的被误判为黑名单，但不会出现是黑名单的判断为不是黑名单
 * 参数：n 样本量 p 失误率 m bitmap长度 k hash次数
 * 设计时，只需要考虑样本量n和失误率p
 * m = -(n*lnp)/((ln2)^2)) 向上取整(理论值)
 * k = ln2 * m/n 约等于 0.7 * m/n 向上取整(理论值)
 * 实际失误率p = (1 - e^((-n * k真) / m真))^k真
 */
public class BloomFilter {

    /**
     * 样本量
     */
    public long n;
    /**
     * 预期误差率
     */
    public double p;
    /**
     * bitmap大小
     */
    public float m;
    /**
     * hash次数
     */
    public int k;
    /**
     * 实际误差率
     */
    public double p1;

    BloomFilter(long n, double p) {
        this.n = n;
        this.p = p;
        this.m = getM(n, p);
        this.k = getK(n, m);
        this.p1 = getP(n, m, k);
    }

    private float getM(long n, double p) {
        return -1 * ((float) Math.ceil((n * Math.log(p)) / (Math.pow(Math.log(2), 2))));
    }

    private int getK(long n, float m) {
        return (int) Math.ceil(Math.log(2) * (m / n));
    }

    private double getP(long n, float m, int k) {
        return Math.pow(1 - Math.pow(Math.exp(1), -1 * (n * k) / m), k);
    }
}
