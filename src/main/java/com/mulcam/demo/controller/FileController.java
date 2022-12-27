package com.mulcam.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mulcam.demo.entity.FileEntity;

@Component
@RequestMapping("/file")
public class FileController {

	@GetMapping("/upload")
	public String uploadForm() {
		return "file/upload";
	}
	
	@ResponseBody
	@PostMapping("/upload") 
	public String upload(@RequestParam MultipartFile[] files, Model model) {
		List<FileEntity> list = new ArrayList<>();
		for (MultipartFile file: files) {
			FileEntity fe = new FileEntity();
			fe.setFileName(file.getOriginalFilename());
			fe.setContentType(file.getContentType());
			list.add(fe); 			// file 이름 받아오기
			
			//물리적 저장
			File fileName = new File(file.getOriginalFilename());
			try {
				file.transferTo(fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String data = "";
		for (FileEntity fe : list)
			data += fe.toString() + "<br>";
		return "<h3>" + data + "</h3>";
//		model.addAttribute("uploadFiles", list);
//		return "file/result";
	}
}
