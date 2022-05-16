package com.fdu.mall.controller;

import com.fdu.mall.model.Result;
import com.fdu.mall.model.bo.UserRegisterBO;
import com.fdu.mall.model.vo.LoginUserVO;
import com.fdu.mall.model.vo.LoginVO;
import com.fdu.mall.model.vo.UserRegisterVO;
import com.fdu.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mall/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     *@Description: 前台用户注册     *@Param:  * @Param: register
     *@return: com.fdu.mall.model.Result
     *@Author: scy
     *@date: 2021/10/15
     */
    @PostMapping("signup")
    public Result register(@RequestBody UserRegisterBO register){
        UserRegisterVO user = userService.register(register);
        if(user==null){
            return Result.fail();
        }
        return Result.ok(user);
    }

    /**
     *@Description: 前台用户登录
     *@Param:  * @Param: map
     *@return: com.fdu.mall.model.Result
     *@Author: scy
     *@date: 2021/10/15
     */
    @PostMapping("login")
    public Result login(@RequestBody Map map){
        String username = (String) map.get("username");
        String pwd = (String) map.get("pwd");
        LoginUserVO login = userService.login(username, pwd);
        if(login==null){
            return Result.fail("密码不正确!");
        }
        return Result.ok(login);
    }

    /**
     *@Description: 前台用户退出
     *@Param:  * @Param: map
     *@return: com.fdu.mall.model.Result
     *@Author: scy
     *@date: 2021/10/15
     */
    @PostMapping("logoutClient")
    public Result logout(@RequestBody Map map){
        return Result.ok();
    }
}
