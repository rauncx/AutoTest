package com.course.testng.MultiThread;

import org.testng.annotations.Test;

//多线程测试
public class MultiThreadTest {

    @Test(invocationCount = 10,threadPoolSize = 3)
    public void test(){
        System.out.println(1234);
        System.out.printf("%s%n",Thread.currentThread().getId());
    }
}
