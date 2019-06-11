package com.mloine.auth.auths.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限控制注解
 *  请在controller 层 的入口加入自定义注解用于权限处理
 *  例子：@AuthPermissions(value={"mloine"},type = "pc端登录")
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthPermissions {

    //权限资源
    String[] value();

    //适用渠道
    String type() default "ALL"; //默认所有登录方式支持

}
