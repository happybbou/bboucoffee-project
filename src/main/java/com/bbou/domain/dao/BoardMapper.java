package com.bbou.domain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.bbou.domain.dto.BoardDTO;
import com.bbou.domain.dto.BoardSearchDTO;

@Mapper
public interface BoardMapper {

	
	//고객게시판 게시글 등록
	void save(BoardDTO dto);

	//고객게시판 리스트조회
	List<BoardDTO> list(@Param("limit") int limit, @Param("offset") int offset);

	//고객게시판 상세조회
	BoardDTO findByNo(long no);

	//고객게시판 수정
	//@Update("update board set title=#{title} , content=#{content} where no=#{no}")
	void update(BoardDTO dto);

	//고객게시판 삭제
	void delete(BoardDTO dto);
	
	//총 게시글 개수
	int countAll();

	//게시글 찾기
	List<BoardDTO> findAllBySearch(BoardSearchDTO dto, RowBounds rowBounds);

	//검색한 후 총 개수
	int countAllBySearch(BoardSearchDTO dto);

}
