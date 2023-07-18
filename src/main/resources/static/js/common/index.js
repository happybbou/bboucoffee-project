/**
 * 
 */

 $(function(){
	goodsListAll(); 
	
	/*$("#gnb .gnb-icon").hover(gnbHover,gnbHoverOut); *///메뉴아이콘에 호버주기
	
 });
 
 
 /*
 function gnbHoverOut(){
	$("#gnb-list").stop().slideUp(); //stop() 옵션 마우스 hover 가 풀렸을때 이벤트 stop
}

 function cateHover(liTag){//1차카테고리 호버시
	 
	 var parentNo= $(liTag).attr("value") //현재 호버한 카테고리
	 var check = $(liTag).find("ul").hasClass("cate-wrap");
	 if(check){$(liTag).children(".sub").stop().slideDown();return;};
	 $.ajax({
		 url : `/category/${parentNo}`,
		 success:function(result){
			$(liTag).children(".sub").html(result);
			$(liTag).children(".sub").stop().slideDown()
		 }
	 })
 }
 
 function cateHoverOut(liTag){
	 $(liTag).children(".sub").stop().slideUp();
 }
 
 function gnbHover(){ //아이콘 호버시
	 var check = $("#gnb-list ul").hasClass("cate-wrap");
	 console.log("ul : "+check);
	 if(check){
		 $("#gnb-list").stop().slideDown();return;
	 }; //리스트 한번만 가져오고 그뒤로 가져오지 않게함
	 
	 $.ajax({
		 url : "/category/0",
		 success:function(result){
			 $("#gnb-list").html(result);
			 $("#gnb-list").stop().slideDown();
		 }
	 })
 }
 */
 //상품정보 index 불러오기 
 function goodsListAll(){
	 $.ajax({
		 url:"/goods",
		 success: function(result){
			 $("#goods>.wrap").html(result);
		 }
	 });
	 
 }
 

 //
 function goodsListOfCategoryNo(aTag){
	
	$.ajax({
		url:$(aTag).attr("href"),
		success:function(result){
			$("#goods>.wrap").html(result);
		}
		
	});
 }
 
 
 
 
 
 