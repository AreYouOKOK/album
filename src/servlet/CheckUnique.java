package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import serviceImpl.UserServiceImpl;

public class CheckUnique extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		int count = 0;
		if(username!=null){
			count =  service.countUsername(username);
		}else if(email!=null){
			count = service.countEmail(email);
		}
		out.print(count);
		out.close();
	}

}
