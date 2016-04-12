package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;
import serviceImpl.UserServiceImpl;
import util.CheckRepeatSubmit;
import util.ErrorMessage;
import util.MD5;
import entity.User;

@WebServlet(name="LoginExe",urlPatterns="/loginExe")
public class LoginExe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = new UserServiceImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("errorMessage");
		if(CheckRepeatSubmit.checkRepeatSubmit(request)){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = null;
			if(username!=null&&password!=null){
				User queryConditionUser = new User();
				queryConditionUser.setUsername(username);
				user = service.queryOneUser(queryConditionUser);
				if (user == null) {
					session.setAttribute("errorMessage", ErrorMessage.用户名不存在);
				} else {
					password = MD5.GetMD5Code(password);
					if (!password.equals(user.getPassword())) {
						session.setAttribute("errorMessage", ErrorMessage.用户名或密码不正确);
						request.setAttribute("user", user);
					} else {
						Cookie cookie1 = new Cookie("username", username);
						cookie1.setMaxAge(365*24*60*60);
						response.addCookie(cookie1);
						String md5Pass = MD5.GetMD5Code(username);
						Cookie cookie2 = new Cookie("md5Pass", md5Pass);
						cookie2.setMaxAge(365*24*60*60);
						response.addCookie(cookie2);
						request.getSession().removeAttribute("token");
						session.setAttribute("userid", user.getId());
						response.sendRedirect("albumList");
						return ;
					}
				}
			}
			
		}else{
			session.setAttribute("errorMessage", ErrorMessage.请不要重复提交);
		}
		request.getRequestDispatcher("jsp/user/login.jsp").forward(request, response);
	}
}
