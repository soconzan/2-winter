package com.example.membership.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.membership.domain.Admin;
import com.example.membership.domain.Member;
import com.example.membership.service.AdminService;
import com.example.membership.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebController {
	
	private final AdminService adminService;
	private final MemberService memberService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/join")
	public String join(
			@RequestParam("adminId") String adminId,
			@RequestParam("pw") String pw,
			RedirectAttributes ra) {
		
		Admin admin = new Admin();
		admin.setAdminId(adminId);
		admin.setPw(pw);
		adminService.join(admin);
		
		ra.addAttribute("message", "관리자 회원가입됨");
		
		return "redirect:/members";
	}
	
//	@PostMapping("/signin")
//	public String signin(
//			@RequestParam(name)) {
//		
//	}
	
	@GetMapping("/members")
	public String members(Model model) {
		model.addAttribute("members", memberService.getMembers());
		return "members";
	}
	
	@PostMapping("/add")
	public String add(
			@RequestParam("phone") String phone,
			RedirectAttributes ra) {
		
		Member member = new Member();
		member.setPhone(phone);
		memberService.join(member);
		
		ra.addAttribute("message", "회원 추가됨");
		
		return "redirect:/members";
	}
}
