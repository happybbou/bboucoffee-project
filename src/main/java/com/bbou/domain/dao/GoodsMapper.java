package com.bbou.domain.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.bbou.domain.dto.admin.GoodsDetailDTO;
import com.bbou.domain.dto.admin.GoodsImgListDTO;
import com.bbou.domain.dto.admin.GoodsListDTO;
import com.bbou.domain.dto.admin.GoodsSaveDTO;
import com.bbou.domain.dto.admin.S3UploadDTO;

@Mapper
public interface GoodsMapper {

	void save(GoodsSaveDTO dto);

	void saveImage(S3UploadDTO img);

	List<GoodsListDTO> findAll();

	List<GoodsListDTO> findByCategoryNo(long categoryNo);

	Optional<GoodsDetailDTO> detail(long goodsNo);

	List<GoodsImgListDTO> goodsImagesForByDetailNo(long goodsNo);

}
