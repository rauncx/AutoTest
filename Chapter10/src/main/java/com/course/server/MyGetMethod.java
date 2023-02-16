package com.course.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
public class MyGetMethod {

    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
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

}
