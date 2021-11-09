package com.nx.longtm.service.Insv;

import com.nx.longtm.bean.StudentsBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentServiceln {
    public List<StudentsBean> queryall();

    public void add(@Param("name") String name,
                    @Param("sex") String sex,
                    @Param("nclass") String nclass,
                    @Param("address") String address,
                    @Param("tel") String tel,
                    @Param("post_time") String post_time);

    public void del(@Param("id") int id);

    public void edit(@Param("id") int id,
                     @Param("name") String name,
                     @Param("sex") String sex,
                     @Param("nclass") String nclass,
                     @Param("address") String address,
                     @Param("tel") String tel);

    public String queryadmin(@Param("name") String name);
}
