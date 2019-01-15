package com.ex.smile_authserver.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class UserCache implements Serializable {
    private int userId;
    private int level; //0 : 일반회원, 1 :  관리자
    private int status;  //0 : ok(정상), 1 : hold (정지), 2 : delete (삭제)
}
