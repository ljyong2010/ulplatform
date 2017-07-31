package org.ulplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ulplatform.dao.ToHorizonUser;
import org.ulplatform.dao.mapper.ToHorizonUserMapper;
import org.ulplatform.service.UserInfoAuthSevice;

@Service
public class UserInfoAuthSeviceImpl implements UserInfoAuthSevice{
    @Autowired
    private ToHorizonUserMapper toHorizonUserMapper;

    @Override
    public String getUserInfoByUserId(String userId) {
        return toHorizonUserMapper.selectByLoginName(userId);
    }
}
