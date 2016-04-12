package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;
import serviceImpl.UserServiceImpl;
import util.CheckRepeatSubmit;
import util.ErrorMessage;
import util.MD5;
import util.Mail;
import util.MailValid;
import entity.User;


@WebServlet(name="RegisterExe",urlPatterns="/registerExe")
public class RegisterExe extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService service = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("errorMessage");
		if(CheckRepeatSubmit.checkRepeatSubmit(request)){
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			//��֤�����Ƿ���ʵ����
			if(email!=null&&!MailValid.valid(email, "localhost")){
				session.setAttribute("errorMessage",ErrorMessage.�����ַ������);
				request.getRequestDispatcher("jsp/user/register.jsp").forward(request, response);
				return;
			};
			if(password!=null){
				password = MD5.GetMD5Code(password);
			}
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			service.addUser(user);
			//ע��ɹ��������ʼ�
			String subject = "ע��ɹ�";
			String content = "��ϲ���ɹ�ע��������ϵͳ���û���Ϊ��"+user.getUsername()+"���뱣�ܺ������û������룡";
			Mail.sendAndCc(Mail.smtp, Mail.from, user.getEmail(), "",subject,content , Mail.username, Mail.password);
			//����token,��ֹ�ظ��ύ
			request.getSession().removeAttribute("token");
			response.sendRedirect("login");
		}else{
			session.setAttribute("errorMessage",ErrorMessage.�벻Ҫ�ظ��ύ);
		}
		
		
	}
	 

}
