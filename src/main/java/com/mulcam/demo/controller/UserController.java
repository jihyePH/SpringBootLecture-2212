package com.mulcam.demo.controller;

import java.util.List;

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

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<User> list = service.getList();
		model.addAttribute("userList", list);
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
//			request.setAttribute("msg", "패스워드 입력이 잘못되었습니다.");
//			request.setAttribute("url", "/bbs/user/register");
//			rd = request.getRequestDispatcher("/WEB-INF/view/user/alertMsg.jsp");
//			rd.forward(request, response);
			System.out.println("패스워드 입력이 잘못되었습니다.");
			return "redirect:/user/list";
		}
		
	}
}
