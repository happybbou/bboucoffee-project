/**
 * 
 */

function goodsSummited(){
	
	var data= $("#form-goods").serialize();
	//console.log(data);
	$.ajax({
		url:"/admin/goods",
		type:"post",
		data : data,
		success : function(result){
			console.log(result);
			$("a[href='/admin/goods/new']").trigger("click");
			alert("등록완료");
			
		}
	});
	//console.log(data);
	
}


function tempUpload(fileEl){
	console.log($(fileEl));
	var fileData =  $(fileEl)[0].files[0];  //index 번호 0번 지정하여야 속성 가져오기가능 , files 의 index 0번 파일정보
	var formData = new FormData(); 
	formData.append("temp", fileData); //form 의 name 을 결정
	
	//security 사용시 csrf 토큰 사용하여야함
	
	$.ajax({
		url : "/admin/goods/temp-img",
		type : "POST",
		contentType : false,
		processData : false,
		data : formData,
		success : function(resultMap){
			//console.log(resultMap.imgUrl);
			//console.log(resultMap.orgName);
			//console.log(resultMap.tempKey);
			$(fileEl).parent().css("background-image" , `url(${resultMap.imgUrl})`);  /* goods의 css 백그라운드 이미지 불러오기 */
			$(fileEl).parents(".img-wrap").find(".orgName").val(resultMap.orgName);
			$(fileEl).parents(".img-wrap").find(".newName").val(resultMap.newName);
			$(fileEl).parents(".img-wrap").find(".tempKey").val(resultMap.tempKey);
			
			var def= $(fileEl).parents(".img-wrap").find(".def").val(); //def 이미지 : true 나옴
			if(def=="true")return;	 //대표이미지면 함수종료
			
			//추가이미지 일때 태그 추가
			var addLength = $(".add-img> .img-wrap").length;
			console.log(">>>>>>>>>>추가이미지 개수" + addLength);
			var targetIdx=$(fileEl).parents(".img-wrap").index()+1;
			console.log(">>>>>>>>>>수정하는 이미지위치 " + targetIdx);
			//추가하면 안되는 경우
			//1.태그가 5개가 완료된 경우
			//2. 5개 만들기 전 이전 이미지 수정하면
			if(addLength >=5 || targetIdx < addLength) return; //5개가 꽉채워지면 추가 X , 만들어진 태그보다 앞의것을 건드렸을경우 추가 X
			
			
			var appendTag=`
			<div class="img-wrap">
				<label class="pre-img"><input type="file" onchange="tempUpload(this)"></label>
				<input type="hidden" class="tempKey" name="tempKey">
				<input type="hidden" class="orgName" name="orgName">
				<input type="hidden" class="newName" name="newName">
				<input type="hidden" class="def" name="def" value="false">
			</div>
			`;
			$(".add-img").append(appendTag); //def 이미지가 아닐때만 tag 추가
			console.log("태그추가됨!")
		}
	});
}