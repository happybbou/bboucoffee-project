package com.bbou.domain.dto.admin;

import lombok.Data;

@Data
public class GoodsImgListDTO {
	
	private String bucketKey;
	private boolean def;
	
	private String url;
	
	//url 메서드 만듦.
	public GoodsImgListDTO url(String domain) {
		this.url=domain+bucketKey;
		return this;
	}
}
