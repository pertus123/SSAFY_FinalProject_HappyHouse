package com.project.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.happyhouse.model.UserDto;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public UserDto login(String userid, String userpwd) throws Exception {
		if (userid == null || userpwd == null) // BL
			return null;
		return UserDaoImpl.getUserDao().login(userid, userpwd);
	}

	@Override
	public int join(UserDto memberDto) throws Exception {
		return UserDaoImpl.getUserDao().join(memberDto);
	}

	@Override
	public List<UserDto> usermodify() {
		return UserDaoImpl.getUserDao().usermodify();
	}

	@Override
	public List<UserDto> userearchbyid(String keyword) {
		return UserDaoImpl.getUserDao().userearchbyid(keyword);
	}

	@Override
	public List<UserDto> userearchbyname(String keyword) {
		return UserDaoImpl.getUserDao().userearchbyname(keyword);
	}

	@Override
	public UserDto getuserinform(String userid) {
		return UserDaoImpl.getUserDao().getuserinform(userid);
	}

	@Override
	public int userinformupdate(UserDto userDto) {
		return UserDaoImpl.getUserDao().userinformupdate(userDto);
	}

	@Override
	public String pwdvalidate(String userid) {
		return UserDaoImpl.getUserDao().pwdvalidate(userid);
	}

	@Override
	public int userpwdupdate(UserDto userDto) {
		return UserDaoImpl.getUserDao().userpwdupdate(userDto);
	}

	@Override
	public int userleave(String userid) {
		return UserDaoImpl.getUserDao().userleave(userid);
	}

	@Override
	public List<String> getinterestloc(String userid) {
		return UserDaoImpl.getUserDao().getinterestloc(userid);
	}

}
