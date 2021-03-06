package com.project.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.happyhouse.model.ArticleDto;
import com.project.happyhouse.model.QnaArticleDto;
import com.project.happyhouse.model.QnaReplyDto;
import com.project.happyhouse.model.mapper.ArticleMapper;
import com.project.happyhouse.model.mapper.QnaArticleMapper;
import com.project.happyhouse.model.mapper.QnaReplyMapper;
import com.project.util.PageNavigation;

@Service
public class QnaReplyServiceImpl implements QnaReplyService {
	@Autowired
	private SqlSession sqlSession;

//////////////////////////////////////////////////////////////////////
	
	
	
	@Override
	public List<QnaReplyDto> qnaGetreplylist(int articleno) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(QnaReplyMapper.class).qnaGetreplylist(articleno);
	}

	@Override
	public int qnaReplywrite(QnaReplyDto noticeDto) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(QnaReplyMapper.class).qnaReplywrite(noticeDto);
	}

	@Override
	public int qnaReplyupdate(QnaReplyDto articleDto) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(QnaReplyMapper.class).qnaReplyupdate(articleDto);
	}

	@Override
	public int qnaReplydelete(int articleno) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(QnaReplyMapper.class).qnaReplydelete(articleno);
	}



	
	
	
	
	
	
	
	

	//@Override
/*	public List<NoticeDto> getnoticelist(int currentPage, int sizePerPage, String key, String word) {
		key = key == null ? "" : key;
		word = word == null ? "" : word;
		sqlSession.getMapper(ArticleMapper.class).getnoticelist(currentPage, sizePerPage, key, word);
		return NoticeDaoImpl.getNoticeDao().getnoticelist(currentPage, sizePerPage, key, word);
	}
	
	/*
	 * 
	 * 
	 	@Override
	public Member login(Map<String, String> map) throws Exception{
		return sqlSession.getMapper(MemberMapper.class).login(map);
	}



	@Override
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage, String key, String word) throws SQLException {
///////////////
		PageNavigation pageNavigation = new PageNavigation();
		int naviSize = 10;
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = NoticeDaoImpl.getNoticeDao().getTotalCount(key, word);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public NoticeDto getnoticedetail(int articleno) {
		return NoticeDaoImpl.getNoticeDao().getnoticedetail(articleno);
	}

	@Override
	public int noticewrite(NoticeDto noticeDto) {
		return NoticeDaoImpl.getNoticeDao().noticewrite(noticeDto);
	}

	@Override
	public int noticedelete(int articleno) {
		return NoticeDaoImpl.getNoticeDao().noticedelete(articleno);
	}

	@Override
	public List<NoticeDto> getnoticelist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, Object> map) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}*/

}
