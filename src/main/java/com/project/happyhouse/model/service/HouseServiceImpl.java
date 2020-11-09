package com.project.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.happyhouse.model.HospitalDto;
import com.project.happyhouse.model.HouseDealDto;
import com.project.happyhouse.model.SearchBean;
import com.project.happyhouse.model.mapper.ArticleMapper;
import com.project.happyhouse.model.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<HouseDealDto> searchZero(SearchBean bean) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(HouseMapper.class).searchZero(bean);
	}
	public List<HouseDealDto> searchOne(SearchBean bean) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(HouseMapper.class).searchOne(bean);
	}

	@Override
	public HouseDealDto getDealInfo(int no) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(HouseMapper.class).getDealInfo(no);
	}

	@Override
	public List<HospitalDto> getHospital(String type, String code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(HouseMapper.class).getHospital(type, code);
	}
	

}
