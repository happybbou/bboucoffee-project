package com.bbou.service;

import org.springframework.ui.Model;

import com.bbou.domain.dto.admin.GoodsSaveDTO;

public interface GoodsService {

	void save(GoodsSaveDTO dto);

	void listProcess(Model model);

	void listProcess(long categoryNo, Model model);

	void detailProcess(long goodsNo, Model model);

}
