package com.bbou.service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.bbou.domain.dto.BoardDTO;
import com.bbou.domain.dto.BoardSearchDTO;

public interface BoardService {
	
	//고객센터 게시글 등록
	String boardSaveProcess(BoardDTO dto);

	//고객센터 리스트 조회
	void list(int page, Model model);

	//고객센터 상세페이지
	void detail(long no, Model model);

	//고객센터 상세페이지 수정
	String updateProcess(BoardDTO dto);

	//고객센터 상세페이지 삭제
	String deleteProcess(BoardDTO dto);

	//게시글 리스트
	ModelAndView ListProcess(BoardSearchDTO dto);

}
