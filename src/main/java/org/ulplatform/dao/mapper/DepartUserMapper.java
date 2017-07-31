package org.ulplatform.dao.mapper;

import org.springframework.stereotype.Repository;
import org.ulplatform.dao.DepartUser;

@Repository
public interface DepartUserMapper {
    int insert(DepartUser record);

    int insertSelective(DepartUser record);

    DepartUser selectByUserId(String userid);
}