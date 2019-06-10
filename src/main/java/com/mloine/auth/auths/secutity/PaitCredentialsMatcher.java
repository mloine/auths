package com.mloine.auth.auths.secutity;


import com.mloine.auth.auths.entity.UserLoginInfo;
import com.mloine.auth.auths.service.impl.UserLoginService;
import com.mloine.auth.auths.utils.SpringContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Author  : Luda Zhuang
 * Date    : 2017/12/15
 * History :
 */
@Component
public class PaitCredentialsMatcher implements CredentialsMatcher {

    private final static Logger logger = LoggerFactory.getLogger(PaitCredentialsMatcher.class);

    // 在这里注入两个 Service 会导致启动时两个 Service 在扫描事物注解时被忽略，所以使用下面的 get 方法获取值
    private UserLoginService userLoginService;

//    private final UMUserService umUserService;
//
//    public PaitCredentialsMatcher(UMUserService umUserService) {
//        this.umUserService = umUserService;
//    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        boolean result = false;
        UserLoginInfo loginInfo = null;
        try {
            // 校验 AuthenticationInfo
            if (!(info instanceof SimpleAuthenticationInfo)) {
                logger.error("info[{}] is not instanceof SimpleAuthenticationInfo", info.getClass().getName());
                return false;
            }
            SimpleAuthenticationInfo simpleAuthenticationInfo = (SimpleAuthenticationInfo) info;
            Object primaryPrincipal = simpleAuthenticationInfo.getPrincipals().getPrimaryPrincipal();
            if (!(primaryPrincipal instanceof UserLoginInfo)) {
                logger.error("primaryPrincipal[{}] is not instanceof UserLoginInfo", info.getClass().getName());
                return false;
            }

            // 存储 UserLoginInfo，供后续更新记录使用
            loginInfo = (UserLoginInfo) primaryPrincipal;

            // 开始校验用户名和密码
             if (token instanceof UMCodePasswordToken) {
                result = doMatchUMCodePassword((UMCodePasswordToken) token, loginInfo);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = false;
        }
        if (loginInfo != null) {
            if (!result) {
                // 登陆失败
                getUserLoginService().handleLoginFail(loginInfo);
            } else {
                getUserLoginService().handleLoginSuccess(loginInfo);
            }
        }

        return result;
    }



    /**
     * 验证 UM 账号和密码
     * @param token 前端传入的 Token
     * @param userLoginInfo 完整的用户登陆信息
     * @return 校验是否通过
     */
    private boolean doMatchUMCodePassword(UMCodePasswordToken token, UserLoginInfo userLoginInfo) {
        try {
            if (StringUtils.isBlank(token.getUmCode())) {
                return false;
            }
            if (!token.getUmCode().equals(userLoginInfo.getUmCode())) {
                return false;
            }

            // return umUserService.authenticate(token.getUmCode(), new String(token.getPassword()));
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    private UserLoginService getUserLoginService() {
        if (userLoginService == null) {
            userLoginService = SpringContext.getBean(UserLoginService.class);
        }
        return userLoginService;
    }
}
