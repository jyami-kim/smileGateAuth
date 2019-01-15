package com.ex.smile_authserver.Api;

import com.ex.smile_authserver.Model.SignInReq;
import com.ex.smile_authserver.Service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ex.smile_authserver.Model.DefaultRes.FAIL_DEFAULT_RES;

@Slf4j
@RestController
@RequestMapping("/signIn")
public class SignInController {

    private AuthService authService;

    SignInController (final AuthService authService){
        this.authService = authService;
    }

    @PostMapping
    ResponseEntity loginUser (@RequestBody SignInReq signInReq){
        try{
            return new ResponseEntity<>(authService.login(signInReq), HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseEntity(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
