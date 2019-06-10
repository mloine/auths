package com.mloine.auth.auths.service.impl;

import java.util.List;

/**
 * Author  : Luda Zhuang
 * Date    : 2017/12/29
 * History :
 */
public interface RedisService {
    boolean set(String key, String value);

    String get(String key);

    boolean expire(String key, long expire);

    <T> boolean setList(String key, List<T> list);

    <T> List<T> getList(String key, Class<T> clz);

    long lPush(String key, Object obj);

    long rPush(String key, Object obj);

    String lPop(String key);
}
