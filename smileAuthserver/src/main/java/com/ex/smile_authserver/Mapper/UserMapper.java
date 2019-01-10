package com.ex.smile_authserver.Mapper;

import com.ex.smile_authserver.Dto.User;
import com.ex.smile_authserver.Model.SignUpReq;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    //회원 이메일로 조회
    @Select("SELECT * FROM user WHERE email = #{email};")
    User findByEmail(@Param("email") final String email);

    //create
    @Insert("INSERT INTO user (userName, email, password) VALUES (#{signUpReq.userName}, #{signUpReq.email}, #{signUpReq.password});")
    void createUser(@Param("signUpReq") final SignUpReq signUpReq);

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
