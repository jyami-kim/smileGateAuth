package com.ex.smile_authserver.Service;

import com.ex.smile_authserver.Dto.User;
import com.ex.smile_authserver.Mapper.UserMapper;
import com.ex.smile_authserver.Model.DefaultRes;
import com.ex.smile_authserver.Model.SignInReq;
import com.ex.smile_authserver.Model.SignUpReq;
import com.ex.smile_authserver.Util.ResponseMessage;
import com.ex.smile_authserver.Util.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.UUID;

@Service
@Slf4j
public class AuthService {

    private final UserMapper userMapper;

    public AuthService(final UserMapper userMapper){
        this.userMapper = userMapper;
    }

    //DefaultRes관련 method

    public DefaultRes login(final SignInReq signInReq){

        final User user = userMapper.findByEmail(signInReq.getEmail());
        if(user !=null){
            if(BCrypt.checkpw(signInReq.getPassword(), user.getPassword())){ //패스워드 확인
                return DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS, getToken());
            }
        }
        return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.LOGIN_FAIL);
    }


    // 회원 가입
    @Transactional
    public DefaultRes createUser(final SignUpReq signUpReq){
        try{
            if (signUpReq.getPassword() != null  && signUpReq.getEmail() != null) {
                final User user = userMapper.findByEmail(signUpReq.getEmail());
                if (user == null) {
                    try {
                        //암호화
                        log.info(signUpReq.getPassword());
                        //getsalt() : 숫자가 높아질수록 해쉬를 생성하고 검증하는 시간은 느려진다. 즉, 보안이 우수해진다. 하지만 그만큼 응답 시간이 느려지기 때문에 적절한 숫자를 선정해야 한다. 기본값은 10이다.
                        String passwordHashed = BCrypt.hashpw(signUpReq.getPassword(), BCrypt.gensalt());
                        signUpReq.setPassword(passwordHashed);

                        log.info(signUpReq.getPassword());

                        //mapper 사용
                        userMapper.createUser(signUpReq);
                        return DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATED_USER);
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
                    }
                } else return DefaultRes.res(StatusCode.CONFLICT, ResponseMessage.ALREADY_USER);
            }
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.INVALID_CREATED_USER);

        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    // token 발급 관련 method
    public String getToken(){
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }

}
