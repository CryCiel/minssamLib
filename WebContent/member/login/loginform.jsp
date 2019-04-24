<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<script>
function requestLogin(){
   $("form[name='login-form']").attr({
      method:"post",
      action:"/member/login"
   });
   $("form[name='login-form']").submit(); 
}
</script>
</head>
<body>
   <div class="login_wrap">
      <form name="login-form">
         <fieldset>
            <legend>회원 로그인</legend>
            <div class="login_area">
               <div class="input_info">
                  <div class="id_area">
                     <input id="id" type="text" name="id" placeholder="아이디를 입력하세요"/>
                  </div>
                  <p class="login_btn">
                     <a href="javascript:requestLogin()" title="로그인 버튼">로그인</a>
                  </p>
                  <div class="pass_area">
                     <input id="pass" type="password" name="pass" placeholder="비밀번호를 입력해주세요"/> 
                  </div>   
               </div>
               <p class="search_id_pw">
                  <a href="#" title="아이디 찾기">아이디</a><span>·</span><a href="#"
                     title="비밀번호 찾기">비밀번호 찾기</a>
               </p>
               <p class="sign_up_area">
                  <a href="/member/regist/registform.jsp" title="회원가입 버튼">회원가입</a>
               </p>
            </div>
         </fieldset>
      </form>
   </div>
</body>
</html>