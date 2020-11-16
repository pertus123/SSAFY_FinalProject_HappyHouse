package com.project.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.project.happyhouse.model.ArticleDto;
import com.project.util.PageNavigation;

public interface ArticleService {

	List<ArticleDto> getnoticelist(Map<String, Object> map);
	
	PageNavigation makePageNavigation(Map<String, Object> map) throws SQLException;

	ArticleDto getnoticedetail(int articleno);

	int noticewrite(ArticleDto noticeDto);

	int noticedelete(int articleno);
	
	int noticeupdate(ArticleDto articleDto);
	////////////////////////////////////////////////////
}
