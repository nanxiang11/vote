package com.nx.longtm.dao;

import com.nx.longtm.bean.LoginBean;
import com.nx.longtm.bean.User;
import com.nx.longtm.bean.UserTP;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface LoginDao {

    public LoginBean login(@Param("studentName") String studentName,
                           @Param("studentPassword") String studentPassword);

    public boolean post(@Param("studentName") String name,
                        @Param("studentPassword") String studentPassword,
                        @Param("studentEmail") String email,
                        @Param("imgurl") String imgurl,
                        @Param("college") String college,
                        @Param("post_time") String postTime);

    public void edituser(@Param("id") int id,
                         @Param("aboutme") String aboutme);

    public List<UserTP> queryUserTP(@Param("id") int id);

    public List<User> queryalluser();

    public void setadminuser(@Param("id") int id,
                             @Param("select") String select);
}
