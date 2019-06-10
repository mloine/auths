package com.mloine.auth.auths.secutity;


import sun.misc.BASE64Decoder;

/**
 * 替换 Jasypt 默认的密码加解密器，替换加解密方法及秘钥和 SCA 相同
 * Author  : Luda Zhuang
 * Date    : 2018/1/10
 * History :
 */
//public class PropertiesValueEncryptor implements StringEncryptor {
//
//    private final byte[] KEY = new byte[] {51, 53, 100, 51, 102, 102, 48, 97, 50, 50, 97, 48, 101, 98, 100, 56, 56, 100, 97, 53, 101, 100, 57, 56};
//
//    @Override
//    public String encrypt(String message) {
//        try {
//            byte[] encrypt = Des3EncryptUtils.encrypt(KEY, message.getBytes());
//            return Base64.encodeBase64String(encrypt);
//        } catch (Exception e) {
//            throw new SystemConfigurationException(e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public String decrypt(String encryptedMessage) {
//        try {
//            byte[] decodeBuffer = new BASE64Decoder().decodeBuffer(encryptedMessage);
//            byte[] decrypt = Des3EncryptUtils.decrypt(KEY, decodeBuffer);
//            return new String(decrypt);
//        } catch (Exception e) {
//            throw new SystemConfigurationException(e.getMessage(), e);
//        }
//    }
//
//}
