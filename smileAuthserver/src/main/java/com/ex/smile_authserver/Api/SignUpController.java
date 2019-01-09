package com.ex.smile_authserver.Api;

import com.ex.smile_authserver.Model.DefaultRes;
import com.ex.smile_authserver.Model.SignUpReq;
import com.ex.smile_authserver.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/signUp")
public class SignUpController {

    private AuthService authService;

    SignUpController (final AuthService authService){
        this.authService = authService;
    }

    // 회원 가입
    @PostMapping("")
    ResponseEntity saveUser(@RequestBody final SignUpReq signUpReq){
        try{
            return new ResponseEntity(authService.createUser(signUpReq), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
