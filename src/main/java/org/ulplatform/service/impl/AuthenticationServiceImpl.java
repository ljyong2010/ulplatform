package org.ulplatform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ulplatform.authorization.DefaultTokenManager;
import org.ulplatform.model.OauthInfo;
import org.ulplatform.model.UserInfo;
import org.ulplatform.service.AuthenticationService;
import org.ulplatform.utils.Constant;
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
    public JSONObject createTokenService(String userId,String systemId,String urlType) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtil.isequals(urlType,"hyjkoa")){
            DefaultTokenManager defaultTokenManager = new DefaultTokenManager();
            String token="";
            try {
                    token = defaultTokenManager.createToken(userId);
                    redisCache.putCacheWithExpireTime(token,userId+systemId,Constant.CACHEEXPIRE);
                    jsonObject.put("token",token);
                    jsonObject.put("code",0);
                    return jsonObject;
            }catch (Exception e){
                LOGGER.error("create token fail!");
                jsonObject.put("token","create token error");
                jsonObject.put("code",401);
                return jsonObject;
            }
        }else {
            jsonObject.put("token", "Unreasonable request");
            jsonObject.put("code",402);
            return jsonObject;
        }
    }

    @Override
    public JSONObject sendListUserInfoService(String token, UserInfo userInfo) {

        JSONObject jsonObject = new JSONObject();
        if (StringUtil.isequals(redisCache.getCache(token),userInfo.getUserId()+userInfo.getSystemId())){
            String users = JSON.toJSONString(userInfo);
            redisCache.deleteCache(token);
            if(redisCache.putCacheWithExpireTime(token,users, Constant.CACHEEXPIRE)){
                jsonObject.put("msg","success");
                jsonObject.put("code",0);
                return jsonObject;
            }else {
                jsonObject.put("msg","save userinfo error!");
                jsonObject.put("code",401);
                return jsonObject;
            }
        }else {
            jsonObject.put("msg","token authentication fail!");
            jsonObject.put("code",402);
            return jsonObject;
        }
    }

    @Override
    public JSONObject unifauthService(String token, OauthInfo oauthInfo) {
        JSONObject jsonObject = new JSONObject();
        JSONObject retJson=new JSONObject();
        DefaultTokenManager defaultTokenManager = new DefaultTokenManager();
        if (StringUtil.isNotEmpty(redisCache.getCache(token))){
            String users = redisCache.getCache(token);
            if (StringUtil.isNotEmpty(users)){
                jsonObject=JSON.parseObject(users);
                if (StringUtil.isequals(oauthInfo.getSystemId(),jsonObject.getString("systemId"))&&StringUtil.isequals(oauthInfo.getPhone(),jsonObject.getString("phone"))){
                    String loginToken = defaultTokenManager.createToken("");
                    try {
                        redisCache.putCacheWithExpireTime(loginToken,token,Constant.CACHEEXPIRE);
                        retJson.put("token",loginToken);
                        retJson.put("code",0);
                        redisCache.deleteCache(token);
                        return retJson;
                    }catch (Exception e){
                        LOGGER.error("create token fail!");
                        retJson.put("token","create token error");
                        retJson.put("code",401);
                        return retJson;
                    }
                }else {
                    retJson.put("token","");
                    retJson.put("code",401);
                    retJson.put("msg","type or userName authentication fail");
                    redisCache.deleteCache(token);
                    return retJson;
                }

            }else {
                retJson.put("token","");
                retJson.put("code",402);
                retJson.put("msg","user info null");
                redisCache.deleteCache(token);
                return retJson;
            }
        }else {
            retJson.put("token","");
            retJson.put("code",403);
            retJson.put("msg","token authentication fail!");
            return retJson;
        }
    }

    @Override
    public JSONObject logoutService(String token) {
        JSONObject retJson = new JSONObject();
        if (redisCache.exitKey(token)){
            try {
                redisCache.deleteCache(token);
                retJson.put("code",0);
                retJson.put("msg","logout success!");
            }catch (Exception e){
                retJson.put("code",400);
                retJson.put("msg","logout fail!");
                LOGGER.error("delete fail!");
            }
            return retJson;
        }else {
            retJson.put("code",0);
            retJson.put("msg","");
            return retJson;
        }
    }
}
