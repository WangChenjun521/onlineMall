package com.fdu.mall.service;


import com.fdu.mall.dao.UserMapper;
import com.fdu.mall.model.Authority;
import com.fdu.mall.model.User;
import com.fdu.mall.model.bo.UserRegisterBO;
import com.fdu.mall.model.vo.LoginUserVO;
import com.fdu.mall.model.vo.UserRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     *@Description:
     *@Param:  * @Param: userRegisterBO
     *@return: com.fdu.mall.model.vo.UserRegisterVO
     *@Author: scy
     *@date: 2021/10/15
     */
    @Override
    public UserRegisterVO register(UserRegisterBO userRegisterBO) {
        List<User> users = userMapper.selectAllUsers();
        //判断用户名是否重复
        for (User user : users) {
            if(user.getUsername().equals(userRegisterBO.getUsername())&&user.getAuthority()==3){
                return null;
            }
        }
        User user = new User(null,userRegisterBO.getUsername(), userRegisterBO.getUsername(),userRegisterBO.getPwd(), userRegisterBO.getRecipient(), userRegisterBO.getAddress(), userRegisterBO.getPhone(), null);
        int i = userMapper.insertRegister(user);
        if(i==1){
            UserRegisterVO userRegisterVO = new UserRegisterVO(0,userRegisterBO.getUsername(), userRegisterBO.getUsername());
            return userRegisterVO;
        }
        return null;
    }

    /**
     *@Description:
     *@Param:  * @Param: name
     * @Param: psw
     *@return: com.fdu.mall.model.vo.LoginVO
     *@Author: scy
     *@date: 2021/10/15
     */
    @Override
    public LoginUserVO login(String name, String psw) {
        User user = userMapper.selectLogin(name, psw);
        if (user != null) {
            //判断是买家
            if (user.getAuthority().equals(Authority.USER.getAuthority())) {
                LoginUserVO loginUserVO = new LoginUserVO(user.getUsername(), user.getNickname());
                return loginUserVO;
            }
        }
        return null;
    }

    /**
     *@Description: 获得用户余额
     *@Param:  * @Param: username
     *@return: java.lang.Integer
     *@Author: scy
     *@date: 2021/12/11
     */
    @Override
    public Integer getCredit(String username) {
        Integer balance = userMapper.selectBalanceByName(username);
        return balance;
    }

    /**
     *@Description: 充值
     *@Param:  * @Param: username
     *@Param: change
     *@return: int
     *@Author: scy
     *@date: 2021/12/11
     */
    @Override
    public int addCredit(String username, Integer change) {
        Integer oldBalance = userMapper.selectBalanceByName(username);
        Integer newBalance = oldBalance+change;
        int balance = userMapper.updateBalanceByName(username, newBalance);
        return balance;
    }

    /**
     *@Description: 订单调用接口
     *@Param:  * @Param: username
     *@return: com.fdu.mall.model.User
     *@Author: scy
     *@date: 2021/12/13
     */
    @Override
    public User getUserInfo(String username) {
        return userMapper.selectUserByName(username);
    }

}
