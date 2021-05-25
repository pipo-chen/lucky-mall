package com.mall.service;

import com.mall.common.ServerResponse;
import com.mall.pojo.AdminUser;

public interface IAdminUserService {

	public ServerResponse<AdminUser> login(String username, String password);

	public ServerResponse register(AdminUser adminUser);

	public ServerResponse list(int pageNum, int pageSize);

	public ServerResponse delete(Integer userId);

	public ServerResponse changeLocked(Integer adminUserId, byte locked);
}
