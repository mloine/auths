package com.mloine.auth.auths.aop;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * @className: LoginAuthInterceptor
 * @Description:TOO
 * @Author:mloine
 * @Date：2019/6/1114:07
 **/
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                             Object handler) throws Exception {

        //校验是否標誌位 目前默認放行
        Boolean roleFlag = true;

        if (handler instanceof HandlerMethod) {
            AuthPermissions authPermissions = findAnnotation((HandlerMethod) handler, AuthPermissions.class);
            String[] value = authPermissions.value();
            String type = authPermissions.type();
//            System.out.println ("注解value值" + value);
//            System.out.println ("渠道" + type);
            //redis 获取权限信息
            //TODO
            //校验是否越远
            //TODO
        }


        return roleFlag;

    }


    private <T extends Annotation> T findAnnotation(HandlerMethod handler, Class<T> annotationType) {
        T annotation = handler.getBeanType().getAnnotation(annotationType);
        if (annotation != null) return annotation;
        return handler.getMethodAnnotation(annotationType);
    }


}

