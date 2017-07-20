package org.ulplatform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RedisCache redisCache;


    @RequestMapping("/getToken")
    public String createToken(@RequestParam("userid") String userid){
        DefaultTokenManager defaultTokenManager = new DefaultTokenManager();
        JSONObject jsonObject = new JSONObject();
        String token = defaultTokenManager.createToken(userid);
        redisCache.putCache(token,userid);
        jsonObject.put("token",token);
        jsonObject.put("userid",userid);
        jsonObject.put("code",0);
        return jsonObject.toJSONString();
    }
    @RequestMapping(value = "/sendListInfo",method = RequestMethod.POST)
    public String sendListUserInfo(@RequestParam("token") String token, @RequestBody UserInfo userInfo){
        JSONObject jsonObject = new JSONObject();
        if (StringUtil.isNotEmpty(redisCache.getCache(token))){
            String users = JSON.toJSONString(userInfo);
            System.out.println(users);
            if(redisCache.putCache(StringUtil.addPrefix(token),users)){
                jsonObject.put("msg","success");
                jsonObject.put("code",0);
                return jsonObject.toJSONString();
            }else {
                jsonObject.put("msg","save userinfo error!");
                jsonObject.put("code",1);
                return jsonObject.toJSONString();
            }
        }else {
            jsonObject.put("msg","token authentication fail!");
            jsonObject.put("code",2);
            return jsonObject.toJSONString();
        }
    }
    @RequestMapping("/unifauthentication")
    public String unifauth(@RequestParam("token") String token,@RequestParam("cid") String cid){
        JSONObject jsonObject = new JSONObject();
        if (StringUtil.isNotEmpty(redisCache.getCache(token))){
            String users = redisCache.getCache(StringUtil.addPrefix(token));
            if (StringUtil.isNotEmpty(users)){
                jsonObject=JSON.parseObject(users);
                jsonObject.put("code",0);
                return jsonObject.toJSONString();
            }else {
                jsonObject.put("code",1);
                jsonObject.put("msg","user info null");
                return jsonObject.toJSONString();
            }
        }else {
            jsonObject.put("code",2);
            jsonObject.put("msg","token authentication fail!");
            return jsonObject.toJSONString();
        }
    }
}
