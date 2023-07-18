package com.bbou.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bbou.domain.dao.MemberMapper;
import com.bbou.domain.dao.MyRoleMapper;
import com.bbou.domain.dto.MemberDTO;
import com.bbou.domain.dto.RoleDTO;
import com.bbou.domain.dto.RoleSaveDTO;
import com.bbou.security.MyRole;
import com.bbou.service.SignService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SignServiceProcess implements SignService{
	
	private final MemberMapper mapper;
	private final MyRoleMapper rolemapper;
	private final PasswordEncoder encoder;
	
	@Override
	public void save(MemberDTO dto) {
		//MemberDTO entity = dto.toMemberDTO();
		
		mapper.save(dto.passEncode(encoder.encode(dto.getPassword())));
		long no=dto.getNo();
		System.out.println("no:"+no);
		//entity 에서 role , no 저장
		RoleSaveDTO roleDto = RoleSaveDTO.builder()
				.memberNo(dto.getNo())
				.role(MyRole.USER)
				.build();
		
		rolemapper.save(roleDto);
		
	}

	@Override
	public int idCheck(String id) {
		int cnt = mapper.idCheck(id);
		System.out.println("cnt: " + cnt);
		return cnt;
	}
 
	
}
