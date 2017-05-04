package com.example.domain;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by admin on 17/4/24.
 */
@Mapper
public interface SysFunctionMapper {

    @Select("select t1.id,t1.function_url,t1.function_name from sys_function t1 left join sys_role_function t2 on t1.id=t2.function_id where t2.role_id = #{roleId}")
    @Results(
            {
                    @Result(id = true, column = "id", property = "id"),
                    @Result(column = "function_url", property = "functionUrl"),
                    @Result(column = "function_name", property = "functionName"),
            })
    List<SysFunction> findByRoleId(@Param("roleId") int roleId);

    @Select("select t1.id,t1.function_url,t1.function_name from sys_function t1 left join sys_role_function t2 on t1.id=t2.function_id where t2.role_id in (${roleIds})")
    @Results(
            {
                    @Result(id = true, column = "id", property = "id"),
                    @Result(column = "function_url", property = "functionUrl"),
                    @Result(column = "function_name", property = "functionName"),
            })
    List<SysFunction> findByRoleIds(@Param("roleIds") String roleIds);

    @Select("select t2.role_id from sys_function t1 left join sys_role_function t2 on t1.id=t2.function_id where t1.function_url = #{uri}")
    List<Integer> findRoleIdsByUri(@Param("uri") String uri);
}
