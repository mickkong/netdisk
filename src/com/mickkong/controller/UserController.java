package com.mickkong.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mickkong.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	//响应体
	@ResponseBody
	public String login(String username,String password, HttpSession session) {
		int result = userService.login(username, password);
		if (result == UserServiceImpl.SUCCESS) {
			session.setAttribute(username, username);
			session.setMaxInactiveInterval(60 * 60);
			return "success";
		} else if (result == UserServiceImpl.PWDERROR) {
			return "PWDERROR";
		} else if (result == UserServiceImpl.NONEXIST) {
			return "NONEXIST";
		}
		return "";
	}

}
