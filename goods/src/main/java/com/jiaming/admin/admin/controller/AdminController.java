package com.jiaming.admin.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiaming.admin.admin.service.AdminService;
import com.jiaming.entity.Admin;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/loginAdmin.action")
	public String login(HttpSession session, Admin admin, Model model) {
		
		Admin ad = adminService.login(admin);
		
		if (ad == null) {
			model.addAttribute("msg", "账户名或密码错误!");
			return "adminjsps/login";
		} 
		
		session.setAttribute("admin", admin);
		
		return "adminjsps/admin/index";
	}
	
	
	
	

}
