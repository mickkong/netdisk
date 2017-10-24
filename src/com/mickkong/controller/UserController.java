package com.mickkong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.mickkong.service.UserService;
import com.mickkong.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(String username, String password,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		int result = userService.login(username, password);
		if(result == UserServiceImpl.SUCCESS) {
			session.setAttribute(username, username);
			session.setMaxInactiveInterval(60*60);
			mav.addObject("username",username);
			mav.setViewName("list");
		} else if (result == UserServiceImpl.PWDERROR) {
			mav.addObject("msg","PWDERROR");
			mav.setViewName("result");
		} else if (result == UserServiceImpl.NONEXIST) {
			mav.addObject("msg","NONEXIST");
			mav.setViewName("result");
		}
		return mav;
	}
}
