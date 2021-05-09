package com.mall.dao;

import com.mall.pojo.ShoppingCart;

public interface ShoppingCartMapper {

    int deleteByPrimaryKey(Long cartItemId);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    ShoppingCart selectByPrimaryKey(Long cartItemId);

    int updateByPrimaryKeySelective(ShoppingCart record);

    int updateByPrimaryKey(ShoppingCart record);
}