package com.course.testng;

import org.testng.annotations.Test;
//异常测试
public class ExpectedException {
    @Test(expectedExceptions = RuntimeException.class)
    public void ex(){
        System.out.println("这是一个失败的异常测试");
    }

    //成功的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void Ex(){
        System.out.println("成功的异常测试");
        throw new RuntimeException();   //抛出一个异常
    }
}
