package com.bbou.domain.dto;

import java.util.Set;

import com.bbou.domain.dao.MemberMapper;
import com.bbou.security.MyRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class MemberDTO {

	private long no;
	private String id;
	private String password;
	private String name;
	private Set<MyRole> roles;
	
	public MemberDTO passEncode(String encodedPass) {
		password=encodedPass;
		return this;
	}



	
	
}
