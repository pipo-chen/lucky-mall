package com.mall.controller.backend;

import com.mall.common.Const;
import com.mall.common.ResponseCode;
import com.mall.common.ServerResponse;
import com.mall.pojo.AdminUser;
import com.mall.pojo.Category;
import com.mall.pojo.GoodsInfo;
import com.mall.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/goods/")
public class GoodsManageController {

	@Autowired
	private IGoodsService iGoodsService;

	@RequestMapping(value = "list.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse selectList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10")int pageSize) {

		return iGoodsService.getGoodsList(pageNum, pageSize);
	}

	@RequestMapping(value = "add.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse addGoods(GoodsInfo goodsInfo) {
		return iGoodsService.addGoods(goodsInfo);
	}

	@RequestMapping(value = "changeGoodsInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse changeGoodsInfo(GoodsInfo goodsInfoList) {

		return iGoodsService.update(goodsInfoList);
	}

	@RequestMapping(value = "delete.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse deleteGoods(Long goodsId) {
		return iGoodsService.delete(goodsId);
	}



}
