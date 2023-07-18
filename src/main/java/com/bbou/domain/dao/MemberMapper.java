package com.bbou.domain.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.bbou.domain.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	void save(MemberDTO dto);

	Optional<MemberDTO> findById(String id);
	int idCheck(String id);

	
}
