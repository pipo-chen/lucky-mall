package com.mall.service;

import com.mall.common.ServerResponse;
import com.mall.pojo.AdminUser;

public interface IAdminUserService {

	public ServerResponse login(String username, String password);

	public ServerResponse register(AdminUser adminUser);
}
