package com.project.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.SearchBean;
import com.ssafy.happyhouse.model.dao.HouseDaoImpl;

public class HouseServiceImpl implements HouseService {

	private static HouseService houseService;
	
	private HouseServiceImpl() {}
	
	public static HouseService getHouseService() {
		if(houseService == null)
			houseService = new HouseServiceImpl();
		return houseService;
	}
	
	@Override
	public List<HouseDealDto> search(SearchBean bean) throws Exception {
		return HouseDaoImpl.getHouseDao().search(bean);
	}

	@Override
	public HouseDealDto getDealInfo(int no) throws Exception {
		return HouseDaoImpl.getHouseDao().getDealInfo(no);
	}

	@Override
	public List<HospitalDto> getHospital(String type, String code) throws Exception {
		return HouseDaoImpl.getHouseDao().getHospital(type, code);
	}

}
