package com.nx.longtm.controller;

import com.nx.longtm.bean.CommonResult;
import com.nx.longtm.bean.UserA;
import com.nx.longtm.bean.UserA2;
import com.nx.longtm.service.TpactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName : ActiveInfo
 * @Description : 详细活动信息
 * @Author : 周文乐
 * @Date: 2021/10/26 21:23
*/
@RestController
public class ActiveInfo {

    @Autowired
    TpactiveService tpactiveService;

    @GetMapping("/getactiveinfo")
    public CommonResult<Object> getactiveinfo(@RequestParam("id") int id){
        return new CommonResult<>(200, tpactiveService.queryId(id));
    }

    @GetMapping("/getactiveall")
    public CommonResult<Object> getactiveall(){
        List<UserA2> activeall = tpactiveService.queryAll();
        return new CommonResult<>(200, activeall);
    }
}
