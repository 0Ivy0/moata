package com.mysite.sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysite.sbb.user.UserService;

@Controller
public class MatchingController 
{
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/matching")
	public String matchingPage() {
		String check_user=userService.getCurrentUserId();
		
		if(check_user==null)
		{
			return "redirect:/login?needLogin5";
		}
		return "matching";
	}
	
}
