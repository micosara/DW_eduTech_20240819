package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.LocationVO;

public interface LocationService {

	List<String> listSi()throws SQLException;
	List<String> listGu(String si)throws SQLException;
	List<LocationVO> listDong(String gu)throws SQLException;
	
	LocationVO findLocation(String code)throws SQLException;
}

