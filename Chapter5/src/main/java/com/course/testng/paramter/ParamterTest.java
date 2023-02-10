package com.course.testng.paramter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//参数化测试
public class ParamterTest {

    @Test
    @Parameters({"name","age"})     //参数化测试
    public void test(String name,int age){
        System.out.println("name:"+name+",age"+age);
    }
}
