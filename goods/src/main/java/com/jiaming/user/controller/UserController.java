package com.jiaming.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiaming.entity.User;
import com.jiaming.user.service.UserService;

import povos.ActivationResult;
import yanzhengma.Verify;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/changePwd.action")
	public String changePwd(HttpSession session, String newpass, Model model) {

		User u = (User) session.getAttribute("user");
		// System.out.println(u);
		u.setLoginpass(newpass);
		boolean res = userService.changePwd(u);
		if (res) {
			session.invalidate();
			return "index";
		}
		model.addAttribute("code", "error");
		model.addAttribute("msg", "修改密码失败了！");
		return "jsps/msg";

	}

	@PostMapping("/verifyPwd.action")
	@ResponseBody
	public Boolean verifyPwd(String loginpass, HttpSession session) {
		System.out.println("verifyPwd.action对应的Handler  工作了。。。。");
		User u = (User) session.getAttribute("user");
		String local_pwd = u.getLoginpass();
		if (local_pwd.equals(loginpass)) {
			return true;
		}
		return false;

	}

	@GetMapping("/logout.action")
	public String logout(HttpSession session) {

		session.invalidate();

		return "index";

	}

	@PostMapping("/login.action")
	public String login(HttpSession session, User user, Model model) {

		User u = userService.login(user);
		if (u == null) {
			model.addAttribute("msg", "用户名或密码错误");
			return "jsps/user/login";
		} else {
			if (!u.getStatus()) {
				model.addAttribute("msg", "当前用户已被禁用，请联系Admin");
				return "jsps/user/login";
			} else {
				session.setAttribute("user", u);
				return "index";
			}

		}

	}

	@GetMapping("/activation.action")
	public String activation(String activationCode, Model model) {

		ActivationResult ar = userService.activation(activationCode);

		// 业务层 告知我 激活 结果 boolean 成功 失败
		// 业务层 还需要 告知 失败的 原因 3个， 成功的原因1个。
		// 1, 激活成功！ 2，激活失败了， 重复激活， 错误的激活码 处理

		// code s e
		// msg

		model.addAttribute("msg", ar.getMessage());
		if (ar.isRes()) {
			model.addAttribute("code", "success");

		} else {
			model.addAttribute("code", "error");
		}

		return "jsps/msg";
	}

	@PostMapping("/regist.action")
	public String regist(Model model, User user) {
		// code , msg

		boolean res = userService.regist(user);
		if (res) {
			model.addAttribute("code", "success");// 显示 对号
			model.addAttribute("msg", "请到邮箱中激活！");
		} else {
			model.addAttribute("code", "error");// 显示 错号
			model.addAttribute("msg", "注册失败了！");

		}
		return "jsps/msg";
	}

	@PostMapping("/verifyLoginname.action")
	@ResponseBody
	public Boolean verifyLoginname(String loginname) {
		return userService.getUserByLoginname(loginname);

	}

	@PostMapping("/verifyEmail.action")
	@ResponseBody
	public Boolean verifyEmail(String email) {
		return userService.getUserByEmail(email);

	}

	@GetMapping("/getVerify.action")
	public void getVerify(HttpServletRequest request, HttpServletResponse response) {
		try {
			Verify.getVirify(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@PostMapping("/verifyCode.action")
	@ResponseBody
	public Boolean verifyCode(HttpSession session, String verifyCode) {

		String local_verifyCode = (String) session.getAttribute("vCode");
		if (verifyCode.equalsIgnoreCase(local_verifyCode)) {
			return true;
		}
		return false;

	}

}
