package com.fdu.mall.service;

import com.fdu.mall.model.User;
import com.fdu.mall.model.bo.UserRegisterBO;
import com.fdu.mall.model.vo.LoginUserVO;
import com.fdu.mall.model.vo.UserRegisterVO;

public interface UserService {

    //注册
    UserRegisterVO register(UserRegisterBO userRegisterBO);
    //登录
    LoginUserVO login(String name, String psw);
    //查询用户余额
    Integer getCredit(String username);
    //充值
    int addCredit(String username,Integer change);
    //订单调用接口
    User getUserInfo(String username);
}
