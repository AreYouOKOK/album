package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import serviceImpl.UserServiceImpl;
import util.MD5;
import entity.User;

@WebServlet(name="ForgetPassword_4",urlPatterns="/forgetPassword_4")
public class ForgetPassword_4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService service = new UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//…Ë÷√–¬√‹¬Î
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		password = MD5.GetMD5Code(password);
		User newUser = new User();
		newUser.setPassword(password);
		User oldUser = new User();
		oldUser.setEmail(email);
        try{
        	service.updateUser(newUser,oldUser);
        	request.setAttribute("step", "5");
        }catch(Exception e){
        	request.setAttribute("step", "4");
        }finally{
        	request.getRequestDispatcher("jsp/user/forgetPassword.jsp").forward(request, response);
        }
        
		
	}

}
