package com.mloine.auth.auths.service.impl;


import com.mloine.auth.auths.entity.RoleInfo;

import java.util.Set;

/**
 * Author  : Luda Zhuang
 * Date    : 2017/12/18
 * History :
 */
public interface RoleService extends BaseService<RoleInfo> {
    /**
     * 判断角色是否存在
     * @param roleName 角色名称
     * @return
     */
    boolean exist(String roleName);

    /**
     * 获取用户角色
     * @param userId userId
     * @return
     */
    Set<String> findRoleNameByUserId(Long userId);
}
