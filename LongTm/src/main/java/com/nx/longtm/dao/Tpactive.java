package com.nx.longtm.dao;

import com.nx.longtm.bean.Active;
import com.nx.longtm.bean.TPresult;
import com.nx.longtm.bean.UserA;
import com.nx.longtm.bean.UserA2;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName : Tpactive
 * @Description :  活动接口
 * @Author : 刘文豪
 * @Date: 2021/10/26 16:05
*/
@Resource
public interface Tpactive {

    public void addactive(@Param("userid") int userid,
                          @Param("email") String email,
                          @Param("activename") String activename,
                          @Param("activedes") String activedes,
                          @Param("imgurl") String imgurl,
                          @Param("choose") String choose,
                          @Param("domains") String domains,
                          @Param("post_time") String post_time,
                          @Param("endtime") String endtime);

    public UserA queryId(@Param("id") int id);

    public List<UserA2> queryAll();
    public List<UserA2> queryAll2();

    public void editId(@Param("id") int id,
                        @Param("domains") String domains);

    public void addPerson_AC(@Param("user_id") int user_id,
                             @Param("active_id") int active_id,
                             @Param("choose") String choose,
                             @Param("post_time") String post_time);

    public List<TPresult> queryTPU(@Param("active_id") int active_id);

    public void adminactive(@Param("id") int id);

    public List<Active> getnameactive(@Param("name") String name);
}
