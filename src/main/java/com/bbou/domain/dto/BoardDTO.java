package com.bbou.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
	
	private long no; //자동구성
	private String title;//form
	private String content;//form
	private String writer;//form
	private int readCount;//default 0
	private LocalDateTime createdDate; //자동구성
	private LocalDateTime updatedDate; //자동구성
	
	//고객게시판 업데이트를 위한 메서드
	public BoardDTO updateTitleAndContent(BoardDTO dto) {
		title=dto.getTitle();
		content=dto.getContent();
		return this;
	}

}
