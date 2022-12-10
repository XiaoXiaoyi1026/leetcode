package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

public class PrintSystemFileDirectoryTest extends TestCase {

    public void testPrintSystemFileDirectory() {
        // 使用\\是因为java中\是转义字符, 所以\\才相当于\
        String[] paths = {"a\\b\\c", "d\\e\\f", "a\\d\\xf"};
        PrintSystemFileDirectory.printPaths(paths);
    }

}
