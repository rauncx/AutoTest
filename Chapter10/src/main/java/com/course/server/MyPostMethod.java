package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我的post请求")
@RequestMapping("v1")   //这里加了v1，下面的方法都要加v1
public class MyPostMethod {

    //这个变量用来装cookie信息的
    private static Cookie cookie;

    //用户登陆成功获取到cookie,如何访问其他接口
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登陆接口，登陆成功后获得cookie信息",httpMethod = "POST")
    public String login(HttpServletResponse response,   //加入cookie信息
                        //参数,required可以设置参数是否为必须的
                        @RequestParam(value = "username",required = true) String username,
                        @RequestParam(value = "password",required = true) String password){
        //验证参数是否正确
        if(username.equals("ruan") && password.equals("123456")){

            cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "登陆成功了";
        }
        return "用户名或密码错误";

    }
}
