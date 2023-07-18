package com.bbou.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bbou.domain.dao.CategoryMapper;
import com.bbou.domain.dto.admin.CategoryListDTO;
import com.bbou.domain.dto.admin.CategorySaveDTO;
import com.bbou.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceProcess implements CategoryService{
	
	private final CategoryMapper mapper;
	
	//최상위 카테고리
	@Override
	public void listProcess(Model model) {
		List<CategoryListDTO> result =mapper.findAllByParentNoIsNull();
		model.addAttribute("list", result);
	}
	
	//부모 카테고리가 있는 카테고리
	@Override
	public void listProcess(Long parentNo, Model model) {
		model.addAttribute("parent", parentNo);
		List<CategoryListDTO> result =mapper.findAllByParentNo(parentNo);
		model.addAttribute("list", result);
	}

	@Override
	public void saveProcess(CategorySaveDTO dto) {
		Long parentNo=null;
		for(Long pno : dto.getParentNo()) {
			parentNo=pno;
		}
		
		mapper.save(dto.getName(), parentNo);
		
	}


}
