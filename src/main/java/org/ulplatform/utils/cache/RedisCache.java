package org.ulplatform.utils.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.Set;

/**
 * Created by Administrator on 2017-7-8.
 */
@Component
public class RedisCache {

    public final static String CAHCENAME="cache";//缓存名
    public final static int CAHCETIME=600;//默认缓存时间

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public boolean putCache(String key, String obj) {
        final byte[] bkey = key.getBytes();
        final byte[] bvalue = obj.getBytes();
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.setNX(bkey, bvalue);
            }
        });
        return result;
    }
    public  boolean putCacheWithExpireTime(String key, String obj, final long expireTime) {
        final byte[] bkey = key.getBytes();
        final byte[] bvalue = obj.getBytes();
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setEx(bkey, expireTime, bvalue);
                return true;
            }
        });
        return result;
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean exitKey(String key){
        final byte[] bkey = key.getBytes();
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            boolean ret = false;
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                ret = connection.exists(bkey);
                return ret;
            }
        });
        return result;
    }
    /**
     * update
     * @param key
     * @param value
     * @return
     */
    public boolean updateCache(final String key,final String value){
        if (key==null){
            throw new NullPointerException("key is null");
        }
        final byte[] bkey=key.getBytes();
        final byte[] bvalue=value.getBytes();
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set(bkey,bvalue);
                return true;
            }
        });
        return result;
    }
    public String getCache(final String key) {
        byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(key.getBytes());
            }
        });
        if (result == null) {
            return null;
        }
        return new String(result);
    }
    /**
     * 精确删除key
     *
     * @param key
     */
    public void deleteCache(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 模糊删除key
     *
     * @param pattern
     */
    public void deleteCacheWithPattern(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }

    /**
     * 清空所有缓存
     */
    public void clearCache() {
        deleteCacheWithPattern(RedisCache.CAHCENAME+"|*");
    }
}
