/**
 * 
 */
function goodsDetail(goodsNo){
	$.ajax({
		url:`/goods/${goodsNo}`,
		success: function(result){
			$("main>.wrap").html(result)
		}
	});
}

function changeDef(liTag){
	$(".img-area .img").removeClass("def");
	$(liTag).addClass("def")
}

function pay(btnTag){
	var amount =$(btnTag).siblings(".amount").val();
	var name= $(btnTag).siblings(".name").val();
	var IMP = window.IMP; // 생략 가능
	IMP.init("imp18604158");
	
	IMP.request_pay({
		pg: "kcp",
		pay_method: "card",
		merchant_uid: "ORD" + new Date().getTime(),
		name: name,
		amount: amount,
		buyer_email: 'Iamport@chai.finance',
		buyer_name: '포트원 기술지원팀',
		buyer_tel: '010-1234-5678',
		buyer_addr: '서울특별시 강남구 삼성동',
		buyer_postcode: '123-456'
	}, function(rsp) { // callback
		//rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.
		var msg="";
		if(rsp.success){
			msg="결제완료"
		}else{
			msg="결제실패"
		}
		alert(msg);
	});
  
}