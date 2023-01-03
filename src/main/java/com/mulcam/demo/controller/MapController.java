package com.mulcam.demo.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/map")
public class MapController {

	@Value("${naver.accessId}")
	private String accessId;
	@Value("${naver.secretKey}")
	private String secretKey;
	
	
	@ResponseBody
	@GetMapping("/staticMap")
	public String staticMap() {
//		Properties props = new Properties();
//		try {
//			InputStream is = new FileInputStream("/Workspace/naver.properties");
//			props.load(is);
//			is.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		String accessId = props.getProperty("accessId");
//		String secretKey = props.getProperty("secretKey");
		
		return "accessId: " + accessId + "<br>secretKey: " + secretKey;
	}
}
