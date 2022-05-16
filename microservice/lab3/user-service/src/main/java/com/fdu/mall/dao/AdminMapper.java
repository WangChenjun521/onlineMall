package com.fdu.mall.dao;

import com.fdu.mall.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    //管理员登录
    User selectLogin(@Param("name") String name, @Param("psw") String psw);
    List<User> selectAllUsers();
    User getUser(String username);
    int deleteUser(int id);
}
