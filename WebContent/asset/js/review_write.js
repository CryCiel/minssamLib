/**
 * 
 */
$(document).ready(function(){
	init();
});
function init(){
	//editor 사용
	CKEDITOR.replace('content');
	$($("input[type='button']")[2]).click(function(){
		send();
	});
	
	//리뷰어가 책에 대해 매긴 평점 카운트
	$($(".starImg").find("img")).click(function(event){
		var i=$(this).index();
		//var clicked=false;
		scoring(i);
	});
	
	//업로드 대기 이미지 등록
	$("input[type=file]").click(function(){
		makeThumbnail();
	});
	
	//업로드 대기 이미지 선택 체크박스 관련
	$("#checkAll").click(function(){
		chkAll();
	});

}


//책의 평점 계산
function scoring(i){
	//몇 번째 별을 클릭했는지 찾기
	console.log(i+"번째 녀석을 클릭");
	//i번째 녀석까지 별을 채워진 별로 반전
	for(var a=0;a<=4;a++){
		$($(".starImg").find("img")[a]).attr('src','/asset/images/star_empty.png');
	}	
	for(var a=0;a<=i;a++){
		$($(".starImg").find("img")[a]).attr('src','/asset/images/star_filled.png');
	}	
	//별 갯수만큼 스코어 부여
	var score = 0;
	score=i+1;
	console.log("리뷰어가 이 책에 매긴 점수 : "+score+"점");
	$(".reviewScore").text(score);
	$("input[name='score.score']").val(score);
	console.log("hidden 타입 score에 담은 스코어밸류"+$("input[name='score']").val());
}

//업로드 대기 이미지 동적 생성
function makeThumbnail(){
	var str="";
	str+="<li class='imgListUnit'>";
	str+="<input type='checkbox' class='chk'/>";
	str+="<img src='' alt='선택한 업로드 이미지'/>";
	str+="</li>";
	$(".selectedImgList").append(str);
}

//업로드 대기 이미지 체크박스 전체선택/전체해제
function chkAll(){
	if($("#checkAll").prop("checked")){
		$(".chk").prop("checked",true);
	}else{
		$(".chk").prop("checked",false);
	}
}

//체크박스 선택된 업로드 대기 이미지 지우기
function imgRegistCancel(){
	alert("삭제버튼 눌렀다.");
	if($(".chk").prop("checked")==true){
		$(".selectedImgList").remove(".imgListUnit");
	}
}

//form 전송
function send(){
	alert("작성한 리뷰를 등록합니다.");
	$("form[name='review-write-form']").attr({
		method:"post",
		action:"/review/write"
	});
	$("form[name='review-write-form']").submit();
}