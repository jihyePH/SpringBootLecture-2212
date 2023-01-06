package com.mulcam.demo.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

@Controller
@RequestMapping("/detect")
public class DetectController {
	
	@Value("${naver.accessId}") private String accessId;
	@Value("${naver.secretKey}") private String secretKey;
	@Value("${etriKey}") private String etriKey;
	@Value("${spring.servlet.multipart.location}") private String uploadDir;

	@GetMapping("/naver")
	public String naverForm() {
		return "detect/naverForm";
	}
	
	@PostMapping("/naver")
	public String naver(MultipartFile upload, Model model) throws Exception {
		File uploadFile = new File(upload.getOriginalFilename());
		upload.transferTo(uploadFile);				// uploadDir에 파일 저장
		
		String apiURL = "https://naveropenapi.apigw.ntruss.com/vision-obj/v1/detect"; // 객체 인식
		URL url = new URL(apiURL);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setUseCaches(false);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST"); 		// 생략 가능
        
        // multipart request
        String boundary = "---" + System.currentTimeMillis() + "---";
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", accessId);
        conn.setRequestProperty("X-NCP-APIGW-API-KEY", secretKey);
        
        // 파일 전송 준비
        OutputStream os = conn.getOutputStream();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);
        String LF = "\n";		// line feed
        String fileName = uploadFile.getName();
        out.append("--" + boundary).append(LF);
        out.append("Content-Disposition: form-data; name=\"image\"; filename=\"" + fileName + "\"").append(LF);
        out.append("Content-Type: "  + URLConnection.guessContentTypeFromName(fileName)).append(LF);
        out.append(LF).flush();
        
        // 실제 파일을 읽어서 전송
        FileInputStream fis = new FileInputStream(uploadDir + "/" + uploadFile);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = fis.read(buffer)) != -1)
        	os.write(buffer, 0, bytesRead);			// buffer의 처음부터 읽은 데이터 수 만큼 전송
        os.flush();
        fis.close();
        out.append(LF);
        out.append("--" + boundary + "--").append(LF).flush();
        out.close();
        
        // 응답 결과 확인
 		int responseCode = conn.getResponseCode();
 		if (responseCode != 200)
 			System.out.println("error!!!!!!! responseCode= " + responseCode);
        
        // 결과 확인
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null)
        	sb.append(line);
        br.close();
        
        model.addAttribute("fileName", fileName);
        model.addAttribute("jsonResult", sb.toString());
		return "detect/naverResult";
	}
	
	@ResponseBody
	@GetMapping("/etri")
	public String etri() throws Exception {
		String openApiURL = "http://aiopen.etri.re.kr:8000/ObjectDetect";
        String accessKey = etriKey;    // 발급받은 API Key
        String type = ".jpg";     // 이미지 파일 확장자
        String file = "/Temp/yolo-test1.jpg";    // 이미지 파일 경로
        String imageContents = "";
        Gson gson = new Gson();
		
        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();
 
        Path path = Paths.get(file);
        byte[] imageBytes = Files.readAllBytes(path);
        imageContents = Base64.getEncoder().encodeToString(imageBytes);
        
        argument.put("type", type);
        argument.put("file", imageContents);
        request.put("argument", argument);
        
        URL url = new URL(openApiURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Authorization", accessKey); 	
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.write(gson.toJson(request).getBytes("UTF-8"));
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();
        InputStream is = conn.getInputStream();
        byte[] buffer = new byte[is.available()];
        int byteRead = is.read(buffer);
        String responBody = new String(buffer);

        String data = "[responseCode] " + responseCode + "<br>"
    			+ "[responBody]" + "<br>"
    			+ responBody;
    
        return data;
	}
	
}
