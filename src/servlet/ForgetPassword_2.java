package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Mail;


public class ForgetPassword_2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		if(email!=null){
			String to = request.getParameter("email");
			String subject = "�������-��������";
			StringBuffer content = new StringBuffer("�����ڳ����޸ĸ����������룬�˲����п����Ƿ��������ˣ�Ҳ�п���������ð��������ݽ��еġ�</br>��ȷ���޸ģ������������ӣ�</br>");
			String url = request.getRequestURL().append("?email="+email+"").toString().replace("_2", "_3");
			content.append(url);
		//  ����ȡ�������䷢�ʼ����ʼ��������ӣ�������Ҫ������ת���޸�����
			Mail.sendAndCc(Mail.smtp, Mail.from, to, "", subject, content.toString(), Mail.username, Mail.password);
			request.setAttribute("step", "3");
			request.setAttribute("email", email);
		//  ����ҳ�棬��ʾ�ʼ��ѷ���
			request.getRequestDispatcher("jsp/user/forgetPassword.jsp").forward(request, response);
			
		}
		
		
	}

}
