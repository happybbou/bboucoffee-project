package com.bbou.domain.dto.admin;

import lombok.Data;

@Data
public class CategorySaveDTO {

	private Long[] parentNo;  //실제로 존재 X     Long default null
	private String name; 
}
