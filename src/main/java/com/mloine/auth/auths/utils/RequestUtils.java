package com.mloine.auth.auths.utils;


import com.mloine.auth.auths.entity.UserLoginInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RequestUtils {

    private static Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    public static HttpServletRequest currentHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse currentHttpServletResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    protected static void fillHeaders(HttpServletRequest request, StringBuilder requestDetail) {
        @SuppressWarnings("rawtypes")
        Enumeration headers = request.getHeaderNames();
        requestDetail.append("Rquest Headers:\n");
        while (headers.hasMoreElements()) {
            String header = (String) headers.nextElement();
            requestDetail.append(header).append(": ").append(request.getHeader(header)).append("\n");
        }
        requestDetail.append("\n");// 换行再加一个空行
    }

    protected static void fillCookies(HttpServletRequest request, StringBuilder requestDetail) {
        Cookie[] cookies = request.getCookies();
        requestDetail.append("Cookie: ");
        for (int i = 0, l = cookies.length; i < l; i++) {
            if (i > 0) {
                requestDetail.append("; ");
            }
            Cookie cookie = cookies[i];
            requestDetail.append(cookie.getName()).append("=").append(cookie.getValue());
        }
        requestDetail.append("\n\n");// 换行再加一个空行
    }

    public static void fillParameters(HttpServletRequest request, StringBuilder requestDetail) {
        @SuppressWarnings("rawtypes")
        Map map = request.getParameterMap();
        @SuppressWarnings("rawtypes")
        Set keys = map.keySet();
        logger.debug("request里的参数的个数:{}", map.size());
        if (keys != null && !keys.isEmpty()) {
            int len = keys.size();
            @SuppressWarnings("rawtypes")
            Iterator keyItr = keys.iterator();
            while (keyItr.hasNext()) {
                String key = (String) keyItr.next();
                if (--len > 0) {
                    requestDetail.append("&");
                }
                arrayToString(key, (String[]) map.get(key), requestDetail);
            }
        } else {
            requestDetail.append("-----------Parameter is null------------");
        }
    }

    protected static void arrayToString(String key, String[] values, StringBuilder requestDetail) {
        for (int i = 0, l = values.length; i < l; i++) {
            if (i > 0) {
                requestDetail.append("&");
            }
            requestDetail.append(key).append("=").append(values[i]);
        }
    }

    /**
     * 判断是否是Ajax请求
     *
     * @return bool
     */
    public static boolean isAjaxRequest() {
        return (currentHttpServletRequest().getHeader("X-Requested-With") != null && currentHttpServletRequest().getHeader("X-Requested-With").contains("XMLHttpRequest"));
    }

    /**
     * 判断是否是文件上传的请求。
     *
     * @return bool
     */
    public static boolean isFileUploadRequest() {
        return currentHttpServletRequest().getContentType().contains("multipart/form-data");
    }

    /**
     * 添加跨域请求头
     */
    public static void addCorsHeader() {
        HttpServletResponse response = currentHttpServletResponse();
        HttpServletRequest request = currentHttpServletRequest();
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader(HttpHeaders.ORIGIN));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Max-Age", "*");
    }


    public static UserLoginInfo currentLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Object principal = subject.getPrincipals().getPrimaryPrincipal();
            if (principal instanceof UserLoginInfo) {
                return (UserLoginInfo) principal;
            }
        }
        // throw new BusinessException(ResponseMessageConstant.Code.NEED_LOGIN.getCode(), "未登录");

        return null;
    }

}


