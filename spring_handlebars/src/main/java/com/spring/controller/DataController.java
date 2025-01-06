package com.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.DataVO;

@RestController
@RequestMapping("/data")
public class DataController {

	@GetMapping("/class")
	public DataVO getData() {
		DataVO data = new DataVO();
		data.setA("111");
		data.setB("222");
		data.setC("333");
		data.setToday(new Date());
		
		return data;
	}
	
	
	@GetMapping("/map")
	public Map<String,DataVO> getMap(){
		Map<String,DataVO> dataMap = new HashMap<String,DataVO>();
		
		DataVO data = new DataVO("aaa","bbb","ccc",new Date());
		
		dataMap.put("data", data);
		
		return dataMap; 
	}
	
	@GetMapping("/list")
	public List<DataVO> getList(){
		List<DataVO> dataList = new ArrayList<DataVO>();
		
		for(int i=1;i<11;i++) {
			dataList.add(new DataVO("data"+i,"data"+i,"data"+i,new Date()));
		}
		
		return dataList;
	}
}








