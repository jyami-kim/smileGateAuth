package com.ex.smilegate_api_server.Service;

import com.ex.smilegate_api_server.Dto.User;
import com.ex.smilegate_api_server.Mapper.UserMapper;
import com.ex.smilegate_api_server.Model.DefaultRes;
import com.ex.smilegate_api_server.Util.ResponseMessage;
import com.ex.smilegate_api_server.Util.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private UserMapper userMapper;


    UserService(final UserMapper userMapper){
        this.userMapper = userMapper;
    }

    // 회원 조회 (관리자)
    public DefaultRes readAll(){
        List<User> userList = userMapper.readAllUser();
        if(!userList.isEmpty()){
            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, userList);
        }
        return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.READ_EMPTY_USER);

    }


    // 회원 조회 (일반회원)
    public DefaultRes readOne(final int userId){
        User user = userMapper.readOneUser(userId);
        if(user!=null){
            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, user);
        }
        return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.READ_EMPTY_USER, user);
    }




    // 회원 수정
    @Transactional
    public DefaultRes updateUser(final int userId, final User user){ //password 수정이지만, 후에 더 컨텐츠가 추가되면 user로 받기 위함
        try{
            if(userId != 0 && user != null){
                userMapper.updateUser(user.getPassword(), userId);
                return DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_USER);
            }
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.INVALID_INFO);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    // 회원 탈퇴
    @Transactional
    public DefaultRes deleteUser(final int userId){
        try{
            if(userId != 0){
                userMapper.deleteUser(userId);
                return DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_USER);
            }
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.INVALID_INFO);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

}
