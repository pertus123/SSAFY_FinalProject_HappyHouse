package com.project.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.project.happyhouse.model.NoticeDto;

public interface ArticleMapper {

	List<NoticeDto> getnoticelist(Map<String, Object> map);

	int getTotalCount(String key, String word) throws SQLException;
	
	NoticeDto getnoticedetail(int articleno);

	int noticewrite(NoticeDto noticeDto);

	int noticedelete(int articleno);
}
