package com.project.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.NoticeDto;

public interface NoticeMapper {

	List<NoticeDto> getnoticelist(int currentPage, int sizePerPage, String key, String word);

	int getTotalCount(String key, String word) throws SQLException;
	
	NoticeDto getnoticedetail(int articleno);

	int noticewrite(NoticeDto noticeDto);

	int noticedelete(int articleno);
}
