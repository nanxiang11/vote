package com.nx.longtm.service;

import com.nx.longtm.bean.StudentsBean;
import com.nx.longtm.dao.StudentDao;
import com.nx.longtm.service.Insv.StudentServiceln;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements StudentServiceln {

    @Autowired
    StudentDao studentDao;

    @Override
    public List<StudentsBean> queryall() {
        return studentDao.queryall();
    }

    @Override
    public void add(String name, String sex, String nclass, String address, String tel, String post_time) {
        studentDao.add(name, sex, nclass, address, tel, post_time);
    }

    @Override
    public void del(int id) {
        studentDao.del(id);
    }

    @Override
    public void edit(int id, String name, String sex, String nclass, String address, String tel) {
        studentDao.edit(id, name, sex, nclass, address, tel);
    }

    @Override
    public String queryadmin(String name) {
        return studentDao.queryadmin(name);
    }


}
