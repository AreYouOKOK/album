package util;

import javax.servlet.http.HttpServletRequest;

public class CheckRepeatSubmit {
  public static boolean checkRepeatSubmit(HttpServletRequest request){
	  String client_token = request.getParameter("token");
	  String server_token = (String) request.getSession().getAttribute("token");
      //1、如果用户提交的表单数据中没有token，则用户是重复提交了表单
      if(client_token==null) return false;
      //2、如果当前用户的Session中不存在Token(令牌)，则用户是重复提交了表单
      if(server_token==null) return false;
        //3、存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
      if(!client_token.equals(server_token))return false;
	return true;
       
  }
}
