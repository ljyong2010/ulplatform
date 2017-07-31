package org.ulplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ulplatform.dao.DepartUser;
import org.ulplatform.dao.mapper.DepartUserMapper;
import org.ulplatform.service.DepartUserServrice;

/**
 * Created by Administrator on 2017-7-28.
 */
@Service
public class DepartUserServriceImpl implements DepartUserServrice {

    @Autowired
    public DepartUserMapper departUserMapper;
    @Override
    public DepartUser departUserInfoByUserId(String userid) {
        DepartUser departUser = departUserMapper.selectByUserId(userid);
        return departUser;
    }
}
