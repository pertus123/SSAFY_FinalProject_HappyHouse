package com.project.happyhouse.model.service;

import java.util.List;

import com.project.happyhouse.model.UserDto;

public interface MemberService {

	public int join(UserDto memberdto) throws Exception;
	public UserDto login(String userid, String userpwd) throws Exception;
	public List<UserDto> usermodify() throws Exception;
	public List<UserDto> userearchbyid(String keyword);
	public List<UserDto> userearchbyname(String keyword);
	public UserDto getuserinform(String userid);
	public int userinformupdate(UserDto userDto);
	public String pwdvalidate(String userid);
	public int userpwdupdate(UserDto userDto);
	public int userleave(String userid);
	public List<String> getinterestloc(String userid);
	
}
