package com.xiaoxiaoyi;

import com.xiaoxiaoyi.graph.EdgeComparator;
import junit.framework.TestCase;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class SingletonDoubleCheckLockTest extends TestCase {

    private static final Integer COUNT = 100;
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(COUNT);

    private static EdgeComparator edgeComparator;

    /**
     * test for singleton double check lock
     */
    public void testSingletonDoubleCheckLock() {
        for (int i = 0; i < COUNT; i++) {
            new Thread(() ->{
                try {
                    Thread.sleep(new Random().nextInt(10) * 1000);
                    COUNT_DOWN_LATCH.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                edgeComparator = EdgeComparator.getMyComparator();
                System.out.println(edgeComparator);
            }, "thread-" +i).start();
            COUNT_DOWN_LATCH.countDown();
        }
    }

}
