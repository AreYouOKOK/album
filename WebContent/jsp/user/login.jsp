<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="zh_CN"/> 
<fmt:setBundle basename="message"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><fmt:message key="login"/></title>
<link rel="stylesheet"  type="text/css"  href="<%=request.getContextPath()%>/css/common.css"/>
<link rel = "Shortcut Icon" href="image/album.ico"/>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.js"></script>
<script type="text/javascript">
 $(function(){
	 var checkUserName = true;
	 var checkPassword = true;
	 var $username = $("#username");
	 var $password = $("#password");
	 $username.focus(function(){
		  $(this).val("");
          $("#username_err").text("");
	 })
	 .blur(function(){
	   if($username.val().length>0){
		   var reg =  /^[A-Za-z0-9]{6,20}$/;
	  	   if(!reg.test($username.val())){
	  		   checkUserName = false;
	  		 $("#username_err").text("***用户名为6-20位的数字字母组合");
	  	   }
	   }
	 });
	 
	 
	 
     $password.focus(function(){
  	   $(this).val("");
         $("#password_err").text("");
     })
     .blur(function(){
  	   if($password.val().length>0){
  		 var reg =  /^[A-Za-z0-9]{6,20}$/;
    	   if(!reg.test($password.val())){
    		   checkPassword = false;
    		   $("#password_err").text("***密码为6-20位的数字字母组合");
    	   }
  	   }
     });
     
     $("#submitButton").click(function(){
    	 
  	  if($username.val().length>0&&$password.val().length>0){
  		 if(!checkUserName){
    		   $("#username_err").text("!!!输入格式正确的用户名");
    	   }else if(!checkPassword){
    		   $("#password_err").text("！！！输入正确格式的密码");
    	   }else{
    		   $("#loginForm").submit();
    	   }
  	  }
     });
     
 });
</script>
</head>

<body>
<form action="loginExe" method="post" name="loginForm"  id="loginForm">
<input type="hidden" name="token"
			value="<%=session.getAttribute("token") %>" />
  <table>
    <tr>
      <td  align="right"> <label for="username"><fmt:message key="user.username"/></label></td>
      <td align="left">
      <input name="username" type="text" id="username" maxlength="20" value="${requestScope.user.username}"/>
      &nbsp;<label id="username_err" class="errMsg"></label>
      </td>
    </tr>
 
    <tr>
      <td  align="right"><label for="password"><fmt:message key="user.password"/></label></td>
      <td align="left">
          <input name="password" type="password" id="password" maxlength="20" value="${requestScope.user.password}"/>
           &nbsp;<label id="password_err" class="errMsg"></label>
     </td>
    </tr>
   <tr>
      <td colspan="2" align="center"><label style="color:red">${sessionScope.errorMessage}</label></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td ><input type="button" name="submitButton" id="submitButton" value="<fmt:message key='user.login'/>" class="flatbtn"/></td>
    </tr>
    <tr>
      <td  align="right"><a href="forgetPassword"><fmt:message key="user.forgetPassword"/></a></td>
       <td  align="left"><a href="register"><fmt:message key="user.register"/></a></td>
    </tr>
    
  </table>
</form>
</body>
</html>