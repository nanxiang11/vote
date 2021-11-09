package com.nx.longtm.controller;

import com.nx.longtm.bean.CommonResult;
import com.nx.longtm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName : Post
 * @Description : 注册
 * @Author : 杨涛
 * @Date: 2021/10/25 9:25
*/
@RestController
public class Post {

    @Autowired
    LoginService loginService;

    @PostMapping("/post")
    public CommonResult<Object> post(@RequestParam("name") String name,
                             @RequestParam("Pass") String pass,
                             @RequestParam("email") String email,
                             @RequestParam("sex") char sex, @RequestParam("college") String college){

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        try {
            String[] q = email.split("@");
            String imgurl = String.format("http://q2.qlogo.cn/headimg_dl?dst_uin=%s&spec=100",q[0]);
            loginService.post(name, pass, email,imgurl,college, dateFormat.format(date));
            return new CommonResult<>(200, "成功注册");
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult<>(403, null);
        }
    }
}
