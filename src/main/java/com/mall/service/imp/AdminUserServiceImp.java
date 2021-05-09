package com.mall.service.imp;

import com.mall.common.Const;
import com.mall.common.ResponseCode;
import com.mall.common.ServerResponse;
import com.mall.dao.AdminUserMapper;
import com.mall.pojo.AdminUser;
import com.mall.service.IAdminUserService;
import com.mall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.rmi.ServerError;

@Service("iAdminUserService")
public class AdminUserServiceImp implements IAdminUserService {

	@Autowired
	private AdminUserMapper adminUserMapper;
	@Override
	public ServerResponse login(String username, String password) {
		//检查用户名
		int rowCount = adminUserMapper.checkUsername(username);
		if (rowCount == 0) {
			return ServerResponse.createByErrorMessage("username error!");
		}
		//检查密码
		String md5Password = MD5Util.MD5EncodeUtf8(password);
		AdminUser adminUser = adminUserMapper.selectLogin(username, md5Password);
		if (adminUser == null) {
			return ServerResponse.createByErrorMessage("password error!");
		}
		adminUser.setLoginPassword(StringUtils.EMPTY);
		return ServerResponse.createBySuccess("login success!",adminUser);
	}

	@Override
	public ServerResponse register(AdminUser adminUser) {
		int resultCount = adminUserMapper.checkUsername(adminUser.getLoginUserName());
		ServerResponse validResponse = this.checkVaild(adminUser.getLoginUserName(), Const.USERNAME);
		if (!validResponse.isSuccess()) {
			return validResponse;
		}
		validResponse = this.checkVaild(adminUser.getNickName(), Const.NIKENAME);
		if (!validResponse.isSuccess())
			return validResponse;

		//md5
		adminUser.setLoginPassword(MD5Util.MD5EncodeUtf8(adminUser.getLoginPassword()));
		//插入
		resultCount = adminUserMapper.insert(adminUser);
		if (resultCount == 0)
			return ServerResponse.createByErrorMessage("register fail!");
		return ServerResponse.createBySuccessMessage("register success!");
	}


	private ServerResponse checkVaild(String str, String type) {
		if (StringUtils.isNotBlank(type)) {
			//开始校验
			if (Const.USERNAME.equals(type)) {
				int resultCount = adminUserMapper.checkUsername(str);
				if (resultCount > 0)
					return ServerResponse.createByErrorMessage("username has already exist!");
			}

			if (Const.NIKENAME.equals(type)) {
				int resultCount = adminUserMapper.checkNickname(str);
				if (resultCount > 0)
					return ServerResponse.createByErrorMessage("nickname has already exist!");
			}
		} else {
			return ServerResponse.createByError(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
		}
		return ServerResponse.createBySuccessMessage("check available!");
	}
}
