package com.bbou.domain.dto;

import com.bbou.security.MyRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RoleSaveDTO {
	
	private long memberNo;
	private MyRole role;
	
}
