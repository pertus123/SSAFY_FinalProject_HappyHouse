package com.project.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.project.happyhouse.model.ArticleDto;

public interface ArticleMapper {

	List<ArticleDto> getnoticelist(Map<String, Object> map);

	int getTotalCount(String key, String word) throws SQLException;
	
	ArticleDto getnoticedetail(int articleno);

	int noticewrite(ArticleDto noticeDto);

	int noticedelete(int articleno);
	
	int noticeupdate(ArticleDto articleDto);
}
