package com.course.testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//参数化参数
public class DataProviderTest {
    @Test(dataProvider = "data")
    @Parameters({"name","age"})
    public void test(String name,int age){
        System.out.println("name:"+name+",age"+age);
    }

    @DataProvider(name = "data")
    public Object[][] ProviderData(){
        Object[][] o = new Object[][]{
                {"ruan",123},
                {"xiao",234}
        };
        return o;
    }
}
