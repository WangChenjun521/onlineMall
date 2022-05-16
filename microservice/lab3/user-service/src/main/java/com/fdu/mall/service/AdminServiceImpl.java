package com.fdu.mall.service;

import com.fdu.mall.dao.AdminMapper;
import com.fdu.mall.model.Authority;
import com.fdu.mall.model.User;
import com.fdu.mall.model.vo.LoginVO;
import com.fdu.mall.model.vo.UserShowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    /**
     * @Description: 后台登录业务处理
     * @Param: name
     * @Param: psw
     * @return: int
     * @Author: scy
     * @date: 2021/10/15
     */
    @Override
    public LoginVO login(String name, String psw) {
        User admin = adminMapper.selectLogin(name, psw);
        if (admin != null) {
            //判断是管理员或者商家
            if (admin.getAuthority().equals(Authority.ADMIN.getAuthority()) || admin.getAuthority().equals(Authority.SHOP.getAuthority())) {
                LoginVO loginVO = new LoginVO(admin.getUsername(), admin.getNickname());
                System.out.println("1111111");
                return loginVO;
            }
        }
        return null;
    }

    public List<UserShowVO> getAllUsers() {
        List<User> users =adminMapper.selectAllUsers();

        List<UserShowVO> userList=new ArrayList<UserShowVO>();
        for(int i=0;i< users.size();i++){
            UserShowVO user_temp=new UserShowVO();
            user_temp.setId(users.get(i).getId());
            user_temp.setUsername(users.get(i).getUsername());
            user_temp.setPwd(users.get(i).getPassword());
            user_temp.setRecipient(users.get(i).getAddressee());
            user_temp.setAddress(users.get(i).getAddress());
            user_temp.setPhone(users.get(i).getPhone());
            userList.add(user_temp);
        }
       return userList;
    }
    public UserShowVO getUser(String username){
        User user =adminMapper.getUser(username);
        if (user != null) {
            UserShowVO user_temp=new UserShowVO();
            user_temp.setId(user.getId());
            user_temp.setUsername(user.getUsername());
            user_temp.setPwd(user.getPassword());
            user_temp.setRecipient(user.getAddressee());
            user_temp.setAddress(user.getAddress());
            user_temp.setPhone(user.getPhone());
            return user_temp;
        }
        return null;
    }
    public int deleteUser(int id){

        return adminMapper.deleteUser(id);
    }

}