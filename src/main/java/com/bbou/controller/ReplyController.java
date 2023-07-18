package com.bbou.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbou.domain.dto.ReplyDto;
import com.bbou.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReplyController {
	
	private final ReplyService service ;
	
	@ResponseBody
	@PostMapping("/board/{boardNo}/replies")
	public ResponseEntity<Boolean> save(ReplyDto dto,Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		dto.setWriter(userDetails.getUsername());
		service.saveProcess(dto);
		System.out.println(">>>>"+dto);
		return ResponseEntity.ok().body(true); //ok()  >>200코드일때
		//return "redirect:/boards/}"+bno;
	}
	
	
	@GetMapping("/board/{boardNo}/replies")
	public String list(
			@PathVariable("boardNo") long no,
			@RequestParam(defaultValue = "1") int page,
			Model model) {
		service.listProcess(no,page,model);
		return "reply/list";
	}
	
	
}
