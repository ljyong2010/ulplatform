package org.ulplatform.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.ulplatform.dao.DepartUser;
import org.ulplatform.dao.DepartUserExample;

public interface DepartUserMapper {
    long countByExample(DepartUserExample example);

    int deleteByExample(DepartUserExample example);

    int insert(DepartUser record);

    int insertSelective(DepartUser record);

    List<DepartUser> selectByExample(DepartUserExample example);

    int updateByExampleSelective(@Param("record") DepartUser record, @Param("example") DepartUserExample example);

    int updateByExample(@Param("record") DepartUser record, @Param("example") DepartUserExample example);
}