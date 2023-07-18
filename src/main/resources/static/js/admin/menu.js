/**
 * 
 */
$(function(){
	
	$(".menu").click(menuClicked);
	//관리자페이지 메뉴 클릭시 컨트롤러
	$(".menu-wrap  a").click(menuItemClicked);

});
 //메뉴 목록 클릭시 처리시 a 태그
function menuItemClicked(event) {
	event.preventDefault();   /*a 태그 비활성화*/
	/* a href를 비동기요청 */
	$.get(
		$(this).attr("href"), //클릭된 href 값을 가져와서  값을 넘겨줌
		function(resultMain) {
			$("main >.wrap").html(resultMain);
		}
	);  /* (url, success) */
}

 //메뉴클릭 시 세부메뉴 목록 보이기-감추기
 function menuClicked(){
	 var check = $(this).hasClass("open");
	  $(".menu").removeClass("open"); //나머지는 다 닫고
	 if(!check){
		 $(this).addClass("open"); //내클래스만 열기
	 }
	 
 }
 
 //카테고리등록
 function categorySubmitted(){
	 var formData= $("#form-category").serialize();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	$.ajax({
		url:"/admin/category",
		type : "POST",
		data : formData,
		success:function(){
			$("a[href='/admin/category/new']").trigger("click");
			alert("등록완료");
		}
	});
	
 }
 
function categoryList() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	$.ajax({
		url: "/admin/category",
		type: "PATCH",
		success: function(result) {
			$("#category").html(result);
		}
	});
}

 /*2차카테고리가져오기*/
function category2List(selectTag) {
	var parentNo = $(selectTag).val();
	if(parentNo== "")return;//최상위카테고리일때 종료
	//console.log("parentNo : " + parentNo);
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	$.ajax({
		url: `/admin/category/${parentNo}`,
		type: "PATCH",
		success: function(result) {
			
			$(selectTag).next().html(result);

		}
	});
}
 
 