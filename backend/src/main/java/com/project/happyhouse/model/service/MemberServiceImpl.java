package com.project.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.happyhouse.model.MemberDto;
import com.project.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public int join(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).join(memberDto);
	}

	@Override
	public List<MemberDto> userList() {
		return sqlSession.getMapper(MemberMapper.class).userList();
	}

	@Override
	public List<MemberDto> userSearchById(String keyword) {
		return sqlSession.getMapper(MemberMapper.class).userSearchById(keyword);
	}

	@Override
	public List<MemberDto> userSearchByName(String keyword) {
		return sqlSession.getMapper(MemberMapper.class).userSearchByName(keyword);
	}

	@Override
	public MemberDto getUserInform(String userid) {
		return sqlSession.getMapper(MemberMapper.class).getUserInform(userid);
	}

	@Override
	public int userInformUpdate(MemberDto userDto) {
		return sqlSession.getMapper(MemberMapper.class).userInformUpdate(userDto);
	}

	@Override
	public String pwdValidate(String userid) {
		return sqlSession.getMapper(MemberMapper.class).pwdValidate(userid);
	}

	@Override
	public int userPwdUpdate(MemberDto userDto) {
		return sqlSession.getMapper(MemberMapper.class).userPwdUpdate(userDto);
	}

	@Override
	public int userLeave(String userid) {
		return sqlSession.getMapper(MemberMapper.class).userLeave(userid);
	}

	@Override
	public List<String> getInterestLoc(String userid) {
		return sqlSession.getMapper(MemberMapper.class).getInterestLoc(userid);
	}

}
