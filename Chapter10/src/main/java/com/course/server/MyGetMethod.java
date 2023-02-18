package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {

    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获得cookie值",httpMethod = "GET")
    public String GetCookies(HttpServletResponse response){

        //HttpServletRequest 装请求信息的类
        //HttpServletResponse 装响应信息的类

        //创建一个cookie
        Cookie cookie = new Cookie("login","true");
        //返回cookie
        response.addCookie(cookie);
        return "成功获得cookies信息";
    }

    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "要求携带cookie才能访问",httpMethod = "GET")
    public String getwithCookies(HttpServletRequest request){

        //有多个cookie时可以用数组存储
        Cookie[] cookies = request.getCookies();
        //判断cookies信息是否为空
        if(Objects.isNull(cookies)){
            return "必须携带cookies，test";
        }
        for(Cookie cookie : cookies){
            //判断cookies信息是否符合要求
            if(cookie.getName().equals("login") &&
                    cookie.getValue().equals("true")){
                return "访问成功了";
            }else {
                return "cookies信息错误";
            }
        }
        return "必须携带cookies信息";

    }

    // 开发一个下用携带参数才能访问的get请求
    //第一种方式：url:key=value&&key=value

    //模拟商品列表
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "携带参数才能访问的get请求1",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();

        myList.put("包包",500);
        myList.put("test",100);
        myList.put("手机",1999);

        return myList;
    }
    //第二种方式
    //区别，访问路径上的区别。url:ip:port/get/with/param/10/20
    @RequestMapping(value = "/get/with/param/{start}/{end}")
    @ApiOperation(value = "携带参数才能访问的请求2",httpMethod = "GET")
    public Map myGetList(@PathVariable Integer start,
                         @PathVariable Integer end){
        Map<String,Integer> myList = new HashMap<>();

        myList.put("包包",500);
        myList.put("test",100);
        myList.put("手机",1999);

        return myList;

    }

}
