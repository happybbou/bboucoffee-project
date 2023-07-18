package com.bbou.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bbou.domain.dao.CategoryMapper;
import com.bbou.domain.dao.GoodsCategoryMapper;
import com.bbou.domain.dao.GoodsMapper;
import com.bbou.domain.dto.admin.GoodsDetailDTO;
import com.bbou.domain.dto.admin.GoodsImgListDTO;
import com.bbou.domain.dto.admin.GoodsListDTO;
import com.bbou.domain.dto.admin.GoodsSaveDTO;
import com.bbou.domain.dto.admin.S3UploadDTO;
import com.bbou.service.FileUploadService;
import com.bbou.service.GoodsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GoodsServiceProcess implements GoodsService {
	
	private final GoodsMapper goodsMapper; 
	private final GoodsCategoryMapper gcMapper;
	private final CategoryMapper categoryMapper;
	private final FileUploadService fileService;
	
	
	
	@Value("${cloud.aws.s3.domain}")
	private String domain;
	
	@Override
	public void save(GoodsSaveDTO dto) {
		
		//1.상품정보 저장
		goodsMapper.save(dto); //dto.no 값 상품정보 저장 후 세팅됨
		
		//2.상품_카테고리 저장 : 상품번호(dto.no), 카테고리번호(dto.categoryNo())
		gcMapper.save(dto.getNo(), dto.categoryNo());
		//3.파일 모두 temp->upload 이동 (이미지)
		dto.files().forEach(img->{
			//temp->src
			System.out.println(img);
			S3UploadDTO uploadResult=fileService.tempToUpload(img);
			
			//fk update 하고 저장
			goodsMapper.saveImage(uploadResult.gno(dto.getNo()));
		});
		
		fileService.clearTemp();
		
	}
	
	//상품+def img
	@Override
	public void listProcess(Model model) {
		List<GoodsListDTO> result = goodsMapper.findAll().stream()
				.map(dto->dto.defImg(domain))
				.collect(Collectors.toList());
		
		model.addAttribute("list", result);
		
		result.forEach(dto->{
			System.out.println(dto);
		});
	}
	
	
	@Override
	public void listProcess(long categoryNo, Model model) {
		//categoryMapper.findAllByParentNo(categoryNo);
		
		//현재카테고리-하위카테고리 상품조회
		// goodsMapper.findByCategoryNo(categoryNo);
		List<GoodsListDTO> result=goodsMapper.findByCategoryNo(categoryNo).stream()
				.map(dto->dto.defImg(domain))
				.collect(Collectors.toList());
		
		model.addAttribute("list", result);
		
		result.forEach(dto->{
			System.out.println(dto);
		});
		
	}

	@Override
	public void detailProcess(long goodsNo, Model model) {
		
		//1.상세정보()
		GoodsDetailDTO detail=goodsMapper.detail(goodsNo).orElseThrow();
		model.addAttribute("detail", detail);
		
		//2.이미지 정보가져오기
		List<GoodsImgListDTO> imgs=goodsMapper.goodsImagesForByDetailNo(goodsNo).stream()
				.map(dto->dto.url(domain)) //imgurl 완성 domain+bucketKey
				.collect(Collectors.toList());
		model.addAttribute("imgs", imgs);
		
	}
	
}
