package com.bbou.service;

import com.bbou.domain.dto.MemberDTO;

public interface SignService {

	void save(MemberDTO dto);

	int idCheck(String id);



}
