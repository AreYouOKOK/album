<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="zh_CN"/> 
<fmt:setBundle basename="message"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><fmt:message key="register"/></title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel = "Shortcut Icon" href="image/album.ico"/>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.js"></script>
<script type="text/javascript">
   var checkUserName = false;
   var checkEmail = false;
   var checkPassword = false;
   var checkConPassword = false;
   $(function(){
       var $username = $("#username");
       $username.css("color:red");
       var countUsername = 0;
       $username.focus(function(){
           $(this).val("");
           $("#username_err").text("输入6-20位数字、字母组合");
       })
       .blur(function(){
    	   var reg =  /^[A-Za-z0-9]{6,20}$/;
    	   if(reg.test($username.val())){
    		   checkUserName = true;
    	   }else{
    		   $("#username_err").text("***用户名为6-20位的数字字母组合");
    	   }
    	   $.ajax({   
    		    url:'<%=request.getContextPath()%>/checkUnique',   
    		    type:'post',   
    		    data:'username='+$username.val(),   
    		    async : false, //默认为true 异步   
    		    error:function(){   
    		       alert('error');   
    		    },   
    		    success:function(data){   
    		        countUsername = data;
    		    }
    		});
    	   if(countUsername>0){
    		   $("#username_err").text("***用户名已经被注册啦");
    		   checkUserName = false;
    	   }
       });
       
       
       var $email = $("#email");
       var countEmail = 0;
       $email.focus(function(){
    	   $(this).val("");
           $("#email_err").text("输入邮箱：QQ、Yahoo等");
       })
       .blur(function(){
    	   var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    	   if(reg.test($email.val())){
    		  checkEmail=true; 
    	   }else{
    		   $("#email_err").text("***邮箱格式不正确");
    	   }
    	   $.ajax({   
   		    url:'<%=request.getContextPath()%>/checkUnique',   
   		    type:'post',   
   		    data:'email='+$email.val(),   
   		    async : false, //默认为true 异步   
   		    error:function(){   
   		       alert('error');   
   		    },   
   		    success:function(data){ 
   		    	countEmail = data;
   		    }
   		});
   	   if(countEmail>0){
   		   $("#email_err").text("***该邮箱已经被注册啦");
   		   checkEmail = false;
   	   }
       });
      
      

	   
       var $password = $("#password");
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
       
       
       var $conpassword = $("#conpassword");
       $conpassword.focus(function(){
    	   $(this).val("");
           $("#conpassword_err").text("再次输入密码");
       })
       .blur(function(){
    	   if($conpassword.val()!=$password.val()){
    		   $("#conpassword_err").text("***两次输入密码不一致");
    	   }else{
    		   checkConPassword = true;
    	   }
       });
       $("#submitButton").click(function(){
    	   if(!checkUserName){
    		   $("#username_err").text("!!!输入格式正确的用户名");
    	   }else if(!checkEmail){
    		   $("#email_err").text("!!!输入正确格式的邮箱");
    	   }else if(!checkPassword){
    		   $("#password_err").text("！！！输入正确格式的密码");
    	   }else if(!checkConPassword){
    		   $("#conpassword_err").text("！！！输入正确格式的密码");
    	   }else{
    		   $("#registerForm").submit();
    	   }
       });
   });
 
</script>
</head>

<body>
	<form  method="post" action="<%=request.getContextPath()%>/registerExe" id="registerForm">
		<input type="hidden" name="token"
			value="<%=session.getAttribute("token") %>" />
		<table>
			<tr>
				<td align="right"><fmt:message key="user.username"></fmt:message></td>
				<td align="left"><label for="username"></label> <input
					name="username" type="text" id="username" maxlength="20" /> &nbsp;<label
					id="username_err" class="errMsg"><fmt:message key="user.username.message"/></label></td>
			</tr>
			<tr>
				<td align="right"><fmt:message key="user.email"></fmt:message></td>
				<td align="left"><label for="email"></label> <input
					name="email" type="text" id="email" maxlength="100" /> &nbsp;<label
					id="email_err" class="errMsg"><fmt:message key="user.email.message"/></label></td>
			</tr>
			<tr>
				<td align="right"><fmt:message key="user.password"/></td>
				<td align="left"><label for="email"></label> <input
					name="password" type="password" id="password" maxlength="20" />
					&nbsp;<label id="password_err" class="errMsg"><fmt:message key="user.password.message"/></label></td>
			</tr>
			<tr>
				<td align="right"><fmt:message key="user.ctPassword"/></td>
				<td align="left"><label for="conpassword"></label> <input
					name="conpassword" type="password" id="conpassword" maxlength="20" />
					&nbsp;<label id="conpassword_err" class="errMsg"><fmt:message key="user.ctPassword.message"/></label></td>
			</tr>
			 <tr>
      <td colspan="2" align="center"><label style="color:red">${sessionScope.errorMessage}</label></td>
    </tr>
			<tr>
				<td align="right"><input type="button" name="submitButton"
					id="submitButton" value="<fmt:message key='user.register'/>" class="flatbtn"/></td>
				<td align="left"><input  type="reset" value="<fmt:message key='reset'/>" class="flatbtn"/></td>
			</tr>
		</table>
	</form>
</body>
</html>