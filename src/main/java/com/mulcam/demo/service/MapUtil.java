package com.mulcam.demo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;

public class MapUtil  {
	@Value("${naver.accessId}") private String accessId;
	@Value("${naver.secretKey}") private String secretKey;
	@Value("${roadAddrKey}") private String roadAddrKey;
	
	public String getAddr(String keyword) throws Exception {
		int currentPage = 1;
		int countPerPage = 10;
		String resultType = "json";
		keyword = URLEncoder.encode(keyword, "utf-8");
		String apiUrl = "https://www.juso.go.kr/addrlink/addrLinkApi.do"
					+ "?confmKey=" + roadAddrKey
					+ "&currentPage=" + currentPage
					+ "&countPerPage=" + countPerPage
					+ "&keyword=" + keyword
					+ "&resultType=" + resultType;
		
		URL url = new URL(apiUrl);	
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		StringBuffer sb = new StringBuffer();
		String line = null;
		
		while((line = br.readLine()) != null)
			sb.append(line);
		br.close();
			
		// JSON 데이터에서 원하는 값 추출하기
		JSONParser parser = new JSONParser();
		JSONObject object = (JSONObject) parser.parse(sb.toString());
		JSONObject results = (JSONObject) object.get("results");
		JSONArray juso = (JSONArray) results.get("juso");
		JSONObject jusoItem = (JSONObject) juso.get(0);
		String roadAddr = (String) jusoItem.get("roadAddr");
		
		return  sb.toString() + "<br>" + roadAddr;
	}
	public String roadAddr(@PathVariable String keyword) throws Exception {
		int currentPage = 1;
		int countPerPage = 10;
		String resultType = "json";
		keyword = URLEncoder.encode(keyword, "utf-8");
		String apiUrl = "https://www.juso.go.kr/addrlink/addrLinkApi.do"
					+ "?confmKey=" + roadAddrKey
					+ "&currentPage=" + currentPage
					+ "&countPerPage=" + countPerPage
					+ "&keyword=" + keyword
					+ "&resultType=" + resultType;
		
		URL url = new URL(apiUrl);	
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		StringBuffer sb = new StringBuffer();
		String line = null;
		
		while((line = br.readLine()) != null)
			sb.append(line);
		br.close();
			
		// JSON 데이터에서 원하는 값 추출하기
		JSONParser parser = new JSONParser();
		JSONObject object = (JSONObject) parser.parse(sb.toString());
		JSONObject results = (JSONObject) object.get("results");
		JSONArray juso = (JSONArray) results.get("juso");
		JSONObject jusoItem = (JSONObject) juso.get(0);
		String roadAddr = (String) jusoItem.get("roadAddr");
		
		return  sb.toString() + "<br>" + roadAddr;
	}
	
	/**
	 * 도로명 주소로부터 경도,위도 정보를 구해주는 메소드
	 * @return
	 * @throws Exception
	 */
	
	public List<String> geocode() throws Exception {
		String apiUrl = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode";
		String query = "서울특별시 광진구 자양로 117(자양동)";
		query = URLEncoder.encode(query, "utf-8");
		apiUrl += "?query=" + query;
		
		URL url = new URL(apiUrl);
		// 헤더 설정
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("&X-NCP-APIGW-API-KEY-ID", accessId);
		conn.setRequestProperty("&X-NCP-APIGW-API-KEY", secretKey);
		conn.setDoInput(true);
		
		//응답 결과 확인
		int responseCode = conn.getResponseCode();
		
		// 데이터 수신 
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer();
		String line = null;
		
		while((line = br.readLine()) != null)
			sb.append(line);
		br.close();
		
		JSONParser parser = new JSONParser();
		JSONObject object = (JSONObject) parser.parse(sb.toString());
//		JSONArray addresses = (JSONArray) object.get("addresses");
//		JSONObject address = (JSONObject) addresses.get(0);
		JSONObject address = (JSONObject) ((JSONArray) object.get("addresses")).get(0);
		String lng = (String) address.get("x");
		String lat = (String) address.get("y");
		
		List<String> list = new ArrayList <>();
		list.add(lng); list.add(lat);
		return list;
	}
	
}
