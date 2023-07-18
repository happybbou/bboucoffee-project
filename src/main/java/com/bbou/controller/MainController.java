package com.bbou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	//회원가입 페이지로 이동
	@GetMapping("/signup")
	public String signup() {
		return "sign/signup";
	}
	
	//로그인 페이지로 이동
	@GetMapping("/signin")
	public String signin(){
		return "sign/signin";
	}
	
	//관리자 페이지 이동
	@GetMapping("/admin")
	public String admin() {
		return "admin/admin";
	}
	
	
}
