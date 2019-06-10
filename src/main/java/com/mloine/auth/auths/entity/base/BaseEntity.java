package com.mloine.auth.auths.entity.base;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;

//import org.springframework.beans.BeanUtils;

/**
 * Author  : Luda Zhuang
 * Date    : 2017/12/18
 * History :
 */
public abstract class BaseEntity<T extends BaseEntity<T>> implements Serializable {
    public <D extends T> D newSubClassInstance(Class<D> clazz) {
        try {
            D instance = clazz.newInstance();
            BeanUtils.copyProperties(instance, this);
            //BeanUtils.copyProperties(this,instance);
            return instance;
        } catch (Exception e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }
}
