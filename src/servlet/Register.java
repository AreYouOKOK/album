package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.TokenProccessor;

@WebServlet(name="Register",urlPatterns="/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Log log =  
            LogFactory.getLog(Register.class.getName());  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String token = TokenProccessor.getInstance().makeToken();//创建令牌
		log.info(Thread.currentThread().getStackTrace()[1].getClassName()+"Token:"+token);
		session.setAttribute("token", token);  //在服务器使用session保存token(令牌)
		session.removeAttribute("errorMessage");
		request.getRequestDispatcher("jsp/user/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
