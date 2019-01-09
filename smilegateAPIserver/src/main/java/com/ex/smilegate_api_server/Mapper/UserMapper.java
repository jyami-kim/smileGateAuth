package com.ex.smilegate_api_server.Mapper;

import org.apache.ibatis.annotations.*;

import com.ex.smilegate_api_server.Dto.User;

import java.util.List;

@Mapper
public interface UserMapper {


    //readOne
    @Select("SELECT * FROM user WHERE userId = #{userId};")
    User readOneUser(@Param("userId") final int userId);

    //readAll
    @Select("SELECT * FROM user")
    List<User> readAllUser();

    //update
    @Update("UPDATE user SET password= #{password} WHERE userId=#{userId};")
    void updateUser(@Param("password") final String password, @Param("userId") final int userId);

    //delete
    @Delete("DELETE FROM user WHERE userId=#{userId};")
    void deleteUser(@Param("userId") final int userId);




}

