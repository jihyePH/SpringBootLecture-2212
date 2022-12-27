package com.mulcam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/basic")
public class BasicController {

	@RequestMapping("/basic")
	public String basic() {
		return "basic/basic";
	}
	
	@ResponseBody
	@RequestMapping("/basic1")
	public String basic1() {
		return "<h1>@Controller에서는 문자열을 웹화면으로 보낼때 @ResponseBody를 사용</h1>";
	}
	
	@RequestMapping("/basic2")
	public String basic2(Model model) {
		model.addAttribute("fileName", "basic2.jsp");
		model.addAttribute("message", "Model로 데이터를 전달함");
		List<String> fruits = new ArrayList<>();
		fruits.add("사과"); fruits.add("귤"); fruits.add("오렌지");
		model.addAttribute("fruits", fruits);
		return "basic/basic2";
	}
	
	// 파라메터 패싱
	@RequestMapping("/basic3")
	public String basic3(@RequestParam(name="id", defaultValue="1")String id) {
		System.out.println("id: " + id);
		return "redirect:/basic/basic" + id;	// Redirection
	}
	
	// Servlet에서는 안되던 방식
	@RequestMapping("/basic4/{id}")
	public String basic4(@PathVariable int id) {
		System.out.println("id: " + id);
		return "redirect:/basic/basic" + id;
	}
	
	// 기존 방식
	@RequestMapping("/basic5")
	public String basic5(HttpServletRequest req) {
		String id = req.getParameter("id");
		System.out.println("id: " + id);
		return "redirect:/basic/basic" + id;
	}
	
	@RequestMapping("/basic11")
	public String basic11(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("uname", "Spring");
		return "redirect:/user/list";
	}
	
}