package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {

    private String url;
    private ResourceBundle bundle;
    //用来存储cookies信息的变量
    private CookieStore store;

    //加载配置文件并获取url
    @BeforeTest
    public void beforeTest(){
        //加载配置文件
        bundle = ResourceBundle.getBundle("application", Locale.CANADA);
        //获取url
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        //从配置文件中拼接url
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url+uri;

        //声明一个get方法
        HttpGet get = new HttpGet(testUrl);
        // 声明一个Client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();
        //执行post方法
        HttpResponse response = client.execute(get);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();

        for (Cookie cookie : cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookies name = "+name+"; cookies value = "+value);
        }
    }
    //携带Cookie信息访问get请求
    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostMethod() throws IOException {
        //拼接url
        String uri = bundle.getString("test.post.with.cookies");
        String testUrl = this.url+uri;

        // 声明一个Client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个post方法
        HttpPost post = new HttpPost(testUrl);

        //添加参数
        JSONObject param = new JSONObject();
        param.put("name","ruan");
        param.put("age","18");

        //设置请求信息header
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        //声明一个对象来进行响应结果的存储
        String result;

        //设置cookies信息
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse response = client.execute(post);

        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //处理结果，判断返回结果是否符合预期
        //将返回的响应结果字符串转换为json对象
        JSONObject resultJson = new JSONObject(result);

        //获取到结果值
        String success = (String) resultJson.get("ruan");
        String status = (String) resultJson.get("status");

        //具体的判断返回结果的值，("期望结果",实际结果)期望结果和实际结果的比对
        Assert.assertEquals("success",success);
        Assert.assertEquals("1",status);

    }
}
