package com.project.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.project.happyhouse.model.NoticeDto;
import com.project.util.PageNavigation;

public interface ArticleService {

	List<NoticeDto> getnoticelist(Map<String, Object> map);
	
	PageNavigation makePageNavigation(Map<String, Object> map) throws SQLException;

	NoticeDto getnoticedetail(int articleno);

	int noticewrite(NoticeDto noticeDto);

	int noticedelete(int articleno);
}
