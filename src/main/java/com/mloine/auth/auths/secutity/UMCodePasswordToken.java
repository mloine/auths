package com.mloine.auth.auths.secutity;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Author     : Hran
 * Date       : 2018/1/11
 * Version    :
 * Description:
 */
public class UMCodePasswordToken implements AuthenticationToken {

    private String umCode;
    private char[] password;

    public UMCodePasswordToken() {}

    public UMCodePasswordToken(String umCode, char[] password) {
        this.setUmCode(umCode);
        this.setPassword(password);
    }

    @Override
    public Object getPrincipal() {
        return getUmCode();
    }

    @Override
    public Object getCredentials() {
        return getPassword();
    }

    public String getUmCode() {
        return umCode;
    }

    public void setUmCode(String umCode) {
        this.umCode = umCode;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
