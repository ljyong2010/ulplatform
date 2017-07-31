package org.ulplatform.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ulplatform.dao.DepartUser;
import org.ulplatform.service.DepartUserServrice;
import org.ulplatform.service.UserInfoAuthSevice;
import org.ulplatform.utils.StringUtil;

/**
 * Created by Administrator on 2017-7-28.
 */
@RestController
@RequestMapping("/userinfo")
public class DepartUserController {
    @Autowired
    public DepartUserServrice departUserServrice;

    @Autowired
    private UserInfoAuthSevice userInfoAuthSevice;

    @RequestMapping("/user")
    public String getUserInfo(@RequestParam("userId") String userId){
        DepartUser departUser = departUserServrice.departUserInfoByUserId(userId);
        String stringText = JSON.toJSONString(departUser);
        return stringText;
    }
    @RequestMapping("/user1")
    public String getUser(@RequestParam("userId") String userId){
        if (StringUtil.isNotEmpty(userInfoAuthSevice.getUserInfoByUserId(userId))){
            return "success";
        }else {
            return "fail";
        }
    }
}
