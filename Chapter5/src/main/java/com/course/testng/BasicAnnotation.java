package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {
    // 最基本的注解，用来把方法标记为测试的一部分
    @Test
    public void testCase1(){
        System.out.println("test测试用例1");

    }

    @BeforeMethod
    public void BeforeMethod(){
        System.out.println("bef这是在测试方法之前运行的");
    }

    @AfterMethod
    public void AfterMethod(){
        System.out.println("Af这是中测试方法之后运行的");
    }

    @BeforeClass
    public void BeforeClass(){
        System.out.println("beclass这是在类运行之前运行的方法");
    }

    @AfterClass
    public void AfterClass(){
        System.out.println("AfClass这是在类运行后运行的方法");
    }

    @BeforeSuite
    public void BeforeSuite(){
        System.out.println("beforesuite测试套件");
    }

    @AfterSuite
    public void AfterSuite(){
        System.out.println("Aftersuite测试套件");
    }
}
