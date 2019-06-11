package com.mloine.auth.auths.controller;

import com.mloine.auth.auths.aop.AuthPermissions;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: DoSomethingOneController
 * @Description:TOO
 * @Author:mloine
 * @Date：2019/6/617:36
 **/
@RestController
@Api(tags = "权限测试接口")
@RequestMapping(value = "/role")
public class DoSomethingOneController {

    @AuthPermissions(value={"mloine"},type = "pc端登录")
    @GetMapping("/role1")
    @RequiresPermissions({ "class-score:list" })
    public String role1(){

        System.out.println ("111111111111111");
        return "有次权限 class-score:list";
    }

    @GetMapping("/role2")
    @RequiresPermissions({ "class-scorenonoaway:list" })
    public String role2(){
        return "有次权限 class-scorenonoaway:list";
    }
}
