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
			//验证邮箱是否真实存在
			if(email!=null&&!MailValid.valid(email, "localhost")){
				session.setAttribute("errorMessage",ErrorMessage.邮箱地址不存在);
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
			//注册成功，发送邮件
			String subject = "注册成功";
			String content = "恭喜您成功注册个人相册系统，用户名为："+user.getUsername()+"。请保管好您的用户名密码！";
			Mail.sendAndCc(Mail.smtp, Mail.from, user.getEmail(), "",subject,content , Mail.username, Mail.password);
			//重置token,防止重复提交
			request.getSession().removeAttribute("token");
			response.sendRedirect("login");
		}else{
			session.setAttribute("errorMessage",ErrorMessage.请不要重复提交);
		}
		
		
	}
	 

}
