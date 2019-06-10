package com.mloine.auth.auths.service;

import com.mloine.auth.auths.dao.RoleInfoMapper;
import com.mloine.auth.auths.entity.RoleInfo;
import com.mloine.auth.auths.service.impl.RoleService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Author  : Luda Zhuang
 * Date    : 2017/12/18
 * History :
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleInfoMapper, RoleInfo> implements RoleService {
    @Override
    public boolean exist(String roleName) {
        RoleInfo info = new RoleInfo();
        info.setRoleName(roleName);
        return dao.selectCount(info) > 0;
    }

    @Override
    public Set<String> findRoleNameByUserId(Long userId) {
        return dao.findRoleNameByUserId(userId);
    }
}
