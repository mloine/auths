
package com.mloine.auth.auths.secutity;



import com.mloine.auth.auths.entity.UserLoginInfo;
import com.mloine.auth.auths.service.impl.RoleService;
import com.mloine.auth.auths.service.impl.UserLoginService;
import com.mloine.auth.auths.utils.SpringContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Set;

/**
 * 身份证登录凭证获取
 */
//@Component
public class PaitAuthorizingRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(PaitAuthorizingRealm.class);

    // 在这里注入两个 Service 会导致启动时两个 Service 在扫描事物注解时被忽略，所以使用下面的 get 方法获取值
    private UserLoginService userLoginService;
    private RoleService roleService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return  token instanceof UMCodePasswordToken;
    }

    @Override
    public String getName() {
        return PaitAuthorizingRealm.class.getName();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (principal instanceof UserLoginInfo) {
            UserLoginInfo userLoginInfo = (UserLoginInfo) principal;
            Set<String> roles = getRoleService().findRoleNameByUserId(userLoginInfo.getUserId());
            authorizationInfo.addRoles(roles);

            Set<String> permissions = getUserLoginService().findPermissionsByUserId(userLoginInfo.getUserId());
            authorizationInfo.addStringPermissions(permissions);
        }
        logger.info("---- 获取到以下权限 ----");
        logger.debug(authorizationInfo.getStringPermissions().toString());
        logger.info("---------------- Shiro 权限获取成功 ----------------------");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("---------------- 执行 Shiro 凭证认证 ----------------------");

        UserLoginInfo userLoginInfo = new UserLoginInfo();

        if (authenticationToken instanceof UMCodePasswordToken) {
            UMCodePasswordToken umCodePasswordToken = (UMCodePasswordToken) authenticationToken;
            userLoginInfo.setUmCode(umCodePasswordToken.getUmCode());
        } else {
            throw new UnknownAccountException();
        }

        userLoginInfo = getUserLoginService().findOne(userLoginInfo);
        if (userLoginInfo != null) {

            // 用户为禁用状态
            if (!"1".equals(userLoginInfo.getUserStatus())) {
                throw new DisabledAccountException();
            }

            if (userLoginInfo.getValidTime() == null
                    || new Date().getTime() < userLoginInfo.getValidTime().getTime()
                    || userLoginInfo.getInvalidTime() == null
                    || new Date().getTime() > userLoginInfo.getInvalidTime().getTime()) {
                throw new DisabledAccountException();
            }

            // 若存在，将此用户存放到登录认证info中，稍后会在CredentialsMatcher中做校验
            byte[] password = null;
            if (StringUtils.isNotBlank(userLoginInfo.getPassword())) {
                password = Hex.decode(userLoginInfo.getPassword());
                userLoginInfo.setPassword(null);
            }
            logger.info("---------------- Shiro 凭证认证成功 ----------------------");
            return new SimpleAuthenticationInfo(userLoginInfo, password, getName());
        }

        throw new UnknownAccountException();
    }

    private RoleService getRoleService() {
        if (roleService == null) {
            roleService = SpringContext.getBean(RoleService.class);
        }
        return roleService;
    }

    private UserLoginService getUserLoginService() {
        if (userLoginService == null) {
            userLoginService = SpringContext.getBean(UserLoginService.class);
        }
        return userLoginService;
    }
}
