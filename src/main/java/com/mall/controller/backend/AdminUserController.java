package com.mall.controller.backend;

import com.mall.common.Const;
import com.mall.common.ServerResponse;
import com.mall.pojo.AdminUser;
import com.mall.service.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/")
public class AdminUserController {
	@Autowired
	private IAdminUserService iAdminUserService;

	@RequestMapping(value = "login.do",method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse login(String loginUserName, String loginPassword, HttpSession session) {
		ServerResponse<AdminUser> response = iAdminUserService.login(loginUserName, loginPassword);
		if (response.isSuccess()) {
			session.setAttribute(Const.CURRENT_USER, response.getData());
		}
		return response;
	}
	@RequestMapping(value = "register.do",method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse register(AdminUser user, HttpSession session) {
		user.setLocked((byte)0);
		ServerResponse response = iAdminUserService.register(user);
		if (response.isSuccess())
			session.setAttribute(Const.CURRENT_USER, response.getData());
		return response;
	}

	@RequestMapping(value = "list.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse list(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10")int pageSize) {
		return iAdminUserService.list(pageNum, pageSize);
	}

	@RequestMapping(value = "delete.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse list(HttpSession session, Integer adminUserId) {
		return iAdminUserService.delete(adminUserId);
	}

	@RequestMapping(value = "changeLocked.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse changeLocked(HttpSession session, Integer adminUserId, Integer locked) {
		return iAdminUserService.changeLocked(adminUserId, locked.byteValue());
	}



}
