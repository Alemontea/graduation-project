package com.news.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.news.pojo.Logtable;
import com.news.pojo.LogtableExample;

public interface LogtableMapper {
    int countByExample(LogtableExample example);

    int deleteByExample(LogtableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Logtable record);

    int insertSelective(Logtable record);

    List<Logtable> selectByExample(LogtableExample example);

    Logtable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Logtable record, @Param("example") LogtableExample example);

    int updateByExample(@Param("record") Logtable record, @Param("example") LogtableExample example);

    int updateByPrimaryKeySelective(Logtable record);

    int updateByPrimaryKey(Logtable record);
}