package com.mloine.auth.auths.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @className: AuthAop
 * @Description:TOO
 *      请在controller 层 的入口加入自定义注解用于权限处理
 *      例子：@AuthPermissions(value={"mloine"},type = "pc端登录")
 * @Author:mloine
 * @Date：2019/6/1110:09
 *
 * 和interceptor 有差距 不建议使用 会报错 - -
 **/
//@Component
//@Aspect
public class AuthAop {

    @Before(value="@annotation(com.mloine.auth.auths.aop.AuthPermissions)")
    public void dofore(JoinPoint jp) {
        try {

            System.out.println ("aop权限处理之前------哈哈哈");

            //获取方法
               Method method = ((MethodSignature) jp.getSignature ()).getMethod ();
            //获取注解
            AuthPermissions annotation = method.getAnnotation (AuthPermissions.class);
            String[] value = annotation.value ();//权限资源
            String type = annotation.type ();//渠道
            System.out.println ("注解value值" + value);
            System.out.println ("渠道" + type);

            //redis 获取权限信息


            //校验是否越远
            Boolean roleFlag = true;
            //TOO
            //获取request
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            HttpServletResponse response = attributes.getResponse ();

            //response 模拟越权请求响应
            if(roleFlag){
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                try(PrintWriter out = response.getWriter();){
                    out.println("越权辣");
                }catch (Exception e){

                } finally{
                }
            }

        } catch (Throwable e) {
            System.out.println("有异常啊");
        }
    }
}
