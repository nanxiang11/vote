package com.nx.longtm.bean;

import lombok.Data;
/**
 * @ClassName : User
 * @Description :  用户实体
 * @Author : 南巷的花猫
 * @Date: 2021/10/29 14:53
*/
@Data
public class User {
    private int id;
    private String studentName;
    private String studentPassword;
    private String studentEmail;
    private String imgurl;
    private String aboutme;
    private String college;
    private String select;
    private String post_time;
}
