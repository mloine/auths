package com.mloine.auth.auths.controller;


import com.mloine.auth.auths.secutity.UMCodePasswordToken;
import com.mloine.auth.auths.service.impl.UserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 权限验证
 */

@RestController
@Api(tags = "登录接口")
//@RequestMapping(value = "/auth", produces = "application/json")
@RequestMapping(value = "/auth")
public class AuthController {


    private final UserLoginService userLoginService;
    @Autowired
    public AuthController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

//    @GetMapping("login")
//    @ApiIgnore
//    public ResponseMessageVO toLogin() {
//        Subject user = SecurityUtils.getSubject();
//        if (user.isAuthenticated()) {
//            return Result.success(RequestUtils.currentLoginUser().newSubClassInstance(UserInfoVO.class));
//        }
//
//        return Result.error(ResponseMessageConstant.Code.NEED_LOGIN.getCode(), "未登录");
//    }

    @PostMapping("login")
    @ApiOperation(value = "登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "cardNo", value = "身份证号码"),
            @ApiImplicitParam(paramType = "query", name = "umCode", value = "UM 账号"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码"),
    })
    public String doLogin(@RequestParam(name = "cardNo", required = false) String cardNo,
                                     @RequestParam(name = "umCode", required = false) String umCode,
                                     @RequestParam(name = "password") String password) {
        Subject user = SecurityUtils.getSubject();
       
        AuthenticationToken token = null;

        if (StringUtils.isNotBlank(cardNo)) {
           // token = new CardNoPasswordToken(cardNo, ByteSource.Util.bytes(password).getBytes());
        } else if (StringUtils.isNotBlank(umCode)) {
            token = new UMCodePasswordToken(umCode, password.toCharArray ());

        } else {
            return "登录方式不支持";
        }

        try {
            user.login(token);
        } catch (ExcessiveAttemptsException e) {
            return"登录失败次数已达最大限制";
        } catch (LockedAccountException e) {
            return "账户已被锁定";
        } catch (DisabledAccountException e) {
            return "账户已被禁用";
        } catch (AuthenticationException e) {
            return "用户名或密码错误";
        }
        //UserInfoVO infoVO = RequestUtils.currentLoginUser().newSubClassInstance(UserInfoVO.class);
       // UserInfoVO infoVO = new UserInfoVO();
        //BeanUtils.copyProperties(user.getPrincipal(),infoVO);
        //infoVO = userLoginService.updateUserExtendValue(infoVO);

        //infoVO.setTrainConversationWaitTime(3);
        //infoVO.setTrainQuestionWaitTime(5);

        //return infoVO.toString();
        return "success";

    }

//    @ApiIgnore
//    @GetMapping("/loginSuccess")
//    public ResponseMessageVO loginSuccessMessage() {
//        return Result.success();
//    }
//
//    @ApiIgnore
//    @GetMapping("/returnUnauthorizedMsg")
//    public ResponseMessageVO returnUnauthorizedMsg() {
//        return Result.error(ResponseMessageConstant.Code.NEED_LOGIN.getCode(), "未登录");
//    }

}