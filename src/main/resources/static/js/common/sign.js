let checkID = /^[a-z0-9]{6,20}$/;
let checkPW = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[~!@#$%^&*()_+])[A-Za-z\d~!@#$%^&*()_+]{8,}$/;
let checkName = /^[가-힣]|[A-Z]|[a-z]$/;

$(function(){
	$("#id").blur(function () {
		if ($("#id").val() == "") {
			$(".error-message-id").text("아이디를 입력해주세요.").css("color", "red");
			//$("#id").focus();
			return false;
		} else if (!checkID.test($("#id").val())) {
			$(".error-message-id").text("영문과 숫자를 조합하여 최소 6~20자리로 입력해주세요.").css("color", "red");
			//$("#id").focus();
			return false;
		} else if (checkID.test($("#id").val())) {
			$(".error-message-id").text("중복체크후 이용하세요").css("color", "green");
			//$("#userPW").focus();
			return true;
		}
	});
	
	$("#userPW").blur(function () {
		if ($("#userPW").val() == "") {
			$(".error-message.password").text("비밀번호를 입력해주세요.").css("color", "red");
			//$("#userPW").focus();
			return false;
		} else if (!checkPW.test($("#userPW").val())) {
			$(".error-message.password").text("대문자를 포함하여 8자리이상 입력하세요.(특수문자포함)").css("color", "red");
			return false;
		} else if (checkPW.test($("#userPW").val())) {
			$(".error-message.password").text("사용가능한 비밀번호입니다.").css("color", "green");
			return true;
		}
	});
	
	$("#userPW_confirm").blur(function () {
		if ($("#userPW").val() != $("#userPW_confirm").val()) {
			$(".error-message.password_confirm").text("비밀번호와 일치하게 입력해주세요.").css("color", "red");
			return false;
		} else if ($("#userPW").val() == $("#userPW_confirm").val()) {
			$(".error-message.password_confirm").text("비밀번호 일치합니다.").css("color", "green");
			return true;
		}
	});
	
	$("#userName").blur(function () {
		if ($("#userName").val() == "") {
			$(".error-message.name").text("이름을 입력해주세요.").css("color", "red");
			return false;
		} else if (!checkName.test($("#userName").val())) {
			$(".error-message.name").text("한글 또는 영문으로 입력해주세요.").css("color", "red");
			//$("#userName").focus();
			return false;
		} else if (checkName.test($("#userName").val())) {
			$(".error-message.name").text("사용가능한 이름입니다.").css("color", "green");
			return true;
		}
	});


	$('.btn-st').click(function () {
		var nameValue = $("#userName").val();
		if (
			$("#id").val() == "" ||
			$("#userPW").val() == "" ||
			$("#userName").val() == "" 
			//$("#userPhone").val() == "" ||
			//$("#userEmail").val() == ""
		) {
			alert("공백을 입력하세요.");
			return false;
		} else if ($("#userPW").val() != $("#userPW_confirm").val()) {
			alert("비밀번호가 서로 일치하지 않습니다.");
			return false;
		} else if (
			!checkID.test($("#id").val()) ||
			!checkPW.test($("#userPW").val()) ||
			!checkName.test($("#userName").val()) 
			//!checkPhone.test($("#userPhone").val()) ||
			//!checkEmail.test($("#userEmail").val())
		) {
			alert("형식에 맞춰 작성해주세요.");
			return false;
		} else {
			alert(nameValue + "회원가입을 환영합니다.");
			return true;
		}
	});
});


function checkId(){
	var token = $("meta[name='_csrf']").attr('content');
	var header = $("meta[name='_csrf_header']").attr('content');
	if (token && header) {
		$(document).ajaxSend(function (event, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	}

    var id = $('#id').val().trim(); //id값이 "id"인 입력란의 값을 저장
    $.ajax({
        url:'/common/idCheck', //Controller에서 요청 받을 주소
        type:'post', //POST 방식으로 전달
        data:{id:id},
        success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
            if(cnt == 1){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
               $(".error-message-id").text("이미존재하거나 탈퇴한 회원입니다.").css("color","red");
              
            } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
                $(".error-message-id").text("사용가능한 아이디!!!").css("color","green");
              
                /*alert("아이디를 다시 입력해주세요");
                $('#id').val('');*/
            }
        },
       /* error:function(){
            alert("에러입니다");
        }*/
    });
};









