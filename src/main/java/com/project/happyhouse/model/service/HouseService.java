package com.project.happyhouse.model.service;

import java.util.List;

import com.project.happyhouse.model.HospitalDto;
import com.project.happyhouse.model.HouseDealDto;
import com.project.happyhouse.model.SearchBean;

public interface HouseService {
	List<HouseDealDto> search(SearchBean bean) throws Exception;
	HouseDealDto getDealInfo(int no) throws Exception;	
	List<HospitalDto> getHospital(String type, String code) throws Exception;	
}
