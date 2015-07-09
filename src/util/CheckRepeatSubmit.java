package util;

import javax.servlet.http.HttpServletRequest;

public class CheckRepeatSubmit {
  public static boolean checkRepeatSubmit(HttpServletRequest request){
	  String client_token = request.getParameter("token");
	  String server_token = (String) request.getSession().getAttribute("token");
      //1������û��ύ�ı�������û��token�����û����ظ��ύ�˱�
      if(client_token==null) return false;
      //2�������ǰ�û���Session�в�����Token(����)�����û����ظ��ύ�˱�
      if(server_token==null) return false;
        //3���洢��Session�е�Token(����)����ύ��Token(����)��ͬ�����û����ظ��ύ�˱�
      if(!client_token.equals(server_token))return false;
	return true;
       
  }
}
