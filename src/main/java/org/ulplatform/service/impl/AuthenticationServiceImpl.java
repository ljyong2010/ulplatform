package org.ulplatform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ulplatform.authorization.DefaultTokenManager;
import org.ulplatform.model.UserInfo;
import org.ulplatform.service.AuthenticationService;
import org.ulplatform.utils.StringUtil;
import org.ulplatform.utils.cache.RedisCache;

/**
 * Created by Administrator on 2017-7-8.
 */
@Component("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private RedisCache redisCache;

    @Override
    public JSONObject createTokenService(String userid) {
        JSONObject jsonObject = new JSONObject();
        DefaultTokenManager defaultTokenManager = new DefaultTokenManager();
        try {
            String token = defaultTokenManager.createToken(userid);
            redisCache.putCache(token,userid);
            jsonObject.put("token",token);
            jsonObject.put("code",0);
            return jsonObject;
        }catch (Exception e){
            LOGGER.error("create token fail!");
        }
        return null;
    }

    @Override
    public JSONObject sendListUserInfoService(String token, UserInfo userInfo) {

        JSONObject jsonObject = new JSONObject();
        if (StringUtil.isNotEmpty(redisCache.getCache(token))){
            String users = JSON.toJSONString(userInfo);
            if(redisCache.putCache(StringUtil.addPrefix(token),users)){
                jsonObject.put("msg","success");
                jsonObject.put("code",0);
                return jsonObject;
            }else {
                jsonObject.put("msg","save userinfo error!");
                jsonObject.put("code",1);
                return jsonObject;
            }
        }else {
            jsonObject.put("msg","token authentication fail!");
            jsonObject.put("code",2);
            return jsonObject;
        }
    }

    @Override
    public JSONObject unifauthService(String token, String ctype,String userName) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtil.isNotEmpty(redisCache.getCache(token))){
            String users = redisCache.getCache(StringUtil.addPrefix(token));
            if (StringUtil.isNotEmpty(users)){
                jsonObject=JSON.parseObject(users);
                if (StringUtil.isequals(ctype,jsonObject.getString("ctype"))&&StringUtil.isequals(userName,jsonObject.getString("userName"))){
                    jsonObject.put("code",0);
                    jsonObject.put("msg","success");
                    return jsonObject;
                }else {
                    jsonObject.put("code",1);
                    jsonObject.put("msg","type or userName authentication fail");
                    return jsonObject;
                }

            }else {
                jsonObject.put("code",2);
                jsonObject.put("msg","user info null");
                return jsonObject;
            }
        }else {
            jsonObject.put("code",3);
            jsonObject.put("msg","token authentication fail!");
            return jsonObject;
        }
    }
}
