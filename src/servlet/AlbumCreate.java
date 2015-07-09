package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AlbumService;
import serviceImpl.AlbumServiceImpl;

import com.mysql.jdbc.StringUtils;

import entity.Album;


public class AlbumCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AlbumService service = new AlbumServiceImpl();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("albumName");
		String description = request.getParameter("description");
		if(!StringUtils.isEmptyOrWhitespaceOnly(name)){
		    int userid = (int) request.getSession().getAttribute("userid");
			Album album = new Album();
			album.setName(name);
			album.setDescription(description);
			album.setUserid(userid);
			service.addAlbum(album);
			response.sendRedirect("albumList");
			return;
		}
		request.getRequestDispatcher("albumList").forward(request, response);
	}

}
