package com.bbou.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ReplyDto {

	private long no ; //rno
	private String content;
	private String writer;
	private int readCount;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private long boardNo;
	
}
