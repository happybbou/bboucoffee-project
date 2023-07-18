package com.bbou.service;

import org.springframework.ui.Model;

import com.bbou.domain.dto.ReplyDto;

public interface ReplyService {

	void listProcess(long no, int page, Model model);

	void saveProcess(ReplyDto dto);

}
