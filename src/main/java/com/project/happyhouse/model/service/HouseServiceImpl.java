package com.project.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.happyhouse.model.HospitalDto;
import com.project.happyhouse.model.HouseDealDto;
import com.project.happyhouse.model.SearchBean;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<HouseDealDto> search(SearchBean bean) throws Exception {
		return sqlSession.getHouseDao().search(bean);
	}

	@Override
	public HouseDealDto getDealInfo(int no) throws Exception {
		return sqlSession.getHouseDao().getDealInfo(no);
	}

	@Override
	public List<HospitalDto> getHospital(String type, String code) throws Exception {
		return sqlSession.getHouseDao().getHospital(type, code);
	}

}
