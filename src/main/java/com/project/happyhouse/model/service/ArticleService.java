package com.project.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.project.happyhouse.model.NoticeDto;
import com.project.util.PageNavigation;

public interface ArticleService {

	List<NoticeDto> getnoticelist(int currentPage, int sizePerPage, String key, String word);
	
	PageNavigation makePageNavigation(int currentPage, int sizePerPage, String key, String word) throws SQLException;

	NoticeDto getnoticedetail(int articleno);

	int noticewrite(NoticeDto noticeDto);

	int noticedelete(int articleno);
}
