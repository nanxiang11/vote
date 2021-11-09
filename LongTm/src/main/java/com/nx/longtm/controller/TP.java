package com.nx.longtm.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nx.longtm.bean.CommonResult;
import com.nx.longtm.bean.TPresult;
import com.nx.longtm.bean.UserA;
import com.nx.longtm.service.TpactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName : TP
 * @Description :  获取投票
 * @Author : 南巷的花猫
 * @Date: 2021/10/27 14:00
*/
@RestController
public class TP {

    @Autowired
    TpactiveService tpactiveService;

    @PostMapping("/getTP")
    public CommonResult<Object> getTP(@RequestBody List<Object> activelist){
        String a = tpactiveService.queryId((Integer) activelist.get(0)).getActive().getDomains();
        JSONArray list = JSONArray.parseArray(a);
        for (int i = 0; i < list.size(); i++){
            JSONObject object = (JSONObject) list.get(i);
            JSONObject jsonObject = new JSONObject();
            if(((String) activelist.get(2)).equals((String) object.get("key"))){
                int num = (Integer) object.get("value");
                num = num + 1;
                object.put("value",num);
                list.set(i, object);
            }
        }
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        tpactiveService.editId((Integer) activelist.get(0), list.toJSONString());
        tpactiveService.addPerson_AC((Integer) activelist.get(3), (Integer) activelist.get(0), (String) activelist.get(2), dateFormat.format(date));
        return new CommonResult<>(200, null);
    }

    @GetMapping("/getactiveV")
    public CommonResult<Object> getactiveV(@RequestParam("id") int id){
        UserA userA = tpactiveService.queryId(id);
        if (userA != null){
            String a = userA.getActive().getDomains();
            return new CommonResult<>(200, a);
        }else {
            return new CommonResult<>(404, null);
        }

    }

    @GetMapping("/getallTP")
    public CommonResult<Object> getallTP(@RequestParam("active_id") int active_id){
        List<TPresult> tPresult = tpactiveService.queryTPU(active_id);
        if (tPresult != null){
            if(tPresult.get(0).getChoose().equals("匿名")){
                for (TPresult presult : tPresult) {
                    presult.setStudentName("匿名");
                }
            }
            return new CommonResult<>(200, tPresult);
        }else {
            return new CommonResult<>(404, null);
        }
    }
}
