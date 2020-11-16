package com.project.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.project.happyhouse.model.ArticleDto;
import com.project.happyhouse.model.QnaArticleDto;

public interface QnaArticleMapper {
	////////////////////////////////////////////////////
	List<QnaArticleDto> qnaGetnoticelist(Map<String, Object> map);

	int qnaGetTotalCount(String key, String word) throws SQLException;
	
	QnaArticleDto qnaGetnoticedetail(int articleno);

	int qnaNoticewrite(QnaArticleDto noticeDto);

	int qnaNoticedelete(int articleno);
	
	int qnaNoticeupdate(QnaArticleDto articleDto);
}
