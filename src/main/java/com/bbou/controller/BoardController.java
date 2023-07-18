package com.bbou.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bbou.domain.dto.BoardDTO;
import com.bbou.domain.dto.BoardSearchDTO;
import com.bbou.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService service;
	
	//게시판 페이지 이동
	@GetMapping("/boards")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {
		service.list(page,model);
		return "board/list";
	}

	// 고객게시판 글쓰기페이지로 이동
	@GetMapping("/boards/write")
	public String write() {
		return "board/write";
	}
	
	//고객게시판 게시글 자세히조회
	@GetMapping("/boards/{no}")
	public String detail (@PathVariable long no , Model model) {
		service.detail(no,model);
		return "board/detail";
	}
	
	//고객게시판 게시글등록
	@PostMapping("/boards")
	public String write(BoardDTO dto, Authentication authentication) {
		//현재 로그인 되어있는 이메일정보 불러오기
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		dto.setWriter(userDetails.getUsername());
		
		service.boardSaveProcess(dto);
		return "redirect:/boards";
	}
	
	//고객게시판 게시글 수정
	@PutMapping("/boards/{no}")
	public String update(BoardDTO dto) {
		return service.updateProcess(dto);
	}
	
	//게시글 삭제
	@DeleteMapping("/boards/{no}")
	public String delete(BoardDTO dto) {
		return service.deleteProcess(dto);
	}
	
	//검색
	@PatchMapping("/rest-boards/search")
	public ModelAndView SearchList(BoardSearchDTO dto) {
		return service.ListProcess(dto);
	}
	
}
