package com.nx.longtm.controller;

import com.nx.longtm.bean.CommonResult;
import com.nx.longtm.bean.User;
import com.nx.longtm.bean.UserA2;
import com.nx.longtm.service.LoginService;
import com.nx.longtm.service.TpactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName : Admin
 * @Description :  后台控制接口
 * @Author : 南巷的花猫
 * @Date: 2021/10/29 9:18
*/
@RestController
public class Admin {

    @Autowired
    TpactiveService tpactiveService;

    @Autowired
    LoginService loginService;

    /**
     * @Description :  获取所有没有过审的
     * @Author : 南巷的花猫
     * @Date : 2021/10/29 11:33
    */
    @GetMapping("/getadminActive")
    public CommonResult<Object> getadminActive(){
        List<UserA2> activeall = tpactiveService.queryAll2();
        return new CommonResult<>(200, activeall);
    }
    /**
     * @Description : 过审
     * @Author : 南巷的花猫
     * @Date : 2021/10/29 11:33
    */
    @PostMapping("/getactiveID")
    public CommonResult<Object> setactiveID(@RequestParam("id") int id){
        tpactiveService.adminactive(id);
        return new CommonResult<>(200, null);
    }
    /**
     * @Description :  获取有身份认证的成员
     * @Author : 易鹏涛
     * @Date : 2021/10/30 9:35
    */
    @GetMapping("getadminuser")
    public CommonResult<Object> getadminuser(){
        List<User> list = loginService.queryalluser();
        if (list != null){
            return new CommonResult<>(200, list);
        }else {
            return new CommonResult<>(404, null);
        }

    }
    /**
     * @Description :
     * @Author : 周锡锋
     * @Date : 2021/10/30 8:35
    */
    @PostMapping("/setuseradmin")
    public CommonResult<Object> setuseradmin(@RequestParam("id") int id,
                                             @RequestParam("select") String select){
        loginService.setadminuser(id, select);
        return new CommonResult<>(200, null);
    }
}
