package com.fdu.mall.service;

import com.fdu.mall.model.vo.LoginVO;
import com.fdu.mall.model.vo.UserShowVO;

import java.util.List;

public interface AdminService {
    //登录
    LoginVO login(String name, String psw);
    List<UserShowVO> getAllUsers();
    UserShowVO getUser(String username);
    int deleteUser(int id);
}
