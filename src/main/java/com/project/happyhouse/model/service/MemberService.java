package com.project.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.project.happyhouse.model.MemberDto;

public interface MemberService {

	public int join(MemberDto memberdto) throws Exception;
	public MemberDto login(Map<String, String> map) throws Exception;
	List<MemberDto> userList();
	List<MemberDto> userSearchById(String keyword);
	List<MemberDto> userSearchByName(String keyword);
	MemberDto getUserInform(String userid);
	int userInformUpdate(MemberDto userDto);
	String pwdValidate(String userid);
	int userPwdUpdate(MemberDto userDto);
	int userLeave(String userid);
	List<String> getInterestLoc(String userid);
	
}
