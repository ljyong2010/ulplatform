package org.ulplatform.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.ulplatform.dao.AuthorMenu;
import org.ulplatform.dao.AuthorMenuExample;

public interface AuthorMenuMapper {
    long countByExample(AuthorMenuExample example);

    int deleteByExample(AuthorMenuExample example);

    int insert(AuthorMenu record);

    int insertSelective(AuthorMenu record);

    List<AuthorMenu> selectByExample(AuthorMenuExample example);

    int updateByExampleSelective(@Param("record") AuthorMenu record, @Param("example") AuthorMenuExample example);

    int updateByExample(@Param("record") AuthorMenu record, @Param("example") AuthorMenuExample example);
}