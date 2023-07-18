package com.bbou.domain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bbou.domain.dto.admin.CategoryListDTO;

@Mapper
public interface CategoryMapper {

	@Select("select * from category"
			+ " where parent_no is null"
			+ " order by name")
	List<CategoryListDTO> findAllByParentNoIsNull();

	List<CategoryListDTO> findAllByParentNo(Long parentNo);
	
	@Insert("insert into category(name, parent_no)" +
			  " values(#{name},#{parentNo})" )
	void save(@Param("name") String name, @Param("parentNo") Long parentNo);

}
