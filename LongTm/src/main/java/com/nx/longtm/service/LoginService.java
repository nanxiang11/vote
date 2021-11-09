package com.nx.longtm.service;


import com.nx.longtm.bean.LoginBean;
import com.nx.longtm.bean.User;
import com.nx.longtm.bean.UserTP;
import com.nx.longtm.dao.LoginDao;
import com.nx.longtm.service.Insv.LoginServiceIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("LoginService")
public class LoginService implements LoginServiceIn {

    @Autowired
    LoginDao loginDao;


    public LoginBean login(String studentName, String studentPassword) {
        return loginDao.login(studentName, studentPassword);
    }

    @Override
    public boolean post(String name, String studentPassword, String email, String imgurl, String college, String postTime) {
        return loginDao.post(name, studentPassword, email, imgurl, college, postTime);
    }

    @Override
    public void edituser(int id, String aboutme) {
        loginDao.edituser(id, aboutme);
    }

    @Override
    public List<UserTP> queryUserTP(int id) {
        return loginDao.queryUserTP(id);
    }

    @Override
    public List<User> queryalluser() {
        return loginDao.queryalluser();
    }

    @Override
    public void setadminuser(int id, String select) {
        loginDao.setadminuser(id, select);
    }


}
