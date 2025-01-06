package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.LocationVO;

public interface LocationDAO {

	
	List<String> selectSiList()throws SQLException;
	List<String> selectGuList(String si)throws SQLException;
	List<LocationVO> selectDongList(String gu)throws SQLException;
	
	LocationVO selectLocationByCode(String code) throws SQLException;
}
