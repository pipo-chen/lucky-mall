package com.mall.service;

import com.github.pagehelper.PageInfo;
import com.mall.common.ServerResponse;
import com.mall.pojo.Carousel;

public interface ICarouselService {

	public ServerResponse<PageInfo> selectList(Integer pageNum, Integer pageSize);

	public ServerResponse insertItem(Carousel carousel);

	public ServerResponse deleteItem(Integer carouselId);

	public ServerResponse updateItem(Carousel carousel);
}
