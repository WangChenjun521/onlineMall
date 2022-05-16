package com.fdu.mall.dao;

import com.fdu.mall.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    //注册
    int insertRegister(@Param("user") User user);
    List<User> selectAllUsers();
    //登录
    User selectLogin(@Param("name") String name, @Param("psw") String psw);
    //查询用户余额
    Integer selectBalanceByName(@Param("name") String username);
    //充值
    int updateBalanceByName(@Param("name") String username,@Param("balance") Integer balance);
    //订单调用接口
    User selectUserByName(@Param("name") String username);
}
