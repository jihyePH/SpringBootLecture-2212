package com.mulcam.demo.crawling;


import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class InterparkExercise {

	public static void main(String[] args) throws Exception {
		// Interpark 주간 베스트셀러
		String url = "http://book.interpark.com/display/collectlist.do?_method=BestsellerHourNew201605&bestTp=1&dispNo=028#";
		// 사이트에 접속해서 html 데이터 가져온 후 Parsing
		Document doc = Jsoup.connect(url).get();		//Jsoup과 연결(connect)

		// 사이트에서 찾고자하는 항목들
		Elements lis = doc.select(".rankBestContentList > ol > li");
//		System.out.println(lis.size());				// li 개수
		
		Element li = lis.get(12);
		String title = li.select(".itemName").text().strip();
		String author = li.select(".author").text().strip();
		String company = li.select(".company").text().strip();
		
		// 이미지 주소 알아내기
//		Element img = li.selectFirst(".coverImage").selectFirst("img");
//		String src = img.attr("src");
		String src = li.select(".coverImage img").attr("src");	// 자손 셀렉터
		
		//String 조작
		String price_ = li.select(".price > em").text().strip();		// 19,800
		int price = Integer.parseInt(price_.replace(",", ""));			// 19800
		System.out.println(price);
		
		// 순위 
		Elements spans = li.select(".rankNumber.digit2").select("span");  	// rankNumber digit2 -> 각각class :빈칸마다 . 
		String rank_ = "";
		for (Element span: spans) {
			String classes = span.attr("class").strip();
//			System.out.println(classes);
			rank_ += classes.substring(classes.length() - 1);
		}
		int rank =  Integer.parseInt(rank_);
		System.out.println(rank);
		
		// Data 정리
		Interpark book = new Interpark(rank, src, title, author, company, price);	//1개만 나옴
		System.out.println(book);
		
		// 반복문으로 데이터 정리
//				List<Interpark> list = new ArrayList<>();
//				for (Element li: lis) {
//					Elements spans = li.select(".rankNumber.digit2").select("span");
//					String rank_ = "";
//					for (Element span: spans) {
//						String classes = span.attr("class").strip();
//						rank_ += classes.substring(classes.length() - 1);
//					}
//					int rank = Integer.parseInt(rank_);
//					String src = li.select(".coverImage img").attr("src");
//					String title = li.select(".itemName").text().strip();
//					String author = li.select(".author").text().strip();
//					String company = li.select(".company").text().strip();
//					String price_ = li.select(".price > em").text().strip();
//					int price = Integer.parseInt(price_.replace(",", ""));
//					Interpark book = new Interpark(rank, src, title, author, company, price);
//					list.add(book);
//				}
//				list.forEach(x -> System.out.println(x));
		}
	}


