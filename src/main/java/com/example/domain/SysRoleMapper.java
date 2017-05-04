package com.example.domain;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by admin on 17/4/24.
 */
@Mapper
public interface SysRoleMapper {

    @Select("SELECT * FROM sys_role")
    List<SysRole> findAll();

    @Select("SELECT name FROM sys_role")
    List<String> findAllName();

    @Select("select t1.* from sys_role t1 left join sys_user_role t2 on t1.id=t2.role_id where t2.user_id = #{userId}")
    List<SysRole> findByUserId(@Param("userId") int userId);

    @Select("select * from sys_role where id in (${roleIds})")
    List<SysRole> findByRoleIds(@Param("roleIds") String roleIds);
}
