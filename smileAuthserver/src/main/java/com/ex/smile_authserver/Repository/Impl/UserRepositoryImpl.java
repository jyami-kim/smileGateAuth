package com.ex.smile_authserver.Repository.Impl;

import com.ex.smile_authserver.Dto.User;
import com.ex.smile_authserver.Model.UserCache;
import com.ex.smile_authserver.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {
    private static Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Override
    public UserCache findByUserIdNoCache(int userId) {
        slowQuery(2000);
        return new UserCache(userId, 0,0 );
    }

    @Override
    @Cacheable(value="UserCache", key="#userId")
    public UserCache findByUserIdCache(int userId) {
        slowQuery(2000);

        return new UserCache(userId, 0, 0);
    }

    @Override
    @CacheEvict(value = "UserCache", key="#userId")
    public void refresh(int userId) {
        logger.info(userId + "의 Cache Clear!");
    }

    // 빅쿼리를 돌린다는 가정
    private void slowQuery(long seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
