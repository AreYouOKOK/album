<%@ page contentType="text/html; charset=utf-8" language="java"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="zh_CN"/> 
<fmt:setBundle basename="message"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet"  type="text/css"  href="<%=request.getContextPath()%>/css/common.css"/>
<link rel = "Shortcut Icon" href="image/album.ico"/>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.js"></script>
<title><fmt:message key="resetPassword"/></title>
<script type="text/javascript">
$(function(){
	  var step = $("#step").val();
	  var $tab1 = $("#tab1");
	  var $tab2 = $("#tab2");
	  var $tab3 = $("#tab3");
	  
	  if(step==1){
		  $tab1.css("color","red");
		  $("#checkEmail").css("display","none");
		  $("#resetPassword").css("display","none");
		  var $username = $("#username");
		  var $validateCode1 = $("#validateCode1");
		  var checkUserName = true;
		  var checkCode1 = true;
		  
		  $username.focus(function(){
			  $(this).val("");
	          $("#username_err").text("");
		 })
		 .blur(function(){
		   if($username.val().length>0){
			   var reg =  /^[A-Za-z0-9]{6,20}$/;
		  	   if(!reg.test($username.val())){
		  		   checkUserName = false;
		  		 $("#username_err").text("***用户名为6-20位的数字字母组合").css("margin-left","300px");
		  	   }
		   }
		 });
		  $validateCode1.focus(function(){
			  $(this).val("");
	          $("#validateCode1_err").text("");
		  })
		  .blur(function(){
			  checkCode1 = true;
			  if($validateCode1.val().length==4){
				  $.ajax({   
		    		    url:'<%=request.getContextPath()%>/checkValidateCode',   
		    		    type:'post',   
		    		    data:'validateCode='+$validateCode1.val(),   
		    		    async : false, //默认为true 异步   
		    		    error:function(){   
		    		       alert('error');   
		    		    },   
		    		    success:function(data){   
		    		       if(data=="false"){
		    		    	   checkCode1 = false;
		    		    	   $("#validateCode1_err").text("验证码不正确");
		    		       }
		    		    }
		    		});
			  }
		  });
		  $("#img1").click(function(){
			  $.ajax({   
	    		    url:'<%=request.getContextPath()%>/validateCodeServlet',   
	    		    type:'post',   
	    		    async : false, //默认为true 异步   
	    		    error:function(){   
	    		       alert('error');   
	    		    }
	    		});
		  });
		 $("#checkUsernameButton").click(function(){
			 if($username.val().length>0&&$validateCode1.val().length==4){
				  if(checkUserName&&checkCode1){
					  $("#checkUsernameForm").submit();
				  }
			  }
		 });
	  }else if(step==2){
		  $tab1.css("color","red");
		  $tab2.css("color","red");
		  $("#checkUsername").css("display","none");
		  $("#checkEmail").css("display","block");
		  $("#resetPassword").css("display","none");
		  var $validateCode2 = $("#validateCode2");
		  var checkCode2 = true;
		  $validateCode2.focus(function(){
			  $(this).val("");
	          $("#validateCode2_err").text("");
		  })
		  .blur(function(){
			  checkCode2 = true;
			  if($validateCode2.val().length==4){
				  $.ajax({   
		    		    url:'<%=request.getContextPath()%>/checkValidateCode',   
		    		    type:'post',   
		    		    data:'validateCode='+$validateCode2.val(),   
		    		    async : false, //默认为true 异步   
		    		    error:function(){   
		    		       alert('error');   
		    		    },   
		    		    success:function(data){   
		    		       if(data=="false"){
		    		    	   checkCode2 = false;
		    		    	   $("#validateCode2_err").text("验证码不正确");
		    		       }
		    		    }
		    		});
			  }
		  });
		  $("#checkEmailButton").click(function(){
			  if($validateCode2.val().length==4&&checkCode2){
				  $("#checkMailForm").submit();
			  }
		  });
		  
	  }else if(step==3){
		  $tab1.css("color","red");
		  $tab2.css("color","red");
		  $("#checkUsername").css("display","none");
		  $("#checkEmail").css("display","block");
		  $("#resetPassword").css("display","none");
		  var email = $("#email").val();
		  $("#checkEmail").html("您的申请已提交成功，请查看您的邮箱："+email);
	  }else if(step==4){
		  $tab1.css("color","red");
		  $tab2.css("color","red");
		  $tab3.css("color","red");
		  $("#checkUsername").css("display","none");
		  $("#checkEmail").css("display","none");
		  $("#resetPassword").css("display","block");
		  var $validateCode3 = $("#validateCode3");
		  var $password = $("#password");
		  var $ctPassword = $("#ctPassword");
		  var checkCode3 = true;
		  var checkPassword = true;
		  var checkCtPassword = true;
		  
		  $password.focus(function(){
	    	   $(this).val("");
	           $("#password_err").text("输入6-20位数字、字母组合");
	       })
	       .blur(function(){
	    	   var reg =  /^[A-Za-z0-9]{6,20}$/;
	    	   if(reg.test($password.val())){
	    		   checkPassword = true;
	    	   }else{
	    		   $("#password_err").text("***密码为6-20位的数字字母组合");
	    	   }
	       });
		  $ctPassword.focus(function(){
	    	   $(this).val("");
	           $("#ctPassword_err").text("再次输入密码");
	       })
	       .blur(function(){
	    	   if($ctPassword.val()!=$password.val()){
	    		   $("#ctPassword_err").text("***两次输入密码不一致");
	    	   }else{
	    		   checkCtPassword_err = true;
	    	   }
	       });
		  $validateCode3.focus(function(){
			  $(this).val("");
	          $("#validateCode3_err").text("");
		  })
		  .blur(function(){
			  checkCode3 = true;
			  if($validateCode3.val().length==4){
				  $.ajax({   
		    		    url:'<%=request.getContextPath()%>/checkValidateCode',   
		    		    type:'post',   
		    		    data:'validateCode='+$validateCode3.val(),   
		    		    async : false, //默认为true 异步   
		    		    error:function(){   
		    		       alert('error');   
		    		    },   
		    		    success:function(data){   
		    		       if(data=="false"){
		    		    	   alert(data);
		    		    	   checkCode3 = false;
		    		    	   $("#validateCode3_err").text("验证码不正确");
		    		       }
		    		    }
		    		});
			  }
		  });
		  $("#resetButton").click(function(){
			  if($password.val().length>=6&&$ctPassword.val().length>=6&&$validateCode3.val().length==4
					  &&checkPassword&&checkCtPassword&&checkCode3){
				  $("#resetPasswordForm").submit();
				  
			  }
		  });
	  }else if(step==5){
		  $tab1.css("color","red");
		  $tab2.css("color","red");
		  $tab3.css("color","red");
		  $("#checkUsername").css("display","none");
		  $("#checkEmail").css("display","none");
		  $("#resetPassword").css("display","block");
		  $("#resetPassword").html("您已成功修改密码。返回<a href='/album/login'>登录</a>");
	  }
});
</script>
</head>

