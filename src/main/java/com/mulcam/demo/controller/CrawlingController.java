package com.mulcam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mulcam.demo.crawling.Crawling;
import com.mulcam.demo.crawling.Genie;
import com.mulcam.demo.crawling.Interpark;

@Controller
@RequestMapping("/crawling")
public class CrawlingController {

	@Autowired private Crawling crawling;     		// Crawling.java : @Service
	
	@GetMapping("/interpark")
	public String interpark(Model model) throws Exception {
		Crawling crawling = new Crawling();
		List<Interpark> list = crawling.interpark();
		model.addAttribute("bookList", list);
		return "crawling/interpark";
	}
	
	@GetMapping("/genie")
	public String genie(Model model) throws Exception {
		Crawling crawling = new Crawling();
		List<Genie> list = crawling.genie();
		model.addAttribute("songList", list);
		return "crawling/genie";
	}
	@GetMapping("/genie2")
	public String genie2(Model model) throws Exception {
		Crawling crawling = new Crawling();
		List<Genie> list = crawling.genie();
		model.addAttribute("songList", list);
		return "crawling/genie2";
	}
	
}