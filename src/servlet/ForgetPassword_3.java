package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="ForgetPassword_3",urlPatterns="/forgetPassword_3")
public class ForgetPassword_3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		if(email!=null){
			request.setAttribute("email", email);
			request.setAttribute("step", "4");
			request.getRequestDispatcher("jsp/user/forgetPassword.jsp").forward(request, response);
		}
		
		
	}

}
