package com.xiaoxiaoyi.tree.orderedlist.stack;

import junit.framework.TestCase;

public class ImplementStackWithQueueTest extends TestCase {

    public void testImplementStackWithQueue() {
        ImplementStackWithQueue.MyStack<Object> myStack = new ImplementStackWithQueue.MyStack<>();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
    }

}
