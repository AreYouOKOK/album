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
			String subject = "个人相册-重置密码";
			StringBuffer content = new StringBuffer("您正在尝试修改个人相册的密码，此操作有可能是发自您本人，也有可能是他人冒用您的身份进行的。</br>如确认修改，请点击以下链接：</br>");
			String url = request.getRequestURL().append("?email="+email+"").toString().replace("_2", "_3");
			content.append(url);
		//  给获取到的邮箱发邮件，邮件包含链接，该链接要可以跳转到修改密码
			Mail.sendAndCc(Mail.smtp, Mail.from, to, "", subject, content.toString(), Mail.username, Mail.password);
			request.setAttribute("step", "3");
			request.setAttribute("email", email);
		//  返回页面，提示邮件已发送
			request.getRequestDispatcher("jsp/user/forgetPassword.jsp").forward(request, response);
			
		}
		
		
	}

}
