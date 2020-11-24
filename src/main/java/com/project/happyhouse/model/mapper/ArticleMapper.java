package com.project.happyhouse.model.mapper;

import java.util.List;

import com.project.happyhouse.model.ArticleDto;

public interface ArticleMapper {
	
	List<ArticleDto> getmainnoticelist();

	List<ArticleDto> getnoticelist();
	
	ArticleDto getnoticedetail(int articleno);

	int noticewrite(ArticleDto noticeDto);

	int noticedelete(int articleno);
	
	int noticeupdate(ArticleDto articleDto);
}
