package org.ulplatform.authorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.ulplatform.utils.CodecUtil;
import org.ulplatform.utils.StringUtil;
import org.ulplatform.utils.cache.RedisCache;

/**
 * Created by Administrator on 2017-7-8.
 */
public class DefaultTokenManager implements TokenManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultTokenManager.class);

    @Autowired
    private RedisCache redisCache;

    @Override
    public String createToken(String username) {
        String token = CodecUtil.createUUID().replace("-","");
        /*redisCache.putCacheWithExpireTime(token,username,10);*/
        return token;
    }
}
