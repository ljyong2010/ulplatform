package org.ulplatform.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.ulplatform.model.OauthInfo;
import org.ulplatform.model.UserInfo;
import org.ulplatform.service.AuthenticationService;


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
    public String createToken(@RequestParam("userId") String userId,@RequestParam("systemId") String systemId,@RequestParam("urlType") String urlType){
        JSONObject jsonObject = authenticationService.createTokenService(userId,systemId,urlType);
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
    @RequestMapping("/test")
    public String rest(@RequestParam("status") String status){
        RestTemplate restTemplate = new RestTemplate();
        String url="http://localhost:8081/test/abc?status="+status;
        HttpHeaders headers=new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept",MediaType.APPLICATION_JSON.toString());
        OauthInfo oauthInfo = new OauthInfo();
        /*oauthInfo.setPhone("13100011122");*/
        oauthInfo.setSystemId("systemid1");

        String requestJson=JSON.toJSONString(oauthInfo);
        HttpEntity<String> entity= new HttpEntity<>(requestJson,headers);
        String result = restTemplate.postForObject(url,entity,String.class);
        System.out.println("springmvc--------------------->"+result);
        return "ssssss";
    }
}
