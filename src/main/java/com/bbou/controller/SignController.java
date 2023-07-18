package com.bbou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbou.domain.dto.MemberDTO;
import com.bbou.service.SignService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SignController {
	
	private final SignService service;
	
	
	@PostMapping("/signup")
	public String signup(MemberDTO dto){
		service.save(dto);
		return "/index";
	}
	//아이디 중복체크
	@PostMapping("/common/idCheck")
	@ResponseBody
	public int idCheck(@RequestParam("id") String id) {
		int cnt = service.idCheck(id);
		System.out.println(cnt);
		return cnt;

	}
	
	
}
