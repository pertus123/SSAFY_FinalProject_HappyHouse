package com.project.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.happyhouse.model.MemberDto;
import com.project.happyhouse.model.service.JwtService;
import com.project.happyhouse.model.service.MemberService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private JwtService jwtService;

	@Autowired
	private MemberService memberService;

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@ApiOperation(value = "회원가입 - 회원의 정보를 입력한다", response = String.class)
	@PostMapping(value = "/join")
	public ResponseEntity<String> join(@RequestBody MemberDto memberDto) throws Exception {
		logger.debug("회원가입 - 호출 : memberDto = " + memberDto);
		if (memberService.join(memberDto) > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "아이디 인증(비밀번호 가져오기)", response = String.class)
	@PostMapping(value = "/idValidate")
	public ResponseEntity<String> idValidate(@RequestBody String userid) throws Exception {
		logger.debug("아이디인증 : userid = " + userid);
		logger.debug(memberService.pwdValidate(userid));
		if (memberService.pwdValidate(userid) != null) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "비밀번호 변경(찾기) (로그인, 마이페이지)", response = String.class)
	@PutMapping(value = "/updatePassword")
	public ResponseEntity<String> updatePassword(@RequestBody MemberDto memberDto) {
		logger.debug("비밀번호 변경 : memberDto = " + memberDto);
		if (memberService.userPwdUpdate(memberDto) > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "로그인", response = Map.class)
	@PostMapping(value = "/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);

			if (loginUser != null) {
//				jwt.io에서 확인
//				로그인 성공했다면 토큰을 생성한다.
				String token = jwtService.create(loginUser);
				logger.trace("로그인 토큰정보 : {}", token);

//				토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다.
//				response.setHeader("auth-token", token);
				resultMap.put("auth-token", token);
				resultMap.put("is-admin", loginUser.getIsadmin());
				resultMap.put("user-id", loginUser.getUserid());
				resultMap.put("user-name", loginUser.getUsername());
				resultMap.put("status",SUCCESS);
//				resultMap.put("status", true);
//				resultMap.put("data", loginUser);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("status",FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 회원 검색은 vue로 해결
	@ApiOperation(value = "회원목록(admin)", response = String.class)
	@GetMapping(value = "/userList")
	public ResponseEntity<List<MemberDto>> userList() {
		logger.debug("회원목록 - 호출 ");
		return new ResponseEntity<List<MemberDto>>(memberService.userList(), HttpStatus.OK);
	}

	@ApiOperation(value = "회원정보(마이페이지)", response = Map.class)
	@GetMapping(value = "/userinform")
	public ResponseEntity<Map<String, Object>> userInform(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(">>>>>> " + jwtService.get(request.getHeader("auth-token")));
		try {
			resultMap.putAll(jwtService.get(request.getHeader("auth-token")));
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원정보 수정(마이페이지)", response = String.class)
	@PutMapping(value = "/userinformupdate")
	public ResponseEntity<String> userInformUpdate(@RequestBody MemberDto memberDto) {
		if (memberService.userInformUpdate(memberDto) > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "비밀번호 확인(마이페이지)", response = String.class)
	@PostMapping(value = "/pwdValidate")
	public ResponseEntity<String> pwdValidate(HttpServletRequest request, @RequestBody String insertpwd) {
		String userid = request.getHeader("user-id");
		logger.debug("비밀번호 확인 - 호출 : userid = " + userid + " insertpwd = " + insertpwd);
		String userpwd = memberService.pwdValidate(userid);
		if (insertpwd.equals(userpwd)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "회원탈퇴(마이페이지)", response = String.class)
	@DeleteMapping(value = "/leave")
	public ResponseEntity<String> leave(HttpServletRequest request) {
		String userid = request.getHeader("user-id");
		logger.debug("회원탈퇴 - 호출 : userid" + userid);
		if (memberService.userLeave(userid) > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	@ApiOperation(value = "관심지역리스트(마이페이지)", response = String.class)
	@GetMapping(value = "/interestloc")
	private ResponseEntity<List<String>> interestloc(HttpServletRequest request) {
		String userid = request.getHeader("user-id");
		logger.debug("관심지역리스트 - 호출 : userid = " + userid);
		return new ResponseEntity<List<String>>(memberService.getInterestLoc(userid), HttpStatus.OK);
	}

	// 관심매물리스트(마이페이지)
	@GetMapping(value = "/interestdeal")
	private String interestdeal() {
		return "member/interestdeal";
	}
}
