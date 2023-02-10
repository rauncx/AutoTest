package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {
    @Test(groups = "test")
    public void test(){
        System.out.println("test");
    }

    @BeforeGroups("test")
    public void beforeG(){
        System.out.println("服务组运行之前");
    }

    @AfterGroups("test")
    public void AfterG(){
        System.out.println("服务组运行之后");
    }
}
