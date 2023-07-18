package com.bbou.domain.dto.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class GoodsSaveDTO {
	
	private long no;
	private String title;
	private int price;
	private boolean onSale;
	private int vat;
	private int stock;
	private String content;
	
	//카테고리 수집
	private long[] parentNo;
	
	//상품등록할 카테고리번호 찾는 메서드
	public long categoryNo() {
		Arrays.sort(parentNo); //정렬
		return parentNo[parentNo.length-1]; //카테고리 마지막 값 취함
	}
	
	private String[] tempKey;
	private String[] orgName;
	private String[] newName;
	private boolean[] def;
	
	
	public List<S3UploadDTO> files(){
		List<S3UploadDTO> list=new ArrayList<>();
		for(int i=0; i<tempKey.length ;i++) {
			if(tempKey[i]!=null && tempKey[i]!="") {
				list.add(S3UploadDTO.builder()
						.tempKey(tempKey[i])
						.orgName(orgName[i])
						.newName(newName[i])
						.def(def[i])
						.build());
			}
		}
		
		return list;
	}
}
