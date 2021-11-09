package com.nx.longtm.service;

import com.nx.longtm.bean.Active;
import com.nx.longtm.bean.TPresult;
import com.nx.longtm.bean.UserA;
import com.nx.longtm.bean.UserA2;
import com.nx.longtm.dao.Tpactive;
import com.nx.longtm.service.Insv.TpactiveIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName : TpactiveService
 * @Description :  活动接口实现类
 * @Author : 刘文豪
 * @Date: 2021/10/26 16:14
*/
@Service
public class TpactiveService implements TpactiveIn {

    @Autowired
    Tpactive tpactive;

    @Override
    public void addactive(int userid, String email, String activename, String activedes, String imgurl, String choose, String domains, String post_time, String endtime) {
        tpactive.addactive(userid, email, activename, activedes, imgurl, choose, domains, post_time, endtime);
    }

    @Override
    public UserA queryId(int id) {
        return tpactive.queryId(id);
    }

    @Override
    public List<UserA2> queryAll() {
        return tpactive.queryAll();
    }

    @Override
    public List<UserA2> queryAll2() {
        return tpactive.queryAll2();
    }

    @Override
    public void editId(int id, String domains) {
        tpactive.editId(id, domains);
    }

    @Override
    public void addPerson_AC(int user_id, int active_id, String choose, String post_time) {
        tpactive.addPerson_AC(user_id, active_id, choose, post_time);
    }

    @Override
    public List<TPresult> queryTPU(int active_id) {
        return tpactive.queryTPU(active_id);
    }

    @Override
    public void adminactive(int id) {
        tpactive.adminactive(id);
    }

    @Override
    public List<Active> getnameactive(String name) {
        return tpactive.getnameactive(name);
    }


}
