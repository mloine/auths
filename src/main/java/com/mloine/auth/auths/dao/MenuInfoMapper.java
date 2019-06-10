package com.mloine.auth.auths.dao;

import com.mloine.auth.auths.entity.MenuInfo;
import com.mloine.auth.auths.entity.base.CommonMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;
@Mapper
public interface MenuInfoMapper extends CommonMapper<MenuInfo> {
    @Select("SELECT * FROM pait_menu_info m " +
            "LEFT JOIN pait_role_menu_info rm  ON m.menu_id = rm.menu_id " +
            "WHERE m.menu_status = 1 " +
            "AND rm.role_id = #{roleId}")
    @ResultMap("BaseResultMap")
    List<MenuInfo> findByRoleId(@Param("roleId") Long roleId);

   // @Lang(SelectExtendedLanguageDriver.class)
    @Select("SELECT DISTINCT m.* FROM pait_menu_info m " +
            "LEFT JOIN pait_role_menu_info rm  ON m.menu_id = rm.menu_id " +
            "WHERE m.menu_status = 1 " +
            "AND rm.role_id IN (#{roleIds})")
    @ResultMap("BaseResultMap")
    List<MenuInfo> findByRoleIds(@Param("roleIds") List<Long> roleIds);

    @Select("SELECT m.* " +
            "FROM pait_user_login_info u " +
            "LEFT JOIN pait_user_role_info ur ON u.user_id = ur.user_id " +
            "LEFT JOIN pait_role_info r ON r.role_code = ur.role_id " +
            "LEFT JOIN pait_role_menu_info rm ON rm.role_id = r.role_code " +
            "LEFT JOIN pait_menu_info m ON m.menu_id = rm.menu_id " +
            "WHERE u.user_id = #{userId} " +
            "ORDER BY m.sort_num ASC")
    @ResultMap("BaseResultMap")
    List<MenuInfo> findByUserId(@Param("userId") Long userId);

    @Select("SELECT m.menu_permissions " +
            "FROM pait_user_login_info u " +
            "LEFT JOIN pait_user_role_info ur ON u.user_id = ur.user_id " +
            "LEFT JOIN pait_role_info r ON r.role_code = ur.role_id " +
            "LEFT JOIN pait_role_menu_info rm ON rm.role_id = r.role_code " +
            "LEFT JOIN pait_menu_info m ON m.menu_id = rm.menu_id " +
            "WHERE u.user_id = #{userId}")
    Set<String> findPermissionsByUserId(@Param("userId") Long userId);
}