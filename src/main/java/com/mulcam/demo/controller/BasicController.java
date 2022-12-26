package com.mulcam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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
		model.addAttribute("message", "Model로 데이터를 전달");
		List<String> fruits = new ArrayList<>();
		fruits.add("사과"); fruits.add("귤"); fruits.add("바나나");
		model.addAttribute("fruits", fruits);
		return "basic/basic2";
	}
	
}
