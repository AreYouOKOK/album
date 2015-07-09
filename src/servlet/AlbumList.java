package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.AlbumService;
import serviceImpl.AlbumServiceImpl;
import entity.Album;


public class AlbumList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AlbumService service = new AlbumServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userid");
		if(userid!=null&&userid.intValue()!=0){
			Album conAlbum = new Album();
			conAlbum.setUserid(userid);
			Album queAlbum = new Album();
			List<Album> list = service.queryAlbumList(queAlbum, conAlbum);
			//TODO 给封面图片添加水印，水印为相册内照片的数量
			request.setAttribute("list", list);
			request.getRequestDispatcher("jsp/album/albumList.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("login").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
