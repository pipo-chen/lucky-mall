package com.mall.dao;

import com.mall.pojo.Config;

public interface ConfigMapper {

    int deleteByPrimaryKey(Long configId);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Long configId);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}