package com.mall.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.ServerResponse;
import com.mall.dao.GoodsInfoMapper;
import com.mall.pojo.GoodsInfo;
import com.mall.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iGoodsService")
public class GoodsServiceImp implements IGoodsService {
	@Autowired
	private GoodsInfoMapper goodsInfoMapper;

	@Override
	public ServerResponse<PageInfo> getGoodsList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<GoodsInfo> goodsInfoList = goodsInfoMapper.selectList();
		PageInfo pageInfo = new PageInfo(goodsInfoList);
		return ServerResponse.createBySuccess(pageInfo);
	}

	@Override
	public ServerResponse<String> addGoods(GoodsInfo goodsInfo) {
		int rowCount =  goodsInfoMapper.insertSelective(goodsInfo);
		if (rowCount > 0) {
			return ServerResponse.createBySuccessMessage("保存成功!");
		}
		return ServerResponse.createByErrorMessage("保存失败！");
	}

	@Override
	public ServerResponse<String> update(GoodsInfo goodsInfoList) {
		int rowCount = goodsInfoMapper.updateByPrimaryKeySelective(goodsInfoList);
		if (rowCount > 0) {
			return ServerResponse.createBySuccessMessage("修改成功");
		} else {
			return ServerResponse.createByErrorMessage("修改失败");
		}

	}

	@Override
	public ServerResponse<String> delete(Long goodsId) {
		int rowCount = goodsInfoMapper.deleteByPrimaryKey(goodsId);
		if (rowCount > 0) {
			return ServerResponse.createBySuccessMessage("删除成功");
		} else {
			return ServerResponse.createByErrorMessage("删除失败");
		}
	}
}
