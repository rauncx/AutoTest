package com.course.testng;

import org.testng.annotations.Test;

//忽略测试
public class lgnoreTest {

    @Test
    public void IgnoreTest(){
        System.out.println("ignore1 执行");
    }

    @Test(enabled = false)
    public void ignoretest(){
        System.out.println("ig2 执行");
    }
}
