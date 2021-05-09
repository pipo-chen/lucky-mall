package com.mall.service;

import com.mall.common.ServerResponse;

public interface IAdminUserService {

	public ServerResponse login(String username, String password);
}
