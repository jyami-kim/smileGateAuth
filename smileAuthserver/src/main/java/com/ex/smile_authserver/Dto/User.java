package com.ex.smile_authserver.Dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User
{
    private int userId;
    private String userName;
    private String email;
    private String password;
    private int level; //0 : 일반회원, 1 :  관리자
    private int status;  //0 : ok(정상), 1 : hold (정지), 2 : delete (삭제)
    private Timestamp createTime;
    private Timestamp updateTime;
}
