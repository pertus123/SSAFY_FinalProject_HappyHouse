package com.project.happyhouse.model.mapper;

import java.util.List;

import com.project.happyhouse.model.HospitalDto;
import com.project.happyhouse.model.HouseDealDto;
import com.project.happyhouse.model.SearchBean;

public interface HouseMapper {

	
	List<HouseDealDto> searchZero(SearchBean bean) throws Exception;
	List<HouseDealDto> searchOne(SearchBean bean) throws Exception;
	HouseDealDto getDealInfo(int no) throws Exception;	
	List<HospitalDto> getHospital(String type, String code) throws Exception;	
}
