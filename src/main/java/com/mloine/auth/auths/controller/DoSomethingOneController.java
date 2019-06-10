package com.mloine.auth.auths.controller;

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

    @GetMapping("/role1")
    @RequiresPermissions({ "class-score:list" })
    public String role1(){
        return "有次权限 class-score:list";
    }

    @GetMapping("/role2")
    @RequiresPermissions({ "class-scorenonoaway:list" })
    public String role2(){
        return "有次权限 class-scorenonoaway:list";
    }
}
