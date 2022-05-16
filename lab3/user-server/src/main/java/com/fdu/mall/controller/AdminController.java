package com.fdu.mall.controller;


import com.fdu.mall.model.Content1;
import com.fdu.mall.model.Resp;
import com.fdu.mall.model.Result;
import com.fdu.mall.model.vo.LoginVO;
import com.fdu.mall.model.vo.UserShowVO;
import com.fdu.mall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     *@Description: 管理员和商家后台登录
     *@Param:  * @Param: map
     *@return: com.fdu.mall.model.Resp
     *@Author: scy
     *@date: 2021/10/14
     */
    @PostMapping("/login")
    public Resp login(@RequestBody Map map){
        String username = (String) map.get("username");
        String pwd = (String) map.get("password");
        LoginVO login = adminService.login(username,pwd);
        if(login==null){
            return new Resp(false,null,"用户名不存在");
        }
        Content1 content1 = new Content1(login.getUsername(), login.getToken(), 1000);
        return new Resp(true, content1,"为什么这里要有个余额？");
    }

    /**
     *@Description: 后台退出
     *@Param:  * @Param: token
     *@return: com.fdu.mall.model.Result
     *@Author: scy
     *@date: 2021/10/15
     */
    @PostMapping("/logout")
    public Result logout(String token){
        return Result.ok();
    }

    @GetMapping("user/allUser")
    public Result selectAllUsers(){
        List<UserShowVO> users=adminService.getAllUsers();
        return Result.ok(users);
    }
    @GetMapping("user/searchUser")
    public Result searchUser(@RequestParam("word") String username){

        UserShowVO user=adminService.getUser(username);
        if(user==null){
            return Result.fail("查询不到用户！");
        }
        return Result.ok(user);
    }


    @GetMapping("user/deleteUser")
    public Result deleteUser(@RequestParam("id") int id){

        int userdelete=adminService.deleteUser(id);
        if(userdelete==1) return Result.ok();
        return Result.fail("用户不存在!");
    }
}
