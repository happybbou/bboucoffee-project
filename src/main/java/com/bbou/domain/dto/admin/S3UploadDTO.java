package com.bbou.domain.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class S3UploadDTO {
	
	private String tempKey;
	private String bucketKey;
	private String orgName;
	private String newName;
	private boolean def;
	private long gno; //fk 상품pk 값 
	
	public S3UploadDTO gno(long no) { //fk값 넣기
		gno=no;
		return this;
	}
	
	public S3UploadDTO bucketKey(String bucketKey) { //fk값 넣기
		this.bucketKey=bucketKey;
		return this;
	}
	
}
