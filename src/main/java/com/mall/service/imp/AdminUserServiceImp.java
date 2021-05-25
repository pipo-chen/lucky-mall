package com.mall.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import java.util.List;

@Service("iAdminUserService")
public class AdminUserServiceImp implements IAdminUserService {

	@Autowired
	private AdminUserMapper adminUserMapper;
	@Override
	public ServerResponse<AdminUser> login(String username, String password) {
		//检查用户名
		int rowCount = adminUserMapper.checkUsername(username);
		if (rowCount == 0) {
			return ServerResponse.createByErrorMessage("用户名不存在！");
		}
		//检查密码
		String md5Password = MD5Util.MD5EncodeUtf8(password);
		AdminUser adminUser = adminUserMapper.selectLogin(username, md5Password);
		if (adminUser == null) {
			return ServerResponse.createByErrorMessage("密码错误！");
		}
		adminUser.setLoginPassword(StringUtils.EMPTY);
		return ServerResponse.createBySuccess("登录成功!",adminUser);
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
			return ServerResponse.createByErrorMessage("注册失败！");
		return ServerResponse.createBySuccessMessage("注册成功！");
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

	@Override
	public ServerResponse list(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<AdminUser>adminUsers = adminUserMapper.selectAllUser();
		PageInfo pageInfo = new PageInfo<>(adminUsers);
		return ServerResponse.createBySuccess(pageInfo);
	}

	@Override
	public ServerResponse delete(Integer userId) {
		int row = adminUserMapper.deleteByPrimaryKey(userId);
		if (row > 0) {
			return ServerResponse.createBySuccessMessage("删除用户成功！");
		}
		return ServerResponse.createByErrorMessage("删除用户失败！");
	}

	@Override
	public ServerResponse changeLocked(Integer adminUserId, byte locked) {
		int row = adminUserMapper.changeLockedStatus(adminUserId, locked);
		if (row > 0) {
			return ServerResponse.createBySuccessMessage("变更用户锁定状态成功！");
		}
		return ServerResponse.createByErrorMessage("变更用户状态失败！");
	}
}
