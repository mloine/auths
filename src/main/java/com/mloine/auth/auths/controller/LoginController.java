package com.mloine.auth.auths.controller;

import com.mloine.auth.auths.entity.User;
import com.mloine.auth.auths.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: LoginController
 * @Description:TOO
 * @Author:mloine
 * @Date：2019/6/617:06
 **/
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login/{id}")
    public String login(@PathVariable("id") Integer id){
        User userById = loginService.getUserById (id);
        return "success mloine! 2019 06 06" + userById.toString () ;
    }

    @RequestMapping(value = "/login")
    public void doLogin(String userName,String pwd){

    }

}
