package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.dao.LocationDAO;
import com.spring.dto.LocationVO;

public class LocationServiceImpl implements LocationService{

	private LocationDAO locationDAO;
	public LocationServiceImpl(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

	@Override
	public LocationVO findLocation(String code) throws SQLException {
		return locationDAO.selectLocationByCode(code);
	}
	@Override
	public List<String> listSi() throws SQLException {
		return locationDAO.selectSiList();
	}
	@Override
	public List<String> listGu(String si) throws SQLException {		
		return locationDAO.selectGuList(si);
	}
	@Override
	public List<LocationVO> listDong(String gu) throws SQLException {
		return locationDAO.selectDongList(gu);
	}
}



