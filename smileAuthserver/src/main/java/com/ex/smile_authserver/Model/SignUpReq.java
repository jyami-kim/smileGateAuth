package com.ex.smile_authserver.Model;

import lombok.Data;

@Data
public class SignUpReq {

    private String userName;
    private String email;
    private String password;

}
