package com.project.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.dao.NoticeDaoImpl;
import com.ssafy.util.PageNavigation;

public class NoticeServiceImpl implements NoticeService {

	private static NoticeService noticeService;

	private NoticeServiceImpl() {
	}

	public static NoticeService getNoticeService() {
		if (noticeService == null)
			noticeService = new NoticeServiceImpl();
		return noticeService;
	}

	@Override
	public List<NoticeDto> getnoticelist(int currentPage, int sizePerPage, String key, String word) {
		key = key == null ? "" : key;
		word = word == null ? "" : word;
		return NoticeDaoImpl.getNoticeDao().getnoticelist(currentPage, sizePerPage, key, word);
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

}
