package com.mall.service;

import com.mall.common.ServerResponse;

import java.util.List;
import java.util.Map;

public interface ICategoryService {

	ServerResponse<List<Map>> selectCategoryAndChildrenById(Long categoryId);

	public ServerResponse<List<Map>> selectBasicCategory(Long parentId);

	ServerResponse addCategory(Long parentId, String categoryName);

	ServerResponse deleteCategory(Long categoryId);
}
