package com.bbou.service;

import org.springframework.ui.Model;

import com.bbou.domain.dto.admin.CategorySaveDTO;

public interface CategoryService {

	void listProcess(Model model);

	void listProcess(Long parentNo, Model model);

	void saveProcess(CategorySaveDTO dto);

}
