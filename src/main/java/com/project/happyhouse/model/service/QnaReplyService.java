package com.project.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.project.happyhouse.model.ArticleDto;
import com.project.happyhouse.model.QnaArticleDto;
import com.project.util.PageNavigation;

public interface QnaReplyService {
	List<QnaArticleDto> qnaGetnoticelist(Map<String, Object> map);

	PageNavigation qnaMakePageNavigation(Map<String, Object> map) throws SQLException;
	
	QnaArticleDto qnaGetnoticedetail(int articleno);

	int qnaNoticewrite(QnaArticleDto noticeDto);

	int qnaNoticedelete(int articleno);

	int qnaNoticeupdate(QnaArticleDto articleDto);
}
