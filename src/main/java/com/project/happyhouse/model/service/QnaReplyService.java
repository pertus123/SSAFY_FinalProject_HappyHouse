package com.project.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.project.happyhouse.model.ArticleDto;
import com.project.happyhouse.model.QnaArticleDto;
import com.project.happyhouse.model.QnaReplyDto;
import com.project.util.PageNavigation;

public interface QnaReplyService {
	List<QnaReplyDto> qnaGetreplylist(QnaReplyDto map);

	int qnaReplywrite(QnaReplyDto noticeDto);

	int qnaReplyupdate(QnaReplyDto articleDto);
	
	int qnaReplydelete(int articleno);
}
