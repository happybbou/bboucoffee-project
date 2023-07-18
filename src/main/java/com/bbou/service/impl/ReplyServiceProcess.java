package com.bbou.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bbou.domain.dao.ReplyMapper;
import com.bbou.domain.dto.ReplyDto;
import com.bbou.service.ReplyService;
import com.bbou.util.PageData;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyServiceProcess implements ReplyService{

	private final ReplyMapper mapper;
	
	@Override
	public void listProcess(long no, int page, Model model) {
		
		int limit=5;
		int offset=(page-1)*limit; // page= 1 이면 offset 은 0
		model.addAttribute("list", mapper.findAllByBoard_no(no,limit,offset));
		int rowTotal= mapper.countByBoard_no(no); //총 개수를 얻음
		System.out.println(">>>>>>"+rowTotal);
		
		//댓글 더 가져올것이 있으면 true , 없으면 false  // 총페이지수가 현재 페이지에 보이는 수보다 크면 true
		//model.addAttribute("hasNext", rowTotal> page*limit? true:false); 
		//model.addAttribute("nextNo", page+1); //다음페이지값
		
		model.addAttribute("rp",PageData.create(page, limit, rowTotal));
	}

	@Override
	public void saveProcess(ReplyDto dto) {
		mapper.save(dto);
	}

	
}
