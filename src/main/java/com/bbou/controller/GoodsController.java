package com.bbou.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bbou.domain.dto.admin.GoodsSaveDTO;
import com.bbou.service.FileUploadService;
import com.bbou.service.GoodsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class GoodsController {

	private final GoodsService service ;
	private final FileUploadService fileService;
	
	//비동기요청 url (상품등록 페이지 이동)
	//@Controller @ResponseBody 사용하지 않으면
	//리턴되는 데이터는 html 이 리턴됨. @ResponseBody+ModelAndView
	/*
	@GetMapping("/admin/goods/new")
	public String write() {
		return "admin/goods/write";
	}
	*/
	//카테고리 리스트에서 카테고리 눌렀을때 상품뜨게하기
	@GetMapping("/category/{categoryNo}/goods")
	public String list(@PathVariable long categoryNo, Model model) {
		service.listProcess(categoryNo,model);
		return "goods/list";
	}
	
	
	//인덱스에 상품정보 가져오기
	@GetMapping("/goods")
	public String list(Model model) {
		service.listProcess(model);
		return "goods/list";
	}
	
	//상품정보 디테일 가져오기
	@GetMapping("/goods/{goodsNo}")
	public String detail(@PathVariable long goodsNo, Model model) {
		service.detailProcess(goodsNo, model);
		return "goods/detail";
	}
	
	
	
	@ResponseBody
	@GetMapping("/admin/goods/new")
	public ModelAndView write() {
		return new ModelAndView ("admin/goods/write");
	}
	
	@ResponseBody
	@GetMapping("/admin/goods")
	public ModelAndView list() {
		return new ModelAndView ("admin/goods/list");
	}
	
	
	//파일업로드
	@ResponseBody
	@PostMapping("/admin/goods/temp-img")
	public Map<String,String> tempUpload(MultipartFile temp){
		return fileService.tempUploadProcess(temp);
	}
	
	//상품 저장 + 카테고리 연결 + 이미지
	@ResponseBody
	@PostMapping("/admin/goods")
	public boolean save(GoodsSaveDTO dto) {
		System.out.println(dto);
		service.save(dto);
		return true;
	}
	

}
