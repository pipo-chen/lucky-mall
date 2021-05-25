package com.mall.dao;

import com.mall.pojo.Carousel;

import java.util.List;

public interface CarouselMapper {

    int deleteByPrimaryKey(Integer carouselId);

    int insert(Carousel record);

    int insertSelective(Carousel record);

    Carousel selectByPrimaryKey(Integer carouselId);

    int updateByPrimaryKeySelective(Carousel record);

    int updateByPrimaryKey(Carousel record);

    List<Carousel> selectAll();

    int changeDeleteStatus(Integer carouselId);
}