package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import serviceImpl.UserServiceImpl;
import entity.User;


public class ForgetPassword_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = new UserServiceImpl();
//	private static Log log =   LogFactory.getLog(ForgetPassword_1.class.getName());  

   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 根据用户名查询出来邮箱，并展示给下一级页面
		String username = request.getParameter("username");
		User conUser = new User();
		conUser.setUsername(username);
		User user = service.queryOneUser(conUser);
		request.setAttribute("user", user);
		request.setAttribute("step", "2");
		request.getRequestDispatcher("jsp/user/forgetPassword.jsp").forward(request, response);
		
	}

}
