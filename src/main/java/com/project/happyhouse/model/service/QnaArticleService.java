package com.project.happyhouse.model.service;

import java.util.List;

import com.project.happyhouse.model.QnaArticleDto;

public interface QnaArticleService {
	
	List<QnaArticleDto> getmainqnalist();
	
	List<QnaArticleDto> qnaGetnoticelist();
	
	QnaArticleDto qnaGetnoticedetail(int articleno);

	int qnaNoticewrite(QnaArticleDto noticeDto);

	int qnaNoticedelete(int articleno);

	int qnaNoticeupdate(QnaArticleDto articleDto);
}
