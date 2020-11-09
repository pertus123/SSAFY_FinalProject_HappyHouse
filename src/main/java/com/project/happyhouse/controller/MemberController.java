package com.project.happyhouse.controller;

import java.io.PrintWriter;
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

	// 회원가입
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@RequestParam MemberDto memberDto, HttpServletResponse response) throws Exception {
		try {
			int cnt = memberService.join(memberDto);
			if (cnt != 0) {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('회원가입에 성공하였습니다.');</script>");
				out.flush();
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('서버에 문제가 있어 회원가입을 실패했습니다.\\n다시 시도해주세요.');</script>");
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원가입 처리 중 문제가 발생했습니다.');</script>");
			out.flush();
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
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 로그인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제가 발생했습니다.");
			return "error/error";
		}
		return "index"; // 로그인 실패시 어떻게 처리할지, 리턴을 바로 보낼지 아니면 index를 바꿀지 상의
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	// 회원 목록 (Admin)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "user/list";
	}
	// 아이디로 회원 검색 (Admin)
	// 이름으로 회원 검색 (Admin)
	// 회원정보(마이페이지)
	// 회원정보 수정(마이페이지)
	// 비밀번호 확인(마이페이지)
	// 비밀번호 수정(마이페이지)
	// 회원탈퇴(마이페이지)
	// 관심지역리스트(마이페이지)

}
