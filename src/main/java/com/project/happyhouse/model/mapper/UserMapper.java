package com.project.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.UserDto;

public interface UserMapper {

	public int join(UserDto memberdto);
	public UserDto login(String userid, String userpwd) throws SQLException;
	public List<UserDto> usermodify();
	public List<UserDto> userearchbyid(String keyword);
	public List<UserDto> userearchbyname(String keyword);
	public UserDto getuserinform(String userid);
	public int userinformupdate(UserDto userDto);
	public String pwdvalidate(String userid);
	public int userpwdupdate(UserDto userDto);
	public int userleave(String userid);
	public List<String> getinterestloc(String userid);
}
