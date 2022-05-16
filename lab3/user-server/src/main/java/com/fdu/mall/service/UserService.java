package com.fdu.mall.service;

import com.fdu.mall.model.bo.UserRegisterBO;
import com.fdu.mall.model.vo.LoginUserVO;
import com.fdu.mall.model.vo.LoginVO;
import com.fdu.mall.model.vo.UserRegisterVO;

public interface UserService {

    //注册
    UserRegisterVO register(UserRegisterBO userRegisterBO);
    //登录
    LoginUserVO login(String name, String psw);
}
