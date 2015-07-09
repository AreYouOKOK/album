package serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import service.AlbumService;
import util.DateUtil;
import util.JdbcUtil;
import dao.AlbumDao;
import daoImpl.AlbumDaoImpl;
import entity.Album;

public class AlbumServiceImpl implements AlbumService {
    private AlbumDao dao = new AlbumDaoImpl();
	@Override
	public void addAlbum(Album album) {
		Connection conn = JdbcUtil.getConnection();
		int id = dao.getMaxId(conn);
		album.setId(id);
		album.setCreateTime(DateUtil.formatDate(new Date()));
		dao.addAlbum(conn, album);
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtil.release(conn);

	}

	@Override
	public void updateAlbum(Album queAlbum, Album conAlbum) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAlbum(Album album) {
		// TODO Auto-generated method stub

	}

	@Override
	public  Album queryOneAlbum(Album queAlbum, Album conAlbum) {
		// TODO Auto-generated method stub
        return null;
	}

	@Override
	public List<Album> queryAlbumList(Album queAlbum, Album conAlbum) {
		Connection conn = JdbcUtil.getConnection();
		List<Album> list = dao.queryForAlbumList(conn, queAlbum, conAlbum);
		JdbcUtil.release(conn);
        return list;
	}

}
