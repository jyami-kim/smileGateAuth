package com.ex.smilegate_api_server.Dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private int userId;
    private String userName;
    private String email;
    private String password;
    private Timestamp createTime;
    private Timestamp updateTime;
}
