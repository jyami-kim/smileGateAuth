package com.ex.smile_authserver.Repository;

import com.ex.smile_authserver.Dto.User;
import com.ex.smile_authserver.Model.UserCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

public interface UserRepository {
    UserCache findByUserIdNoCache(int userId);
    UserCache findByUserIdCache(int userId);
    void refresh(int userId);
}
