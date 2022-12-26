package com.mulcam.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

	@RequestMapping("/hello")
	public String hello() {
		return "<h1>Hello World!!!</h1>";
	}
	
	@RequestMapping("/hello2")
	public String hello2() {
		String data = "<h1>안녕하세요!!!</h1>"
					+ "<hr>"
					+ "<h3>@RestController는 반환하는 문자열을 @RequestMapping 주소로 웹화면을 만들어줍니다.</h3>";
		return data;
	}
}
