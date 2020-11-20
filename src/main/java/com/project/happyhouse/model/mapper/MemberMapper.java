package com.project.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.project.happyhouse.model.MemberDto;

public interface MemberMapper {

	// 회원가입 
	public int join(MemberDto memberdto);
	// 로그인
	public MemberDto login(MemberDto memberDto) throws SQLException;
	// 회원 목록 (Admin)
	public List<MemberDto> userList();
	// 아이디로 회원 검색 (Admin)
	public List<MemberDto> userSearchById(String keyword);
	// 이름으로 회원 검색 (Admin)
	public List<MemberDto> userSearchByName(String keyword);
	// 회원정보(마이페이지)
	public MemberDto getUserInform(String userid);
	// 회원정보 수정(마이페이지)
	public int userInformUpdate(MemberDto userDto);
	// 비밀번호 확인(마이페이지)
	public String pwdValidate(String userid);
	// 비밀번호 수정(마이페이지)
	public int userPwdUpdate(MemberDto userDto);
	// 회원탈퇴(마이페이지)
	public int userLeave(String userid);
	// 관심지역리스트(마이페이지)
	public List<String> getInterestLoc(String userid);
}
