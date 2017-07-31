package org.ulplatform.dao.mapper;

import org.springframework.stereotype.Repository;
import org.ulplatform.dao.ToHorizonUser;

@Repository
public interface ToHorizonUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(ToHorizonUser record);

    int insertSelective(ToHorizonUser record);

    ToHorizonUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ToHorizonUser record);

    int updateByPrimaryKey(ToHorizonUser record);

    String selectByLoginName(String loginName);

}