package com.mulcam.demo.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/detect")
public class DetectController {
	@Value("${naver.accessId}") private String accessId;
	@Value("${naver.secretKey}") private String secretKey;
	
	@ResponseBody 		// 결과가 화면에 나오게
	@GetMapping("/naver")
		public String naver() throws Exception {
			String apiURL = "https://naveropenapi.apigw.ntruss.com/vision-obj/v1/detect"; // 객체인식
			File uploadFile = new File("c:/Temp/yolo-test1.jpg"); 	// (img)파일 업로드
			
			URL url = new URL(apiURL);			// connection위해 1st로
			HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 	//connection -> read,write
			conn.setUseCaches(false);
            conn.setDoOutput(true); 			// ouput
            conn.setDoInput(true);				// input
			conn.setRequestMethod("POST");		//생략가능
            
           // multipart request
			String boundary = "---" + System.currentTimeMillis() + "---";
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", accessId);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY", secretKey);
			
			// 파일 전송 준비
            OutputStream os = conn.getOutputStream();
            PrintWriter out = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);
            String LINE_FEED = "\n";						// line feed
            String fileName = uploadFile.getName(); 		//file 추가
            out.append("--" + boundary).append(LINE_FEED);
            out.append("Content-Disposition: form-data; name=\"image\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            out.append("Content-Type: "  + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
            out.append(LINE_FEED).flush();
            
            // 실제 파일 읽어서 전송 
            FileInputStream fis = new FileInputStream(uploadFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1; 
            while((bytesRead = fis.read(buffer)) != -1)
            	os.write(buffer, 0, bytesRead);  // buffer의 첫번째부터 내가 읽은 데이터수 만큼
            fis.close();
            os.flush();	
            out.append(LINE_FEED);
            out.append("--" + boundary + "--").append(LINE_FEED).flush();
            out.close();
            
            // 응답 결과 확인
    		int responseCode = conn.getResponseCode();
    		if (responseCode != 200) 			// 오류 발생
    			System.out.println("error!!!!!!! responseCode= " + responseCode);
            
            // 결과 확인
            BufferedReader br =  new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer(); 		//여러명사용시 Buffer
            String line;
            while((line = br.readLine()) != null)
            	sb.append(line);
            br.close();
            
            
            return sb.toString();
	}
}
