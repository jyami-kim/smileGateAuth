package com.ex.smilegate_api_server.Api;

import com.ex.smilegate_api_server.Dto.User;
import com.ex.smilegate_api_server.Model.DefaultRes;
import com.ex.smilegate_api_server.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private UserService userService;

    UserController(final UserService userService){
        this.userService = userService;
    }

    // 회원 조회 ( querifier 사용해볼까? )
    @GetMapping(path = {"/{userId}", ""})
    ResponseEntity getUser(@PathVariable Optional<Integer> userId){
        try{
            if(userId.isPresent()){ //일반 회원 한명 조회 (일반인)
                // url :  localhost:3030/user/1
                return new ResponseEntity(userService.readOne(userId.get()), HttpStatus.OK);
            }else{// 회원 전체 조회 (관리자)
                // url :  localhost:3030/user
                return new ResponseEntity(userService.readAll(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 회원 수정
    @PutMapping("/{userId}")
    ResponseEntity updateUser(@RequestBody final User user, @PathVariable(value= "userId") final int userId){
        try{
            return new ResponseEntity(userService.updateUser(userId, user), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 회원 탈퇴
    @DeleteMapping("/{userId}")
    ResponseEntity deleteUser(@PathVariable(value = "userId") final int userId){
        try{
            return new ResponseEntity(userService.deleteUser(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
