package com.project.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.util.PageNavigation;

public interface NoticeService {

	List<NoticeDto> getnoticelist(int currentPage, int sizePerPage, String key, String word);
	
	PageNavigation makePageNavigation(int currentPage, int sizePerPage, String key, String word) throws SQLException;

	NoticeDto getnoticedetail(int articleno);

	int noticewrite(NoticeDto noticeDto);

	int noticedelete(int articleno);
}
