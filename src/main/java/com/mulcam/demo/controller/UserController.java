package com.mulcam.demo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mulcam.demo.entity.User;
import com.mulcam.demo.service.UserService;
import com.mulcam.demo.session.UserSession;

@Controller
@RequestMapping("/user")
public class UserController {

	
	@Resource
	private UserSession userSession;

	@Autowired
	private UserService service;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<User> list = service.getList();
		model.addAttribute("userList", list);
		model.addAttribute("sessionUid", userSession.getUid());
		model.addAttribute("sessionUname", userSession.getUname());
		return "user/list";
	}
	
	@RequestMapping("/detail/{uid}")
	public String detail(@PathVariable String uid, Model model) {
		User user = service.get(uid);
		System.out.println(user);
		model.addAttribute("user", user);
		return "redirect:/user/list";
	}
	
	@GetMapping("/register")
	public String registerFrom() {
		return "user/register";
	}
	
	@PostMapping("/register")
	public String register(HttpServletRequest req) {
		String uid = req.getParameter("uid").strip();
		String pwd = req.getParameter("pwd").strip();
		String pwd2 = req.getParameter("pwd2").strip();
		String uname = req.getParameter("uname").strip();
		String email = req.getParameter("email").strip();
		
		if (pwd.equals(pwd2)) {
			User u = new User(uid, pwd, uname, email);
			service.register(u);
			return "redirect:/user/list";
		} else {
			System.out.println("패스워드 입력이 잘못되었습니다.");
			return "redirect:/user/list";
		}
	}
	@GetMapping("/update/{uid}")
	public String updateForm(@PathVariable String uid, Model model) {
		User user = service.get(uid);
		model.addAttribute("user", user);
		return "user/update";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest req) {
		String uid = req.getParameter("uid").strip();
		String uname = req.getParameter("uname").strip();
		String email = req.getParameter("email").strip();
		User user = new User(uid, uname, email);
		service.update(user);
		return "redirect:/user/list";
	}
	
	// 완전 삭제 기능
//	@GetMapping("/delete/{uid}")
//	public String delete(@PathVariable String uid) {
//		service.delete(uid);
//		return "redirect:/user/list";
//	}
	
	// isDeleted 필드만 변경
	@GetMapping("/delete/{uid}")
	public String delete(@PathVariable String uid) {
		service.delete(uid);
		return "redirect:/user/list";
	}
	
	@GetMapping("/login") 
	public String loginForm() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest req, Model model) {
		String uid = req.getParameter("uid");
		String pwd = req.getParameter("pwd");
		int result = service.login(uid, pwd);
		switch (result) {
		case UserService.CORRECT_LOGIN:
			return "redirect:/user/list";
			
		case UserService.WRONG_PASSWORD:
			return "user/login";
			
		case UserService.UID_NOT_EXIST:
			return "redirect:/user/register";
			
		default:
			return "";
		}
		
	}
	
	@RequestMapping("/logout")
	public String logout() {
		userSession.setUid("");
		userSession.setUname("");
		return "redirect:/user/login";
	}
}
