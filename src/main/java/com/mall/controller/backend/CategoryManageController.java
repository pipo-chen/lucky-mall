package com.mall.controller.backend;

import com.mall.common.ServerResponse;
import com.mall.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/category/")
public class CategoryManageController {

	@Autowired
	ICategoryService iCategoryService;

	@RequestMapping("get_deep_category.do")
	@ResponseBody
	//找到所有子节点
	public ServerResponse getCategoryAndDeepChildrenCategory( @RequestParam(value = "categoryId", defaultValue = "107") Long categoryId) {
		return iCategoryService.selectCategoryAndChildrenById(categoryId);
	}

	@RequestMapping("get_basic_category.do")
	@ResponseBody
	public ServerResponse getAllCategory() {
		return iCategoryService.selectBasicCategory((long) 0);
	}


}
