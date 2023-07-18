package com.bbou.domain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bbou.domain.dto.ReplyDto;

@Mapper
public interface ReplyMapper {
	
	
	
	int countByBoard_no(long no);
	
	List<ReplyDto> findAllByBoard_no(
			@Param("boardNo")long boardNo,
			@Param("limit") int limit, 
			@Param("offset") int offset);

	void save(ReplyDto dto);

}
