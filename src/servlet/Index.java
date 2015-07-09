package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import service.UserService;
import serviceImpl.UserServiceImpl;
import util.MD5;

public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService service = new UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			String username = null;
			String md5Pass = null;
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("username")){
					username = cookie.getValue();
				}
				if(cookie.getName().equals("md5Pass")){
					md5Pass = cookie.getValue();
				}
				if(username!=null&&md5Pass!=null){
					User queryConditionUser = new User();
					queryConditionUser.setUsername(username);
					User user = service.queryOneUser(queryConditionUser);
					HttpSession session = request.getSession();
					session.setAttribute("userid", user.getId());
					String newMd5Pass = MD5.GetMD5Code(username);
					if(newMd5Pass.equals(md5Pass)){
						response.sendRedirect("albumList");
						return ;
					}
				}
				
			}
		}
		request.getRequestDispatcher("login").forward(request, response);
		
		
	}

	

}
