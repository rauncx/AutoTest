package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我的post请求")
@RequestMapping("v1")   //这里加了v1，下面的方法都要加v1
public class MyPostMethod {

    //这个变量用来装cookie信息的
    private static Cookie cookie;

    //用户登陆成功获取到cookie,然后访问其他接口
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

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User u){
        User user;
        //获取cookie
        Cookie[] cookies = request.getCookies();
        //验证cookie
        for(Cookie c : cookies){
            if(c.getName().equals("login") && c.getValue().equals("true")
                    && u.getUsername().equals("ruan")
                    && u.getPassword().equals("123456")){
                user = new User();
                user.setName("ruan");
                user.setAge("18");
                user.setSex("n");
                return user.toString();
            }
        }
        return "参数不合法";
    }
}
