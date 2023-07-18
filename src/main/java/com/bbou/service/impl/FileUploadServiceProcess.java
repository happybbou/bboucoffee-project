package com.bbou.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.bbou.domain.dto.admin.S3UploadDTO;
import com.bbou.service.FileUploadService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class FileUploadServiceProcess implements FileUploadService {

	private final AmazonS3Client client ;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	
	@Value("${cloud.aws.s3.temp-path}")
	private String tempPath;
	
	@Value("${cloud.aws.s3.upload-path}")
	private String uploadPath;
	
	@Override
	public Map<String, String> tempUploadProcess(MultipartFile temp) {
		Map<String, String> resultMap = new HashMap<>();
		String newName =createNewFileName(temp.getOriginalFilename());
		String tempKey= tempPath+newName;
		try(InputStream is =temp.getInputStream()) {
			PutObjectRequest putObjectRequest =new PutObjectRequest(bucketName, tempKey, is, objectMetadata(temp));//버킷에 저장
			client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead)); //읽기권한 부여 
			
			log.info(">>>>>>[tempKey]" + tempKey);
			String imgUrl = client.getUrl(bucketName, tempKey).toString().substring(6); //이미지경로 https://  >>https제거
			log.info(">>>>>>[imgUrl]" + imgUrl);
			
			resultMap.put("imgUrl", imgUrl);
			resultMap.put("orgName", temp.getOriginalFilename()); //org Name
			resultMap.put("newName", newName);
			resultMap.put("tempKey", tempKey); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	private ObjectMetadata objectMetadata(MultipartFile temp) { //파일 설정정보 
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(temp.getContentType());
		objectMetadata.setContentLength(temp.getSize());
		return objectMetadata;
	}

	//오리지날 이름이 넘어오면 새로운 이름으로 변경
	private String createNewFileName(String orgName) {
		int idx = orgName.lastIndexOf(".");
		return UUID.randomUUID().toString()
				+orgName.substring(idx); //확장자만 가져오기
	}

	@Override
	public S3UploadDTO tempToUpload(S3UploadDTO img) {
		
		String bucketkey=uploadPath+img.getNewName();
		System.out.println(">>>>key:"+img.getTempKey());
		CopyObjectRequest copyObjectRequest=new CopyObjectRequest(bucketName, img.getTempKey(), bucketName, bucketkey);
		client.copyObject(copyObjectRequest.withCannedAccessControlList(CannedAccessControlList.PublicRead));
		client.deleteObject(bucketName, img.getTempKey());
		return img.bucketKey(bucketkey);
	}


	@Override
	public void clearTemp() {
		//템프 경로의 목록을 갖고와서 제거
		// 폴더 내의 모든 객체를 삭제
		ObjectListing objectListing = client.listObjects(bucketName, tempPath);
		while (true) {
		    for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
		    	client.deleteObject(bucketName, objectSummary.getKey());
		    }

		    if (objectListing.isTruncated()) {
		        objectListing = client.listNextBatchOfObjects(objectListing);
		    } else {
		        break;
		    }
		}
		
		// 폴더를 삭제
		client.deleteObject(bucketName, tempPath);

	}

}
