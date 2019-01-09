package com.ex.smile_authserver.Mapper;

import com.ex.smile_authserver.Dto.User;
import com.ex.smile_authserver.Model.SignUpReq;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    //회원 이메일로 조회
    @Select("SELECT * FROM user WHERE email = #{email};")
    User findByEmail(@Param("email") final String email);

    //create

    @Insert("INSERT INTO user (userName, email, password) VALUES (#{signUpReq.userName}, #{signUpReq.email}, #{signUpReq.password});")
    void createUser(@Param("signUpReq") final SignUpReq signUpReq);


}
