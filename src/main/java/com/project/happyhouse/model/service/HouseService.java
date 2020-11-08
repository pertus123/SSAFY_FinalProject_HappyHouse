package com.project.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.SearchBean;

public interface HouseService {
	List<HouseDealDto> search(SearchBean bean) throws Exception;
	HouseDealDto getDealInfo(int no) throws Exception;	
	List<HospitalDto> getHospital(String type, String code) throws Exception;	
}
