package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.JdbcUtil;
import util.SQLBaseImpl;
import dao.AlbumDao;
import entity.Album;

public class AlbumDaoImpl extends SQLBaseImpl implements AlbumDao {
	private static Log log =  
            LogFactory.getLog(UserDaoImpl.class.getName());  
	@Override
	public void addAlbum(Connection conn, Album album) {
		add(conn, album);

	}

	@Override
	public void updateAlbum(Connection conn, Album queAlbum, Album conAlbum) {
		update(conn, queAlbum, conAlbum);

	}

	@Override
	public void deleteAlbum(Connection conn, Album album) {
		delete(conn, album);

	}

	@Override
	public Album queryForAlbum(Connection conn, Album queAlbum, Album conAlbum) {
		return (Album) queryForObject(conn,conAlbum);

	}

	@Override
	public List<Album> queryForAlbumList(Connection conn, Album queAlbum,
			Album conAlbum) {
		return queryForList(conn,queAlbum, conAlbum);

	}

	@Override
	public int getMaxId(Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int maxId = 0;
		String sql = "select max(id) from album";
		try {
			 ps = conn.prepareStatement(sql);
			 rs = ps.executeQuery();
			 log.info(Thread.currentThread().getStackTrace()[1].getClassName()+
					 Thread.currentThread().getStackTrace()[1].getMethodName()+"Ö´ÐÐSQL£º"+sql);
			 while(rs.next()){
				 maxId = rs.getInt(1);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(rs);
			JdbcUtil.release(ps);
		}
		
		return maxId+1;
	}

}
