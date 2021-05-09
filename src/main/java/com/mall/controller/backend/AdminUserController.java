package com.mall.controller.backend;

import com.mall.common.ServerResponse;
import com.mall.service.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/")
public class AdminUserController {
	@Autowired
	private IAdminUserService iAdminUserService;

	@RequestMapping("login.do")
	@ResponseBody
	public ServerResponse login(String username, String password) {
		return iAdminUserService.login(username, password);
	}
}
