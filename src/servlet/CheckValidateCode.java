package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="CheckValidateCode",urlPatterns="/checkValidateCode")
public class CheckValidateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		PrintWriter out = response.getWriter();
		String client_code = request.getParameter("validateCode");
		String server_code = (String) request.getSession().getAttribute("validateCode");
		boolean result = true;
		if(client_code==null){
			result =  false;
		}else if(server_code==null){
			result =  false;
		}else if(!client_code.toUpperCase().equals(server_code)){
			result = false;
		}
		out.print(result);
		out.close();
		
	}

}
