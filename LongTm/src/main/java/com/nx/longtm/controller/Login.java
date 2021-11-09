package com.nx.longtm.controller;

import com.nx.longtm.bean.CommonResult;
import com.nx.longtm.bean.LoginBean;
import com.nx.longtm.bean.PostLogin;
import com.nx.longtm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : Login
 * @Description : 登入接口
 * @Author : 唐彩云
 * @Date: 2021/10/25 8:33
*/
@RestController
public class Login {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public CommonResult<Object> login(@RequestParam("name") String name, @RequestParam("Pass") String Pass){
        LoginBean user_info = loginService.login(name, Pass);
        if (user_info != null){
            return new CommonResult<Object>(200, user_info);
        }else {
            return new CommonResult<Object>(403, null);
        }
    }
}
