package com.bbou.domain.dto.admin;

import lombok.Data;

@Data
public class GoodsDetailDTO {

	private long no;
	private String title;
	private String content;
	private int price;
	private int stock;
}
