package com.example.domain;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by admin on 17/4/24.
 */
@Mapper
public interface SysUserMapper {

    @Select("SELECT * FROM USER WHERE ID = #{id}")
    SysUser findById(@Param("id") Long id);

    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    SysUser findByName(@Param("name") String name);

    @Select("SELECT * FROM USER")
    List<SysUser> findAll();

    @Insert("INSERT INTO USER(NAME, AGE,email) VALUES(#{name}, #{age}, #{email})")
    int insert(@Param("name") String name, @Param("age") Integer age, @Param("email") String email);

    @Update("UPDATE USER SET NAME = #{name}, AGE = #{age}, email = #{email} WHERE ID = #{id}")
    void update(@Param("id") Long id, @Param("name") String name, @Param("age") Integer age, @Param("email") String email);

    @Delete("DELETE FROM USER WHERE ID = #{id}")
    void deleteById(@Param("id") Long id);
}
