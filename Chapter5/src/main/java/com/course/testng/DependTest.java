package com.course.testng;

import org.testng.annotations.Test;

//依赖测试
public class DependTest {
    @Test
    public void test1(){
        System.out.println("test");
    }

    //被依赖的方法如果执行失败了，那么这个依赖的方法会忽略不执行
    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.println("test2");
    }
}
