package com.example.domain;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by admin on 17/4/24.
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USER WHERE ID = #{id}")
    User findById(@Param("id") Long id);

    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    User findByName(@Param("name") String name);

    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    @Select("SELECT name,age FROM USER")
    List<User> findAll();

    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    @Update("UPDATE USER SET NAME = #{name}, AGE = #{age} WHERE ID = #{id}")
    void update(@Param("id") Long id,@Param("name") String name, @Param("age") Integer age);

    @Delete("DELETE FROM USER WHERE ID = #{id}")
    void deleteById(@Param("id") Long id);
}
