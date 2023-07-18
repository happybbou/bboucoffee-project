package com.bbou.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchDTO {
	
	private int columnName;
	private String query;
	private int page=1;
	
}
