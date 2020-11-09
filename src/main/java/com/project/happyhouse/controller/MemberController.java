package com.project.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.happyhouse.model.MemberDto;
import com.project.happyhouse.model.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	// 회원가입 창으로 이동
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "member/join";
	}

	// 회원가입
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(MemberDto memberDto, Model model) throws Exception {
		try {
			int cnt = memberService.join(memberDto);
			if (cnt == 0) {
				model.addAttribute("msg", "서버에 문제가 있어 회원가입을 실패했습니다.\\\\n다시 시도해주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원가입 처리 중 문제가 발생했습니다.");
			return "error/error";
		}
		return "redirect:/";
	}

	// 아이디 인증하기 화면으로 이동
	@RequestMapping(value = "/idValidate", method = RequestMethod.GET)
	public String idValidate() {
		return "member/idValidate";
	}

	// 아이디 인증 후 비밀번호찾기(비밀번호변경)화면으로 이동
	@RequestMapping(value = "/idValidate", method = RequestMethod.POST)
	public String idValidate(String userid, HttpServletResponse response,Model model) throws Exception {
		String userpwd = memberService.pwdValidate(userid);
		System.out.println(userpwd);
		if (userpwd==null) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('변경가능한 아이디가 없습니다.\\n다시 확인해주세요.');location.href='./idValidate'</script>");
			out.flush();
		}
		model.addAttribute("userid",userid);
		return "member/findPassword";
	}

	// 비밀번호찾기(비밀번호변경)
	@RequestMapping(value = "/findPassword", method = RequestMethod.POST)
	public String findPassword(String new_userpwd, String userid, HttpSession session, Model model) {
		System.out.println(new_userpwd);
		System.out.println(userid);
		MemberDto memberDto = new MemberDto();
		memberDto.setUserid(userid);
		memberDto.setUserpwd(new_userpwd);
		try {
			int cnt = memberService.userPwdUpdate(memberDto);
			if (cnt == 0) {
				model.addAttribute("msg", "서버에 문제가 있어 비밀번호 변경을 실패했습니다.\\n다시 시도해주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "비밀번호 변경 처리 중 문제가 발생했습니다.");
			return "error/error";
		}
		return "redirect:/";
	}

	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session,
			HttpServletResponse response) {
		try {
			MemberDto memberDto = memberService.login(map);
			if (memberDto != null) {
				session.setAttribute("userinfo", memberDto);

				Cookie cookie = new Cookie("user_id", memberDto.getUserid());
				cookie.setPath("/");
				if ("saveok".equals(map.get("idsave"))) {
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);// 40년간 저장.
				} else {
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie);

			} else {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('아이디 또는 비밀번호 확인 후 로그인해 주세요.');location.href='../'</script>");
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제가 발생했습니다.");
			return "error/error";
		}
		return "redirect:/";
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	// 회원 목록 (Admin)
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public String userList(Model model) {
		List<MemberDto> members = memberService.userList();
		model.addAttribute("members", members);
		return "member/adminModifyUser";
	}

	// 아이디로 회원 검색 (Admin)
	// 이름으로 회원 검색 (Admin)
	@RequestMapping(value = "/userSearch", method = RequestMethod.GET)
	public String userSearch(String select, String search, Model model) {
		System.out.println(select);
		System.out.println("냥왜실행안되냥");
		System.out.println(search);
		List<MemberDto> members;
		if ("idsearch".equals(select)) {
			members = memberService.userSearchById(search);
		} else if ("namesearch".equals(select)) {
			members = memberService.userSearchByName(search);
		} else {
			members = null;
		}
		model.addAttribute("members", members);
		System.out.println(members);

		return "member/adminModifyUser";
	}

	// 회원정보(마이페이지)
	@RequestMapping(value = "/userInform", method = RequestMethod.GET)
	public String userInform(HttpSession session, Model model) {
		System.out.println("userInform");
		String userid = ((MemberDto) session.getAttribute("userinfo")).getUserid();
		MemberDto memberDto = memberService.getUserInform(userid);
		model.addAttribute("member", memberDto);
		return "member/modifyInform";
	}

	// 회원정보 수정(마이페이지)
	@RequestMapping(value = "/userInform", method = RequestMethod.POST)
	public String userInformUpdate(MemberDto memberDto, HttpSession session, Model model) {
		System.out.println("userInformUpdate");
		memberDto.setUserid(((MemberDto) session.getAttribute("userinfo")).getUserid());
		System.out.println(memberDto);
		try {
			int cnt = memberService.userInformUpdate(memberDto);
			if (cnt == 0) {
				model.addAttribute("msg", "서버에 문제가 있어 정보 수정을 실패했습니다.\n다시 시도해주세요.");
				return "error/error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "정보 수정 처리 중 문제가 발생했습니다.");
			return "error/error";
		}
		return "redirect:/member/userInform";
	}

	// 비밀번호 확인 창이동(마이페이지)
	@RequestMapping(value = "/pwdValidate", method = RequestMethod.GET)
	public String pwdValidateMv() {
		return "member/passwordValidation";
	}

	// 비밀번호 확인(마이페이지)
	@RequestMapping(value = "/pwdValidate", method = RequestMethod.POST)
	public String pwdValidate(String insertpwd, HttpSession session, Model model, HttpServletResponse response) {
		try {
			String userid = ((MemberDto) session.getAttribute("userinfo")).getUserid();
			System.out.println(insertpwd);
			String userpwd = memberService.pwdValidate(userid);
			System.out.println(userpwd);
			if (!insertpwd.equals(userpwd)) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('비밀번호를 틀렸습니다.\\n다시 확인해주세요.');location.href='./pwdValidate'</script>");
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "비밀번호 인증 중 문제가 발생했습니다.");
			return "error/error";
		}
		return "member/modifyPwd";
	}

	// 비밀번호 수정(마이페이지)
	@RequestMapping(value = "/userPwdUpdate", method = RequestMethod.POST)
	public String userPwdUpdate(String new_pwd, HttpSession session, Model model) {
		MemberDto memberDto = new MemberDto();
		memberDto.setUserid(((MemberDto) session.getAttribute("userinfo")).getUserid());
		memberDto.setUserpwd(new_pwd);
		try {
			int cnt = memberService.userPwdUpdate(memberDto);
			if (cnt == 0) {
				model.addAttribute("msg", "서버에 문제가 있어 비밀번호 변경을 실패했습니다.\\n다시 시도해주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "비밀번호 변경 처리 중 문제가 발생했습니다.");
			return "error/error";
		}
		return "redirect:/member/userInform";
	}

	// 회원탈퇴(마이페이지)
	@RequestMapping(value = "/leave", method = RequestMethod.GET)
	public String leave(HttpSession session, Model model) {
		String userid = ((MemberDto) session.getAttribute("userinfo")).getUserid();
		try {
			int cnt = memberService.userLeave(userid);

			if (cnt != 0) {
				session.invalidate();
			} else {
				model.addAttribute("msg", "서버에 문제가 있어 회원 탈퇴에 실패했습니다.\\n다시 시도해주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 탈퇴 처리 중 문제가 발생했습니다.");
			return "error/error";
		}

		return "redirect:/";
	}

	// 관심지역리스트(마이페이지)
	@RequestMapping(value = "/interestloc", method = RequestMethod.GET)
	private String interestloc(HttpSession session, Model model) {
		try {
			String userid = ((MemberDto) session.getAttribute("userinfo")).getUserid();
			System.out.println(userid);
			List<String> interestloc = memberService.getInterestLoc(userid);
			for (int i = 0; i < interestloc.size(); i++) {
				System.out.println(interestloc.get(i));
			}
			model.addAttribute("interestloc", interestloc);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 탈퇴 처리 중 문제가 발생했습니다.");
			return "error/error";
		}
		return "member/interestloc";
	}

	// 관심매물리스트(마이페이지)
	@RequestMapping(value = "/interestdeal", method = RequestMethod.GET)
	private String interestdeal() {
		return "member/interestdeal";
	}
}
