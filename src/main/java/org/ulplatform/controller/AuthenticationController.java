package org.ulplatform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ulplatform.authorization.DefaultTokenManager;
import org.ulplatform.model.OauthInfo;
import org.ulplatform.model.UserInfo;
import org.ulplatform.service.AuthenticationService;
import org.ulplatform.utils.StringUtil;
import org.ulplatform.utils.cache.RedisCache;

/**
 * Created by Administrator on 2017-7-8.
 */
@RestController
@RequestMapping("/authentiaction")
public class AuthenticationController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping("/getToken")
    public String createToken(@RequestParam("userId") String userId,@RequestParam("urlType") String urlType){
        JSONObject jsonObject = authenticationService.createTokenService(userId,urlType);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/sendListInfo")
    public String sendListUserInfo(@RequestParam("token") String token, @RequestBody UserInfo userInfo){
        JSONObject jsonObject = authenticationService.sendListUserInfoService(token, userInfo);
        return jsonObject.toJSONString();
    }
    @RequestMapping(value = "/unifauthentication")
    public String unifauth(@RequestParam("token") String token, @RequestBody OauthInfo oauthInfo){
        JSONObject jsonObject = authenticationService.unifauthService(token,oauthInfo);
        return jsonObject.toJSONString();
    }
    @RequestMapping("/logout")
    public String logout(@RequestParam("token") String token){
        JSONObject jsonObject = authenticationService.logoutService(token);
        return jsonObject.toJSONString();
    }
}
