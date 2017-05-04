package com.example.domain;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by admin on 17/4/24.
 */
@Mapper
public interface SysUserRoleMapper {

    @Select("SELECT * FROM sys_user_role where user_id = #{userId}")
    @Results(
            {
                    @Result(id = true, column = "id", property = "id"),
                    @Result(column = "user_id", property = "userId"),
                    @Result(column = "role_id", property = "roleId"),
            })
    List<SysUserRole> findByUserId(@Param("userId") int userId);
}
