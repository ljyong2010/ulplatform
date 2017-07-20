package org.ulplatform.service;

import com.alibaba.fastjson.JSONObject;
import org.ulplatform.model.UserInfo;

/**
 * Created by Administrator on 2017-7-8.
 */
public interface AuthenticationService {
    public JSONObject createTokenService(String userid);
    public JSONObject sendListUserInfoService(String token, UserInfo userInfo);
    public JSONObject unifauthService(String token,String ctype,String userName);
}
