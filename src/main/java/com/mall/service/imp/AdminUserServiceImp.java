package com.mall.service.imp;

import com.mall.common.ServerResponse;
import com.mall.dao.AdminUserMapper;
import com.mall.service.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iAdminUserService")
public class AdminUserServiceImp implements IAdminUserService {

	@Autowired
	private AdminUserMapper adminUserMapper;
	@Override
	public ServerResponse login(String username, String password) {
		int resultRow = adminUserMapper.selectByUserNameAndPassword(username, password);
		if (resultRow > 0) {
			return ServerResponse.createBySuccessMessage("login success!");
		}

		return ServerResponse.createByErrorMessage("login fail!");
	}
}
