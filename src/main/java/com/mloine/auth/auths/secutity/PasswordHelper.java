package com.mloine.auth.auths.secutity;

import com.mloine.auth.auths.entity.UserLoginInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.ConfigurableHashService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;

/**
 * Author  : Luda Zhuang
 * Date    : 2017/12/15
 * History :
 */
public class PasswordHelper {
    public final static String ALGORITHM_NAME = "md5";
    public final static int ITERATION_TIMES = 2;

    private static ConfigurableHashService hashService = null;

    static {
        hashService = new DefaultHashService();
        hashService.setHashAlgorithmName(ALGORITHM_NAME);
        hashService.setHashIterations(ITERATION_TIMES);
    }

    public static String generateInitPasswordByUserLoginInfo(UserLoginInfo info, String newPassword) {
        String password = getInitPassword(info, newPassword);
        password = preparePasswordWithFrontEndEncrypt(password);
        return encryptPasswordHex(password, null);
    }

    public static String getInitPassword(UserLoginInfo userLoginInfo, String newPassword) {
        if (userLoginInfo == null) {
            return null;
        }
        if (StringUtils.isNotBlank(newPassword)) {
            return newPassword;
        }
        if (StringUtils.isNotBlank(userLoginInfo.getCardNo())) {
            return StringUtils.right(userLoginInfo.getCardNo(), 6);
        }
        if (StringUtils.isNotBlank(userLoginInfo.getUmCode())) {
            return null;
        }
        return null;
    }

    public static void main(String[] args) {
        UserLoginInfo user = new UserLoginInfo();
        user.setUmCode("10001");
        user.setPassword("111111");
        System.out.println(PasswordHelper.generateInitPasswordByUserLoginInfo(user, "111111"));
    }

    public static String preparePasswordWithFrontEndEncrypt(String plainTextPassword) {
        return plainTextPassword;
    }


    public static byte[] encryptPassword(Object plaintextPassword, Object salt) {
        if (plaintextPassword == null) {
            return null;
        }
        return doComputeHash(plaintextPassword, salt).getBytes();
    }

    public static String encryptPasswordHex(Object plaintextPassword, Object salt) {
        if (plaintextPassword == null) {
            return null;
        }
        return doComputeHash(plaintextPassword, salt).toHex();
    }

    private static Hash doComputeHash(Object plaintextPassword, Object salt) {
        Object source;
        if (plaintextPassword instanceof byte[]) {
            source = plaintextPassword;
        } else if (plaintextPassword instanceof String) {
            source = ByteSource.Util.bytes(plaintextPassword).getBytes();
        } else {
            source = plaintextPassword;
        }
        HashRequest.Builder builder = new HashRequest.Builder();
        builder.setAlgorithmName(ALGORITHM_NAME)
                .setSource(source)
                .setIterations(ITERATION_TIMES);
        if (salt != null) {
            builder.setSalt(salt);
        }
        return hashService.computeHash(builder.build());
    }
}
