package com.bbou.util;

import lombok.Getter;

@Getter
public class PageData {
	
	private int tot ; //페이지총개수
	private int from ; //출력되는 페이지 시작번호
	private int to ; //출력되는 페이지 마지막 번호
	
	private boolean hasNext ; //다음 페이지 값을 가질경우 true
	private int page; //현재 페이지 
	
	/**
	 * 
	 * @param page : 페이지번호
	 * @param limit : 페이지당 게시글 수
	 * @param rowCount : 총페이지 수
	 * @param RANGE : default RANGE=10
	 * @return 페이지 번호 from(출력되는 페이지 시작번호), to(출력되는 페이지 마지막 번호), tot(페이지총개수)
	 */
	public static PageData create(int page,int limit, int rowCount) {
		return new PageData(page, limit, rowCount);
	}
	
	/**
	 * 
	 * @param page : 페이지번호
	 * @param limit : 페이지당 게시글 수
	 * @param rowCount : 총페이지 수
	 * @param RANGE : 페이지번호 개수
	 * @return 10개로 구성된 페이지 번호 from(출력되는 페이지 시작번호), to(출력되는 페이지 마지막 번호), tot(페이지총개수)
	 */
	public static PageData create(int page,int limit, int rowCount , int RANGE) {
		return new PageData(page, limit, rowCount ,RANGE);
	}
	
	
	private PageData(int page,int limit, int rowCount , int RANGE) {
		//int RANGE =10; // 한 페이지에 표현되는 페이지 번호 개수 
		
		//더보기 기능구현을 위해서 추가
		hasNext=rowCount > limit*page?true:false; //다음페이지 존재할경우 true 
		this.page=page;
		
		
		
		this.tot=rowCount/limit;
		if(rowCount%limit >0) this.tot++; //나머지가 존재하면 1증가
		
		int rangeNo=page/RANGE; //1~9 : 0  , 10/10 :1
		if(page%RANGE>0)rangeNo++; //1~10 : 1
		
		this.to =RANGE*rangeNo; //페이지 마지막 번호
		this.from=this.to-RANGE +1; //페이지 시작 번호
		
		if(this.to > this.tot) this.to = this.tot; //페이지 마지막 번호가 총 페이지번호보다 클경우 총페이지번호로 표시
	}
	
	
	private PageData(int page,int limit, int rowCount){
		this( page, limit,  rowCount , 10);
			
	}

}
