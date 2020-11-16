package com.project.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.project.happyhouse.model.QnaReplyDto;

public interface QnaReplyMapper {
	////////////////////////////////////////////////////
	List<QnaReplyDto> qnaGetreplylist(QnaReplyDto map);

	int qnaReplywrite(QnaReplyDto noticeDto);

	int qnaReplyupdate(QnaReplyDto articleDto);
	
	int qnaReplydelete(int articleno);
}