<body>
<input type="hidden" value="${requestScope.step}" id="step"/>
<div id="tab1" class="tab">
<label><fmt:message key="user.forget.step.1"/></label>
</div>
<div id="tab2" class="tab">
<label><fmt:message key="user.forget.step.2"/></label>
</div>
<div id="tab3" class="tab">
<label><fmt:message key="user.forget.step.3"/></label>
</div>
<br></br>
<div id="checkUsername" >
  <form action="forgetPassword_1" method="post" name="checkUsername" id="checkUsernameForm">
   <table width="60%" border="0" cellspacing="0" cellpadding="2">
  <tr>
    <td align="right"><label><fmt:message key="user.username"/></label></td>
    <td><input name="username" type="text" maxlength="20" id="username"/>
      &nbsp;<label id="username_err" class="errMsg"></label>
    </td>
  </tr>
  <tr>
    <td align="right"><fmt:message key="validateCode"/></td>
    <td><input name="validateCode1" type="text" maxlength="4" id="validateCode1"/>
    
      &nbsp;<label id="validateCode1_err" class="errMsg"></label>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><img src="validateCodeServlet" onclick="this.src='validateCodeServlet?d='+Math.random()"></img></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input name="checkUsernameButton" type="button" value="下一步" id="checkUsernameButton" class="flatbtn"/></td>
  </tr>
</table>

 </form>
</div>
<div id="checkEmail">
<input type="hidden" id="email" value="${requestScope.email }"></input>
<form action="forgetPassword_2" method="post" name="checkEmail" id="checkMailForm">
<table width="60%" border="0" cellspacing="0" cellpadding="2">
  <tr>
    <td>&nbsp;</td>
    <td><fmt:message key="user.forget.email"/>${requestScope.user.email}<input type="hidden" name="email" value="${requestScope.user.email}"/>
    </td>
  </tr>
  <tr>
    <td align="right"><label><fmt:message key="validateCode"/></label></td>
    <td align="left"><input name="validateCode2" type="text" maxlength="4" id="validateCode2"/>
     &nbsp;<label id="validateCode2_err" class="errMsg"></label>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><img src="validateCodeServlet" onclick="this.src='validateCodeServlet?d='+Math.random()"></img></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input name="checkEmailButton" type="button" value="找回密码" id="checkEmailButton" class="flatbtn"/></td>
  </tr>
</table>
</form>
</div>

<div id="resetPassword">

<form action="forgetPassword_4" method="post" name="resetPasswordForm" id="resetPasswordForm">
<table width="60%" border="0" cellspacing="0" cellpadding="2">
  <tr>
    <td align="right"><label><fmt:message key="user.password"/></label></td>
    <td align="left"><input name="password" type="password" id="password"/>
    &nbsp;<label id="password_err" class="errMsg"><fmt:message key="user.password.message"/></label>
    </td>
  </tr>
  <tr>
    <td align="right"><label><fmt:message key="user.ctPassword"/></label></td>
    <td align="left"><input name="ctPassword" type="password" id="ctPassword"/>
    &nbsp;<label id="ctPassword_err" class="errMsg"><fmt:message key="user.ctPassword.message"/></label></td>
  </tr>
  <tr>
    <td align="right"><fmt:message key="validateCode"/></td>
    <td align="left"><input name="validateCode3" type="text" maxlength="4" id="validateCode3"/>
     &nbsp;<label id="validateCode3_err" class="errMsg"></label>
     <input type="hidden" name="email" value="${requestScope.email}"/>
    </td>
  </tr>
    <tr>
    <td>&nbsp;</td>
    <td><img src="validateCodeServlet" onclick="this.src='validateCodeServlet?d='+Math.random()"></img></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input name="resetButton" type="button" value="<fmt:message key='user.resetPassword'/>" id="resetButton" class="flatbtn"/></td>
  </tr>
</table>
</form></div>
</body>
</html>