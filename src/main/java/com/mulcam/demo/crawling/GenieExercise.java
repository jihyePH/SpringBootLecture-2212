package com.mulcam.demo.crawling;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GenieExercise  {

	public static void main(String[] args) throws Exception {
/*		String url = "https://genie.co.kr/chart/top200";	// 지니뮤직 1~50위 사이트
		Document doc = Jsoup.connect(url).get();
		
		// 리스트
		Elements trs = doc.select("tr.list");		// <tr class="list"...>
		System.out.println(trs.size());				//50 (1~50위)
		Element tr = trs.get(0);
		
		// 랭킹
		String rank_ = tr.select(".number").text().split(" ")[0];
		int rank = Integer.parseInt(rank_);			// 1 
		
		// 앨범 이미지 
//		String src = tr.select(".cover > img").attr("src");		// https: 추가해줘야함
// 		//image.genie.co.kr/Y/IMAGE/IMG_ALBUM/083/325/577/83325577_1672649874616_1_140x140.JPG/dims/resize/Q_80,0

		// 노래 정보 
		String src = tr.select(".cover > img").attr("src");	
		String title = tr.select(".title.ellipsis").text().strip();
		String artist = tr.select(".artist.ellipsis").text().strip();
		String album = tr.select(".albumtitle.ellipsis").text().strip();
		System.out.println(title + ", " + artist + ", " + album); 	// Ditto, NewJeans, NewJeans 'OMG'
*/
		
		LocalDateTime now = LocalDateTime.now();
		String ymd = now.toString().substring(0, 10).replace("-", "");
		String hh = now.toString().substring(11, 13);
		
		// 페이지 Loop 
//		https://genie.co.kr/chart/top200?ditc=D&ymd=20230106&hh=13&rtm=Y&pg=1
		List<Genie> list = new ArrayList<>();
		for (int i =1; i <= 4; i++) {
			String url = "https://genie.co.kr/chart/top200?ditc=D&ymd=" + ymd
						+ "&hh=" + hh +  "&rtm=Y&pg=" + i; 
			Document doc = Jsoup.connect(url).get();
			Elements trs = doc.select("tr.list"); 		// 50개씩 추출
			
			for (Element tr: trs) {
				String rank_ = tr.select(".number").text().split(" ")[0];
				int rank = Integer.parseInt(rank_);	
				
				String src = "https:" + tr.select(".cover > img").attr("src");	
				String title = tr.select(".title.ellipsis").text().strip();
				String artist = tr.select(".artist.ellipsis").text().strip();
				String album = tr.select(".albumtitle.ellipsis").text().strip();
				Genie genie = new Genie(rank, src, title, artist, album);
				list.add(genie);
			}
		}
		System.out.println(list.size());		// 200
	}
}
