package com.ex.smile_authserver.Api;

import com.ex.smile_authserver.Dto.User;
import com.ex.smile_authserver.Model.UserCache;
import com.ex.smile_authserver.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@EnableCaching // 어노테이션을 이용한 캐시기능을 사용하겠다는 선언
@RestController
@RequestMapping("cache")
public class CacheController {

    private UserRepository userRepository;

    public CacheController(final UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/nothave/{id}")
    public ResponseEntity getNoCacheMember(@PathVariable (value = "id")final int userId){
        long start = System.currentTimeMillis();
        UserCache userCache = userRepository.findByUserIdNoCache(userId);
        long end = System.currentTimeMillis();

        log.info(userId + "의 NoCache 수행시간 : "+ Long.toString(end-start));

        return new ResponseEntity(userCache, HttpStatus.OK);
    }

    @GetMapping("/have/{id}")
    public ResponseEntity getCacheMember(@PathVariable (value = "id")final int userId){
        long start = System.currentTimeMillis();
        UserCache userCache = userRepository.findByUserIdCache(userId);
        long end = System.currentTimeMillis();

        log.info(userId + "의 NoCache 수행시간 : "+ Long.toString(end-start));

        return new ResponseEntity(userCache, HttpStatus.OK);
    }

    @GetMapping("/refresh/{id}")
    public ResponseEntity refresh(@PathVariable (value = "id")final int userId){
        userRepository.refresh(userId); // 캐시제거
        log.info("cache clear!!!");

        return new ResponseEntity(HttpStatus.OK);
    }

}
