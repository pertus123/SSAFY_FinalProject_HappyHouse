package com.project.happyhouse.model.service;

import java.util.List;

import com.project.happyhouse.model.ArticleDto;

public interface ArticleService {

	List<ArticleDto> getmainnoticelist();
	
	List<ArticleDto> getnoticelist();

	ArticleDto getnoticedetail(int articleno);

	int noticewrite(ArticleDto noticeDto);

	int noticedelete(int articleno);
	
	int noticeupdate(ArticleDto articleDto);
	////////////////////////////////////////////////////
}
