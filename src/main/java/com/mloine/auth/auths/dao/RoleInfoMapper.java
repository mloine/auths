package com.mloine.auth.auths.dao;


import com.mloine.auth.auths.entity.RoleInfo;
import com.mloine.auth.auths.entity.base.CommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.Set;
@Mapper
public interface RoleInfoMapper extends CommonMapper<RoleInfo> {
    @Select("SELECT r.role_name " +
            "FROM pait_user_login_info u " +
            "LEFT JOIN pait_user_role_info ur ON u.user_id = ur.user_id " +
            "LEFT JOIN pait_role_info r ON r.role_code = ur.role_id " +
            "WHERE u.user_id = #{userId}")
    Set<String> findRoleNameByUserId(@Param("userId") Long userId);


    @Select("SELECT r.* " +
            "FROM pait_user_login_info u " +
            "LEFT JOIN pait_user_role_info ur ON u.user_id = ur.user_id " +
            "LEFT JOIN pait_role_info r ON r.role_code = ur.role_id " +
            "WHERE u.user_id = #{userId}")
    @ResultMap("BaseResultMap")
    Set<String> findRolesByUserId(@Param("userId") Long userId);
}