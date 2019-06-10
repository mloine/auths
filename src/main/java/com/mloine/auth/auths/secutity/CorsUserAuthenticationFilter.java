package com.mloine.auth.auths.secutity;


import com.mloine.auth.auths.utils.JsonUtils;
import com.mloine.auth.auths.utils.RequestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Author  : Luda Zhuang
 * Date    : 2017/12/26
 * History :
 */
public class CorsUserAuthenticationFilter extends UserFilter {

//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        return super.isAccessAllowed(request, response, mappedValue);
//    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        RequestUtils.addCorsHeader();
       // String json = JsonUtils.encode(Result.error(ResponseMessageConstant.Code.NEED_LOGIN.getCode(), "未登录"));
        String json = JsonUtils.encode( "未登录");


        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            out = response.getWriter();
            out.println(json);
        } finally {
            IOUtils.closeQuietly(out);
        }
        return false;
    }
}
