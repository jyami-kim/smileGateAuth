package com.ex.smile_authserver.Model;

import lombok.Data;

@Data
public class SignInReq {
    private String email;
    private String password;
}
