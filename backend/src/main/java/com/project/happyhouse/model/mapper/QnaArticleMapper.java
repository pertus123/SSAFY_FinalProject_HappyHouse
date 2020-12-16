package com.project.happyhouse.model.mapper;

import java.util.List;

import com.project.happyhouse.model.QnaArticleDto;

public interface QnaArticleMapper {
	////////////////////////////////////////////////////
	List<QnaArticleDto> qnaGetnoticelist();
	
	QnaArticleDto qnaGetnoticedetail(int articleno);

	int qnaNoticewrite(QnaArticleDto noticeDto);

	int qnaNoticedelete(int articleno);
	
	int qnaNoticeupdate(QnaArticleDto articleDto);

	List<QnaArticleDto> getmainqnalist();
}
