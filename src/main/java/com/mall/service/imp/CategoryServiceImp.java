package com.mall.service.imp;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mall.common.ServerResponse;
import com.mall.dao.CategoryMapper;
import com.mall.pojo.Category;
import com.mall.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("iCategoryService")
public class CategoryServiceImp implements ICategoryService {
	@Autowired
	private CategoryMapper categoryMapper;

	public ServerResponse<List<Map>> selectBasicCategory(Long parentId) {
		List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(parentId);
		List<Map> result = new ArrayList<>();
		for (Category item : categoryList) {
			Map map = new HashMap();
			map.put("categoryId", item.getCategoryId());
			map.put("categoryName", item.getCategoryName());
			result.add(map);
		}
		return ServerResponse.createBySuccess(result);
	}
	@Override
	public ServerResponse<List<Map>> selectCategoryAndChildrenById(Long categoryId) {
		Set<Category> categorySet = Sets.newHashSet();
		findChildCategory(categorySet, categoryId);
		List<Map> categoryInfoList = Lists.newArrayList();
		if (categoryId != null) {
			for (Category categoryItem : categorySet) {
				Map map = new HashMap();
				map.put("categoryId",categoryItem.getCategoryId());
				map.put("categoryName",categoryItem.getCategoryName());
				categoryInfoList.add(map);
			}
		}

		return ServerResponse.createBySuccess(categoryInfoList);
	}

	private Set<Category> findChildCategory(Set<Category> categorySet, Long categoryId) {
		Category category = categoryMapper.selectByPrimaryKey(categoryId);
		if (category != null) {
			categorySet.add(category);
		}
		List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
		for (Category categoryItem : categoryList) {
			findChildCategory(categorySet, categoryItem.getCategoryId());
		}
		return categorySet;
	}

}
