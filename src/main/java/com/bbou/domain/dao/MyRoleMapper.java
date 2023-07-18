package com.bbou.domain.dao;

import org.apache.ibatis.annotations.Mapper;

import com.bbou.domain.dto.RoleDTO;
import com.bbou.domain.dto.RoleSaveDTO;

@Mapper
public interface MyRoleMapper {

	void save(RoleSaveDTO roleDto);

}
