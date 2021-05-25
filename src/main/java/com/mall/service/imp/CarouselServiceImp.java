package com.mall.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.ServerResponse;
import com.mall.dao.CarouselMapper;
import com.mall.pojo.Carousel;
import com.mall.service.ICarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iCarouselService")
public class CarouselServiceImp implements ICarouselService {

	@Autowired
	private CarouselMapper carouselMapper;

	@Override
	public ServerResponse<PageInfo> selectList(Integer pageNum, Integer pageSize) {

		PageHelper.startPage(pageNum, pageSize);
		List<Carousel> carouselList = carouselMapper.selectAll();
		PageInfo pageInfo = new PageInfo(carouselList);
		return ServerResponse.createBySuccess(pageInfo);
	}

	@Override
	public ServerResponse insertItem(Carousel carousel) {
		int row = carouselMapper.insertSelective(carousel);
		if (row > 0)
			return ServerResponse.createBySuccessMessage("添加轮播图成功！");
		return ServerResponse.createByErrorMessage("添加轮播图失败！");
	}

	@Override
	public ServerResponse deleteItem(Integer carouselId) {
		int row = carouselMapper.changeDeleteStatus(carouselId);
		if (row > 0)
			return ServerResponse.createBySuccessMessage("删除轮播图成功！");
		return ServerResponse.createByErrorMessage("删除轮播图失败！");
	}

	@Override
	public ServerResponse updateItem(Carousel carousel) {
		int row = carouselMapper.updateByPrimaryKeySelective(carousel);
		if (row > 0)
			return ServerResponse.createBySuccessMessage("修改轮播图成功！");
		return ServerResponse.createByErrorMessage("修改轮播图失败！");
	}
}
