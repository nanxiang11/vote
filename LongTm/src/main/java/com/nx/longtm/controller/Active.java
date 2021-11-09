package com.nx.longtm.controller;

import com.alibaba.fastjson.JSONObject;
import com.nx.longtm.bean.CommonResult;
import com.nx.longtm.service.TpactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName : Active
 * @Description : 接收前端活动详情数据
 * @Author : 刘文豪
 * @Date: 2021/10/26 11:28
*/
@RestController
public class Active {

    @Autowired
    TpactiveService tpactiveService;
    private String imgurl = "";

    @PostMapping("/setactive")
    public CommonResult<Object> setactive(@RequestBody Map<String, Object> active){
        if(this.imgurl != null){

            List<Map> newdomains = new ArrayList<>();

            int userid = (int) active.get("userid");
            String email = (String) active.get("email");
            String activename = (String) active.get("active");
            String activedes = (String) active.get("msg");
            String imgurl = this.imgurl;
            String choose = (String) active.get("choose");
            String endtime = (String) active.get("endtime");
//            String domains = (String) active.get("domains");

            String[] a = endtime.split("T");

            List<Map> Ldomains = (List<Map>) active.get("domains");
            for(int i = 0; i < Ldomains.size(); i++){
                Map<String, Object> map2 = (Map) Ldomains.get(i);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("key", map2.get("value"));
                jsonObject.put("value", 0);
                newdomains.add(jsonObject);
            }
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            tpactiveService.addactive(userid, email, activename, activedes, imgurl, choose, newdomains.toString(), dateFormat.format(date), a[0]);

            return new CommonResult<>(200, null);
        }else {
            return new CommonResult<>(404, null);
        }

    }

    @PostMapping("/upimg")
    public void upimg(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "C:\\NXJava\\LongTm\\src\\main\\resources\\static\\images\\"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "\\images\\" + fileName;
        this.imgurl = filename;

    }

    @GetMapping("/getnameactive")
    public CommonResult<Object> getnameactive(@RequestParam("name") String name){
        if(tpactiveService.getnameactive(name) != null){
            return new CommonResult<>(200, tpactiveService.getnameactive(name));
        }else {
            return new CommonResult<>(404, null);
        }

    }


}
