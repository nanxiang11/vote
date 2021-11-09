package com.nx.longtm.controller;

import com.github.pagehelper.PageHelper;
import com.nx.longtm.bean.CommonResult;
import com.nx.longtm.bean.StudentsBean;
import com.nx.longtm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/student")
public class Students {

    @Autowired
    StudentService studentServicel;

    @GetMapping("/queryall")
    public CommonResult<List<StudentsBean>> querall(@RequestParam(defaultValue = "1") Integer pageNum,
                                                    @RequestParam(defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);//使用分页插件，最核心的一句，即开启分页
        return new CommonResult<>(200, studentServicel.queryall(), studentServicel.queryall().size());
    }

    @PostMapping("/save")
    public CommonResult<Object> save(@RequestParam("name") String name,
                                     @RequestParam("tel") String tel,
                                     @RequestParam("sex") String sex,
                                     @RequestParam("nclass") String nclass,
                                     @RequestParam("address") String address){
        if (!name.equals("")){
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            studentServicel.add(name, sex, nclass, address, tel, dateFormat.format(date));
            return new CommonResult<>(200, "success");
        }else {
            return new CommonResult<>(403, "error");
        }
    }

    @GetMapping("/del")
    public CommonResult<Object> del(@RequestParam("id") int id){
        try{
            studentServicel.del(id);
            return new CommonResult<Object>(200, "success");
        }catch (Exception exception){
            exception.printStackTrace();
            return new CommonResult<Object>(403, "error");
        }
    }

    @PostMapping("/edit")
    public CommonResult<Object> edit(@RequestParam("id") int id,
                                     @RequestParam("name") String name,
                                     @RequestParam("tel") String tel,
                                     @RequestParam("sex") String sex,
                                     @RequestParam("nclass") String nclass,
                                     @RequestParam("address") String address){
        try{
            studentServicel.edit(id, name, sex, nclass, address, tel);
            return new CommonResult<Object>(200, "success");
        }catch (Exception exception){
            exception.printStackTrace();
            return new CommonResult<Object>(403, "error");
        }
    }

    @GetMapping("/getmanage")
    public CommonResult<Object> getmanage(@RequestParam("name") String name){
        System.out.println(name);
        try {
            String manageid = studentServicel.queryadmin(name);
            if (manageid != null){
                return new CommonResult<Object>(200, "success");
            }else {
                System.out.println(manageid);
                return new CommonResult<Object>(404, "error");
            }

        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult<Object>(403, "error");
        }
    }

}
