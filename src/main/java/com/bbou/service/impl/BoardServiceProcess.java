package com.bbou.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.bbou.domain.dao.BoardMapper;
import com.bbou.domain.dto.BoardDTO;
import com.bbou.domain.dto.BoardSearchDTO;
import com.bbou.service.BoardService;
import com.bbou.util.PageData;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceProcess implements BoardService{
		
	private final BoardMapper mapper;

	@Override
	public String boardSaveProcess(BoardDTO dto) {
		mapper.save(dto);
		return "redirect:/board/list";
	}

	@Override
	public void list(int page, Model model) {
		if(page<1) page=1;
		
		int limit = 5; //한 화면에 보이는 개수
		int offset = (page-1)*limit; // 0:1p , 5:2p 
		
		List<BoardDTO> boardlist=mapper.list(limit,offset);
		model.addAttribute("list", boardlist);
		
		int rowCount = mapper.countAll(); //게시글 총 개수
		model.addAttribute("pd",PageData.create(page, limit, rowCount , 5));
	}

	
	@Override
	public void detail(long no, Model model) {
		BoardDTO result = mapper.findByNo(no);
		model.addAttribute("detail", result);
	}


	@Override
	public String updateProcess(BoardDTO dto) {
		mapper.update(dto);
		return "redirect:/boards/" + dto.getNo();
	}

	@Override
	public String deleteProcess(BoardDTO dto) {
		mapper.delete(dto);
		return "redirect:/boards";
	}

	@Override
	public ModelAndView ListProcess(BoardSearchDTO dto) {
		ModelAndView mv=new ModelAndView("board/list"); 
		int limit = 5;
		RowBounds rowBounds =new RowBounds((dto.getPage()-1)*limit, limit);
		mv.addObject("list", mapper.findAllBySearch(dto ,rowBounds));  //가져갈 데이터수 , dto
		mv.addObject("pd", PageData.create(dto.getPage(), limit, mapper.countAllBySearch(dto), 5));  //페이지데이터
		return mv;
	}
	
}

	
	

