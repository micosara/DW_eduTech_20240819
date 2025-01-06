package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.dto.LocationVO;

public class LocationDAOImpl implements LocationDAO{

	private SqlSession session;
	public LocationDAOImpl(SqlSession session) {
		this.session = session;
	}
	

	@Override
	public LocationVO selectLocationByCode(String code) throws SQLException {
		return session.selectOne("Location-Mapper.selectLocationByCode",code);
	}

	@Override
	public List<String> selectSiList() throws SQLException {		
		return session.selectList("Location-Mapper.selectSiList");
	}
	@Override
	public List<String> selectGuList(String si) throws SQLException {
		return session.selectList("Location-Mapper.selectGuList",si);
	}
	@Override
	public List<LocationVO> selectDongList(String gu) throws SQLException {
		return session.selectList("Location-Mapper.selectDongList",gu);
	}

}
