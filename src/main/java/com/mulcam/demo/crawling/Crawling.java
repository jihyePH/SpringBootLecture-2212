package com.mulcam.demo.crawling;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
public class Crawling {

	public List<Interpark> interpark() throws Exception {
		String url = "http://book.interpark.com/display/collectlist.do?_method=BestsellerHourNew201605&bestTp=1&dispNo=028#";
		Document doc = Jsoup.connect(url).get();
		Elements lis = doc.select(".rankBestContentList > ol > li");
		
		List<Interpark> list = new ArrayList<>();
		for (Element li: lis) {
			Elements spans = li.select(".rankNumber.digit2").select("span");
			String rank_ = "";
			for (Element span: spans) {
				String classes = span.attr("class").strip();
				rank_ += classes.substring(classes.length() - 1);
			}
			int rank = Integer.parseInt(rank_);
			String src = li.select(".coverImage img").attr("src");
			String title = li.select(".itemName").text().strip();
			String author = li.select(".author").text().strip();
			String company = li.select(".company").text().strip();
			String price_ = li.select(".price > em").text().strip();
			int price = Integer.parseInt(price_.replace(",", ""));
			Interpark book = new Interpark(rank, src, title, author, company, price);
			list.add(book);
		}
		return list;
	}
	
	public List<Genie> genie() throws Exception{
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
		return list;
	}
	
	// Driver
		private static WebDriver driver;
		// Properties
		private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
		private static final String WEB_DRIVER_PATH = "/DevTools/chromedriver_win32/chromedriver.exe";

	public List<FireStation> fireStation() throws Exception{
		// Driver Setup
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		driver = new ChromeDriver();
		
		String url = "https://www.nfa.go.kr/nfa/introduce/status/firestationidfo";		// 소방청-> 전국소방기관안내
		driver.get(url);				// 브라우저 뜸 
		Thread.sleep(2000);   			// 2초 지연
		
		WebElement inputBox = driver.findElement(By.cssSelector("#searchKeyword"));
		inputBox.sendKeys("서울");
		WebElement searchBtn = driver.findElement(By.cssSelector("#fsSearchBtn"));
		searchBtn.click();
		Thread.sleep(2000);  
		
		String xPath = "//*[@id=\"listForm\"]/div/section/div/p/strong[2]"; 		//건수
		String num_ = driver.findElement(By.xpath(xPath)).getText().strip();		// 100건
		int num = Integer.parseInt(num_.substring(0, num_.length() -1 ));
		int pages = (int)Math.ceil(num/10);
		
		List<FireStation> list = new ArrayList<>();
		for (int page =1; page <= pages; page++) {
			if (page > 1 && page % 2 == 0) {
				driver.findElement(By.xpath("//*[@id=\"listForm\"]/div/section/ul/li[1]/div/div/ul/li[4]/a")).click();
				Thread.sleep(2000);
			}
			if (page > 1 && page %2 ==1) {
				driver.findElement(By.cssSelector(".next_page")).click();
				Thread.sleep(1000);
			}
			Document doc = Jsoup.parse(driver.getPageSource());
			Elements lis = doc.select(".stations-list > li");
			for (Element li: lis) {
				String name = li.select(".title").text().strip();		// 동작소방서
				String addr = li.select("address").text().strip();		
				String tel = li.select(".tel").text().strip();
				FireStation fs = new FireStation(name, addr, tel);
				list.add(fs);
			}
		}
		System.out.println(list.size());		//100
		
		driver.quit();
		return list;
	}
}