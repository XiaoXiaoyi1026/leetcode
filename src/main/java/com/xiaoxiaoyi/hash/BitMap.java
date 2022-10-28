package com.xiaoxiaoyi.hash;

/**
 * @author xiaoxiaoyi
 * 位图数据结构
 */
public class BitMap {

    /**
     * 存储位信息
     */
    private final int[] bits;
    /**
     * 位图大小
     */
    private final int size;

    /**
     * 位图默认大小为4*32 = 128位
     */
    BitMap() {
        bits = new int[4];
        size = 128;
    }

    /**
     * @param size 位图大小
     */
    BitMap(int size) {
        // 为了针对size刚好是32的倍数的情况，所以要-1
        bits = new int[(size - 1) / 32 + 1];
        this.size = size * 32;
    }

    /**
     * 获取index位置上的位信息
     *
     * @param index 位置
     * @return 位信息
     */
    public int getBit(int index) {
        // index从1开始
        if (--index < 0 || index >= size) {
            // 如果越界了，则直接返回-1
            return -1;
        }
        // 计算位在哪个int数上
        int numIndex = index / 32;
        // 计算位在数的那个bit上
        int bitIndex = index % 32;
        // 返回那一位bit的具体值
        return (bits[numIndex] >> (31 - bitIndex)) & 1;
    }

    /**
     * 将第index位设为1
     *
     * @param index 位置
     */
    public void setBit(int index) {
        // index从1开始
        if (--index < 0 || index >= size) {
            // 如果越界了，则直接返回
            return;
        }
        // 计算位在哪个int数上
        int numIndex = index / 32;
        // 计算位在数的那个bit上
        int bitIndex = index % 32;
        // 将对应bit位变成1
        bits[numIndex] |= (1 << (31 - bitIndex));
    }

}
