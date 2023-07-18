package com.bbou.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.bbou.domain.dto.MemberDTO;

import lombok.Getter;
@Getter
public class MyUserDetails extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String  name;
	private String id;
	
	MyUserDetails(MemberDTO dto) { //추가적으로 name을 가져오기위하여 추가설정 dto 불러와줌

		this(dto.getId(),dto.getPassword(), dto.getRoles().stream()   //enum으로 넣어놓았기때문에 stream으로 변경
				.map(role-> new SimpleGrantedAuthority(role.roleName()))
				.collect(Collectors.toSet()));
		this.name= dto.getName(); 
		this.id= dto.getId();
	}
	
	private MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) { //필수로 들어가야할 항목들
		super(username, password, authorities);  //
	}

}
