package com.bbou.domain.dto.admin;

import lombok.Data;


@Data
public class GoodsListDTO {
	//이미지
	private String bucketKey;  //프로퍼티즈에 도메인주소 , DB에 도메인뒤 이미지 주소존재
	
	private String defImg;
	
	//기본이미지 세팅을 위한 편의메서드
	public GoodsListDTO defImg(String domain) {
		defImg=domain+bucketKey;
		return this;
	}
	
	private long no;
	private String title;
	private int price;
	private int stock;
	
	
}
