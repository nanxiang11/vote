package com.nx.longtm.controller;

import com.nx.longtm.bean.CommonResult;
import com.nx.longtm.bean.UserTP;
import com.nx.longtm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName : Editsuer
 * @Description : 编辑个人信息
 * @Author : 南巷的花猫
 * @Date: 2021/10/28 15:00
*/
@RestController
public class Editsuer {

    @Autowired
    LoginService loginService;

    @PostMapping("/edituser")
    public CommonResult<Object> edituser(@RequestParam("id") int id,
                                         @RequestParam("aboutme") String aboutme){
            if (!aboutme.equals("")){
                loginService.edituser(id, aboutme);
                return new CommonResult<>(200, null);
            }else {
                return new CommonResult<>(403, null);
            }
    }
    /**
     * @Description :
     * @Author : 南巷的花猫
     * @Date : 2021/10/31 8:42
    */

    @GetMapping("/getalluseractive")
    public CommonResult<Object> getalluseractive(@RequestParam("id") int id){
        List<UserTP> list = loginService.queryUserTP(id);
        if(list != null){
            return new CommonResult<>(200, list);
        }else {
            return new CommonResult<>(404, null);
        }

    }
}
