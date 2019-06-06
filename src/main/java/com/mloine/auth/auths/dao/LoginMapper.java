package com.mloine.auth.auths.dao;

import com.mloine.auth.auths.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select * from user where id = #{id}")
    public User getDeptById(Integer id);




}
