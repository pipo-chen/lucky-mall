package com.mall.service;

import com.github.pagehelper.PageInfo;
import com.mall.common.ServerResponse;
import com.mall.pojo.GoodsInfo;

import java.util.List;

public interface IGoodsService {
	public ServerResponse<PageInfo> getGoodsList(int pageNum, int pageSize);

	public ServerResponse<String> addGoods(GoodsInfo goodsInfo);

	public ServerResponse<String> update(GoodsInfo goodsInfoList);

	public ServerResponse<String> delete(Long goodsId);
}
