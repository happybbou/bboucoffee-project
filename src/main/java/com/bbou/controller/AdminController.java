package com.bbou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbou.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminController {

	private final CategoryService service;
	
	// 비동기요청
	// 카테고리 등록페이지 이동
	@GetMapping("/admin/category/new")
	public String write() {
		return "admin/category/write";
	}
	
	// 비동기요청
	@ResponseBody
	@PostMapping("/admin/category")
	public void save(com.bbou.domain.dto.admin.CategorySaveDTO dto) {
		service.saveProcess(dto);
	}
	
	//카테고리 리스트
	// @ResponseBody
	@GetMapping("/admin/category")
	public String list(Model model) {
		service.listProcess(model);
		return "admin/category/list";
	}
	
	// 카테고리의 옵션 리스트 (1차 카테고리)
	@PatchMapping("/admin/category")
	public String dataList(Model model) {
		service.listProcess(model);
		return "admin/category/cate-option";
	}
	
	// 카테고리의 옵션 리스트 (2차 카테고리)
	@PatchMapping("/admin/category/{parentNo}")
	public String dataList(@PathVariable Long parentNo, Model model) {
		service.listProcess(parentNo, model);
		return "admin/category/cate-option";
	}
}
