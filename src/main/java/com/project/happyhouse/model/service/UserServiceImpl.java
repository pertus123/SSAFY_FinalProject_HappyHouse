package com.project.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {

	private static UserService userService;

	private UserServiceImpl() {
	}

	public static UserService getUserService() {
		if (userService == null)
			userService = new UserServiceImpl();
		return userService;
	}

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
