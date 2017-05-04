package com.example.domain;

import org.apache.ibatis.annotations.*;

/**
 * Created by admin on 17/5/2.
 */
@Mapper
public interface RememberMeMapper {


    @Insert("insert into remember_me(user_name,series,token_value,date) VALUES(#{me.username},#{me.series},#{me.tokenValue},#{me.date}) ")
    @Options(useGeneratedKeys = true, keyProperty = "me.id")
    void insert(@Param("me") RememberMe rememberMe);

    void updateByPK(RememberMe rememberMe);

    @Select("select * from remember_me where series=#{series}")
    RememberMe selectByPK(@Param("series")String series);

    @Delete("delete from remember_me where user_name=#{username}")
    void deleteByPK(@Param("username") String username);
}
