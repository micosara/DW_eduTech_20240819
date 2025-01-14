package com.spring.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.dto.LocationVO;
import com.spring.service.LocationService;

@Controller
public class WeatherController {

	@Autowired
	private LocationService service;
	
	@GetMapping("/location")
	public void location(Model model) throws Exception{
		List<String> listSi = service.listSi();
		
		model.addAttribute("listSi",listSi);
	}
	
	@GetMapping("/findGu")
	@ResponseBody
	public List<String> findGu(String si)throws Exception{
		List<String> listGu = service.listGu(si);
		return listGu;
	}
	
	
	@GetMapping("/findDong")
	@ResponseBody
	public List<LocationVO> findDong(String gu)throws Exception{
		return service.listDong(gu);
	}
	
	@GetMapping("/weather")
	@ResponseBody
	public Map<String,Object> weather(String district_code)throws Exception{
		Map<String,Object> dataMap=null;
		
		LocationVO loc = service.findLocation(district_code);
		System.out.println(loc.getXo()+":"+loc.getYo());
		
		dataMap = getWeather(loc.getXo(),loc.getYo());
		
		return dataMap;
	}
	
	private Map<String,Object> getWeather(int xo, int yo)throws Exception{
		Map<String,Object> dataMap=null;
		
		  StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=9mFR3xg3LYu%2BJNpcWd39FzCmx7DKy1KziZ4OQ8qu4bi1qxqJqnznTxR1mXDR6bg%2BmJAHWP%2BCXPCBmpkmEo0%2Bdg%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
	        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20250103", "UTF-8")); /*‘21년 6월 28일 발표*/
	        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("1500", "UTF-8")); /*06시 발표(정시단위) */
	        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(""+xo, "UTF-8")); /*예보지점의 X 좌표값*/
	        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(""+yo, "UTF-8")); /*예보지점의 Y 좌표값*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString());
	        
	        //json string -> map
	        dataMap = new ObjectMapper().readValue(sb.toString(),Map.class);
	        
		return dataMap;
	}
}
 










