package org.ulplatform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ulplatform.authorization.DefaultTokenManager;
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
    public String createToken(@RequestParam("userid") String userid,@RequestParam("urltype") String urltype){
        JSONObject jsonObject = authenticationService.createTokenService(userid,urltype);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/sendListInfo",method = RequestMethod.POST)
    public String sendListUserInfo(@RequestParam("token") String token, @RequestBody UserInfo userInfo){
        JSONObject jsonObject = authenticationService.sendListUserInfoService(token, userInfo);
        return jsonObject.toJSONString();
    }
    @RequestMapping("/unifauthentication")
    public String unifauth(@RequestParam("token") String token,@RequestParam("systemId") String systemId,@RequestParam String userName){
        JSONObject jsonObject = authenticationService.unifauthService(token,systemId,userName);
        return jsonObject.toJSONString();
    }
}
