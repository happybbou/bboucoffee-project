package com.bbou.domain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GoodsCategoryMapper {

	@Insert("insert into goods_category(gno, cno) values(#{gno},#{cno})")
	void save(@Param("gno") long goodsNo, @Param("cno") long categoryNo);

	
	@Select("select gno from goods_category where cno=#{categoryNo}")
	List<Long> findAllCno(long categoryNo);

}
