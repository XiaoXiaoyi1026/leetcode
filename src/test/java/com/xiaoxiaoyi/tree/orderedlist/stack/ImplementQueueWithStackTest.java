package com.xiaoxiaoyi.tree.orderedlist.stack;

import junit.framework.TestCase;

public class ImplementQueueWithStackTest extends TestCase {

    public void testImplementQueueWithStack() {
        ImplementQueueWithStack.MyQueue<Integer> myQueue = new ImplementQueueWithStack.MyQueue<>();
        myQueue.add(1);
        myQueue.add(2);
        myQueue.add(3);
        myQueue.add(4);
        myQueue.add(5);
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
    }

}
