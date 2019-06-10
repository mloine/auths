
package com.mloine.auth.auths.dao;




import com.mloine.auth.auths.entity.UserLoginInfo;
import com.mloine.auth.auths.entity.base.CommonMapper;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
@Mapper
public interface UserLoginInfoMapper extends CommonMapper<UserLoginInfo> {

//    @SelectProvider(type = UserLoginSQLProvider.class, method = "buildListBySearch")
//    @ResultMap("BaseResultVOMap")
//    List<UserInfoVO> listBySearch(@Param("searchType") String searchType,
//                                  @Param("searchText") String searchText,
//                                  @Param("classManagerId") Long classManagerId,
//                                  @Param("startValidTime") Date startValidTime,
//                                  @Param("endValidTime") Date endValidTime);

    @Select("SELECT puli.* FROM pait_user_login_info puli " +
            "LEFT JOIN pait_user_role_info puri ON puri.user_id = puli.user_id " +
            "LEFT JOIN pait_role_info pri ON pri.role_code = puri.role_id " +
            "WHERE pri.role_name = #{roleName}")
    @ResultMap("BaseResultMap")
    List<UserLoginInfo> listByRoleName(@Param("roleName") String roleName);

    @Select("SELECT COUNT(1) FROM pait_user_login_info puli " +
            "LEFT JOIN pait_user_role_info puri ON puri.user_id = puli.user_id " +
            "LEFT JOIN pait_role_info pri ON pri.role_code = puri.role_id " +
            "WHERE pri.role_name = #{roleName}")
    int countByRoleName(@Param("roleName") String roleName);
    


    @Select("SELECT * FROM pait_user_login_info WHERE class_name = #{className} LIMIT 1")
    @ResultMap("BaseResultMap")
    UserLoginInfo findClassByClassName(@Param("className") String className);

    @Select("SELECT\n" +
            "  DISTINCT class_name\n" +
            "FROM pait_user_login_info\n" +
            "WHERE class_type = #{classType}\n" +
            "GROUP BY class_name")
    List<String> listClassNoAndClassNameByClassType(@Param("classType") Long classType);

    @Update("UPDATE pait_user_login_info " +
            "SET fail_count     = COALESCE(fail_count, 0) + 1, " +
            "  last_login_time  = CURRENT_TIMESTAMP, " +
            "  update_time      = CURRENT_TIMESTAMP, " +
            "  update_user_code = user_id, " +
            "  update_user_name = user_name " +
            "WHERE user_id = #{userId}")
    int updateFailCountByUserId(@Param("userId") Long userId);

    @Update("UPDATE pait_user_login_info " +
            "SET fail_count     = 0, " +
            "  last_login_time  = CURRENT_TIMESTAMP, " +
            "  update_time      = CURRENT_TIMESTAMP, " +
            "  update_user_code = user_id, " +
            "  update_user_name = user_name " +
            "WHERE user_id = #{userId}")
    int updateSuccessStatusByUserId(@Param("userId") Long userId);

    @Select("SELECT COUNT(1) * 10 AS exp " +
            "FROM ( " +
            "       SELECT 1 " +
            "       FROM pait_train_result_count r " +
            "       WHERE r.score IS NOT NULL " +
            "             AND r.user_id = #{userId} " +
            "       GROUP BY date_format(end_time, '%Y%m%d') " +
            "       HAVING sum(longtime) >= 7200000 AND avg(score) >= 80 " +
            "     ) t")
    int selectUserExp(@Param("userId") Long userId);

    @Select("SELECT user_id FROM pait_user_login_info WHERE batch_no = #{batchNo}")
    List<Long> listUserIdByBatchNo(@Param("batchNo") String batchNo);

    @Select("SELECT user_id FROM pait_user_login_info WHERE class_name = #{className}")
    List<Long> listUserIdByClassName(@Param("className") String className);
    
    @Select("SELECT um_code FROM pait_user_login_info WHERE user_id=#{userId}")
    String  findUmCodeByUserId(@Param("userId") Long userId);
}
