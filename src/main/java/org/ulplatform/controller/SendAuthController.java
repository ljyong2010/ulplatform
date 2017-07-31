package org.ulplatform.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ulplatform.authorization.DefaultTokenManager;
import org.ulplatform.model.AuthInfo;
import org.ulplatform.model.UauthInfo;
import org.ulplatform.service.UserInfoAuthSevice;
import org.ulplatform.utils.StringUtil;
import org.ulplatform.utils.cache.RedisCache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-7-27.
 */
@RestController
@RequestMapping("/authorize")
public class SendAuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendAuthController.class);

    @Autowired
    private UserInfoAuthSevice userInfoAuthSevice;


    @Autowired
    private RedisCache redisCache;

    @RequestMapping("/gettoken")
    public String getToken(@RequestBody AuthInfo authInfo){
        JSONObject jsonObject = new JSONObject();
        DefaultTokenManager defaultTokenManager = new DefaultTokenManager();
        String userId=authInfo.getUserId();

        if (StringUtil.isNotEmpty(userInfoAuthSevice.getUserInfoByUserId(userId))){
            String token = defaultTokenManager.createToken(userId);
            redisCache.putCache(token,userId);
            jsonObject.put("code",0);
            jsonObject.put("token",token);
            return jsonObject.toJSONString();
        }else {
            jsonObject.put("code",101);
            jsonObject.put("msg","systemId auth fail");
            return jsonObject.toJSONString();
        }
    }

  /*  @RequestMapping("/gettoken1")
    public String getToken(@RequestParam("userId") String userId,@RequestParam("systemId") String systemId){
        JSONObject jsonObject = new JSONObject();
        DefaultTokenManager defaultTokenManager = new DefaultTokenManager();

        if (map.containsKey(userId)){
            String token = defaultTokenManager.createToken(userId);
            redisCache.putCache(token,userId);

           jsonObject.put("code",0);
           jsonObject.put("token",token);
            return jsonObject.toJSONString();
        }else {
            jsonObject.put("code",101);
            jsonObject.put("msg","systemId auth fail");
            return jsonObject.toJSONString();
        }
    }*/
    @RequestMapping("/authtoken")
    public String authToken(@RequestBody UauthInfo uauthInfo){
        JSONObject jsonObject = new JSONObject();
        if (StringUtil.isNotEmpty(redisCache.getCache(uauthInfo.getToken()))){
            jsonObject.put("code",0);
            jsonObject.put("msg","success");
        }else {
            jsonObject.put("code",201);
            jsonObject.put("msg","token fail");
        }
        return jsonObject.toJSONString();
    }
}
